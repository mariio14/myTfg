package es.udc.fi.tfg.model.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.udc.fi.tfg.model.entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;

/**
 * The type Post service.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService  {

    @Autowired
    private PostDao postDao;

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private UniversityDao universityDao;

    @Override
    public Post uploadPost(Long userId, String titulo, String description, String academicYear, Long subjectId) throws InstanceNotFoundException {

        Users user = permissionChecker.checkUser(userId);
        Optional<Subject> subject = subjectDao.findById(subjectId);
        Post post;

        if (!subject.isPresent()) {
            throw new InstanceNotFoundException("Asignatura no encontrada", subject);
        }

        post = new Post(user, titulo, description, academicYear, LocalDateTime.now(), subject.get());

        postDao.save(post);
        return post;
    }

    @Override
    public void deletePost(Long userId, Long postId) throws InstanceNotFoundException {

        Users user = permissionChecker.checkUser(userId);

        Optional<Post> postOptional = postDao.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            if (post.getUser().getId().equals(user.getId())) {
                postDao.delete(post);
            }
        } else {
            throw new InstanceNotFoundException("project.entities.post", postId);
        }
    }

    @Override
    public Post updatePost(Long userId, Long postId, String titulo, String descripcion, String academicYear, Long subjectId)
            throws InstanceNotFoundException {

        permissionChecker.checkUser(userId);

        Post post;
        Optional<Post> postOptional = postDao.findById(postId);

        if (postOptional.isPresent()) {
            post = postOptional.get();
        } else {
            throw new InstanceNotFoundException("project.entities.post", postId);
        }

        post.setTitle(titulo);
        post.setDescription(descripcion);
        post.setAcademicYear(academicYear);

        return post;

    }

    @Override
    public List<University> findAllUniversities() {
        Iterable<University> universities = universityDao.findAll(Sort.by(Sort.Direction.ASC, "uniName"));
        List<University> universitiesAsList = new ArrayList<>();

        universities.forEach(c -> universitiesAsList.add(c));

        return universitiesAsList;
    }

    @Override
    public List<Subject> findAllSubjectsByUni(Long id) {
        return subjectDao.findByUniversityId(id);
    }

    @Override
    public Block<Post> findPosts(String keywords, String minYear, String maxYear, String order, int page, int size) {

        Slice<Post> slice = postDao.find(keywords, minYear, maxYear, order, page, size);

        return new Block<>(slice.getContent(), slice.hasNext());

    }


    @Override
    public Block<Post> findPostsByUserId(Long userId, int page, int size) {

        Slice<Post> slice = postDao.findByUserId(userId, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());

    }

    @Override
    public Post findPostById(Long id) throws InstanceNotFoundException {
        Optional<Post> post = postDao.findById(id);

        if (post.isEmpty()) {
            throw new InstanceNotFoundException("project.entities.post", id);
        }
        return post.get();
    }

}