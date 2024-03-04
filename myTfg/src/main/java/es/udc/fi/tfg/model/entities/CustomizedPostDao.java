package es.udc.fi.tfg.model.entities;

import java.math.BigDecimal;

import org.springframework.data.domain.Slice;

/**
 * The Interface CustomizedPostDao.
 */
public interface CustomizedPostDao {


    Slice<Post> find(String keywords, String minYear, String maxYear, String order, int page, int size);

}
