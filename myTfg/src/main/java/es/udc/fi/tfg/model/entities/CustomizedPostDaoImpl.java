package es.udc.fi.tfg.model.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

/**
 * The Class CustomizedPostDaoImpl.
 */
public class CustomizedPostDaoImpl implements CustomizedPostDao {

    /** The entity manager. */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Gets the tokens.
     *
     * @param keywords the keywords
     * @return the tokens
     */
    private String[] getTokens(String keywords) {

        if (keywords == null || keywords.length() == 0) {
            return new String[0];
        } else {
            return keywords.split("\\s");
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public Slice<Post> find(String keywords, String minYear, String maxYear, String order, int page, int size) {

        String[] tokens = getTokens(keywords);
        String queryString = "SELECT p FROM Post p";

        if (tokens.length > 0 || minYear != null || maxYear != null) {
            queryString += " WHERE ";
        }

        if (minYear != null) {
            queryString += "p.year >= :minYear";
        }

        if (maxYear != null) {
            if (minYear != null) {
                queryString += " AND ";
            }
            queryString += "p.year <= :maxYear";
        }

        if (tokens.length != 0) {
            if (minYear != null || maxYear != null) {
                queryString += " AND ";
            }

            for (int i = 0; i<tokens.length-1; i++) {
                queryString += "LOWER(p.title) LIKE LOWER(:token" + i + ") AND LOWER(p.description) LIKE LOWER(:token" + i + ") AND";
            }

            queryString += "LOWER(p.title) LIKE LOWER(:token" + (tokens.length-1) + ") AND LOWER(p.description) LIKE LOWER(:token" + (tokens.length-1) + ")";
        }


        if(order == null){
            queryString += " ORDER BY p.title";
        }else if(order.equals("YEARA")){
            queryString += " ORDER BY p.academicYear ASC";
        }else if(order.equals("YEARD")){
            queryString += " ORDER BY p.academicYear DESC";
        }else if(order.equals("NEW")){
            queryString += " ORDER BY p.creationDate DESC";
        }else if(order.equals("OLD")){
            queryString += " ORDER BY p.creationDate ASC";
        }else if(order.equals("POPULARITY")){
            queryString += " ORDER BY p.avgRating DESC";
        }


        Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);

        if (minYear != null) {
            query.setParameter("minPrice", minYear);
        }

        if (maxYear != null) {
            query.setParameter("maxPrice", maxYear);
        }

        if (tokens.length != 0) {
            for (int i = 0; i<tokens.length; i++) {
                query.setParameter("token" + i, '%' + tokens[i] + '%');
            }
        }

        List<Post> posts = query.getResultList();
        boolean hasNext = posts.size() == (size+1);

        if (hasNext) {
            posts.remove(posts.size()-1);
        }

        return new SliceImpl<>(posts, PageRequest.of(page, size), hasNext);
    }

}