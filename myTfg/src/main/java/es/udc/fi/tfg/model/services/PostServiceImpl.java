package es.udc.fi.tfg.model.services;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import es.udc.fi.tfg.model.entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    private NotificationDao notificationDao;

    @Autowired
    private ApunteDao apunteDao;

    @Autowired
    private UniversityDao universityDao;

    @Autowired
    private EtiquetaDao etiquetaDao;

    @Autowired
    private EtiquetaOfPostDao etiquetaOfPostDao;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public Post uploadPost(Long userId, String titulo, String description, String academicYear, Long subjectId, List<MultipartFile> files, List<String> etiquetas) throws InstanceNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeyException {

        Users user = permissionChecker.checkUser(userId);
        Optional<Subject> subject = subjectDao.findById(subjectId);
        Post post;

        if (!subject.isPresent()) {
            throw new InstanceNotFoundException("Asignatura no encontrada", subject);
        }

        post = new Post(user, titulo, description, Integer.parseInt(academicYear.split("/")[0]), LocalDateTime.now(), subject.get(), new BigDecimal(0));

        postDao.save(post);

        for(MultipartFile file : files){
            fileStorageService.uploadFile(file);
            /*String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Define the storage path
            Path destination = Paths.get("frontend/public").resolve(fileName).normalize().toAbsolutePath();

            // Create the directory if it doesn't exist
            Files.createDirectories(destination.getParent());

            // Save the file
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Apunte apunte = new Apunte(fileName, file.getContentType(), file.getSize(), destination.toString(), post);
            apunteDao.save(apunte);*/

        }

        for (FollowedUser follower: user.getFollowerUsers() ) {
            Notification notification = new Notification(false, true, false, follower.getFollower(), post);
            notificationDao.save(notification);
        }

        for (FollowedSubject follower: subject.get().getFollowedSubjects() ) {
            Notification notification = new Notification(false, true, true, follower.getUser(), post);
            notificationDao.save(notification);
        }

        for (String etiqueta: etiquetas) {
            String[] parts = etiqueta.split("-");
            String key = parts[0];
            String value = parts[1];

            Optional<Etiqueta> etiquetaOptional = etiquetaDao.findByKeyAndValue(key, value);

            Etiqueta etiqueta1;
            if (etiquetaOptional.isEmpty()) {
                etiqueta1 = new Etiqueta(key, value);
                etiquetaDao.save(etiqueta1);
            }
            else etiqueta1 = etiquetaOptional.get();

            EtiquetaOfPost etiquetaOfPost = new EtiquetaOfPost(post,etiqueta1);
            etiquetaOfPostDao.save(etiquetaOfPost);
        }

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
        post.setAcademicYear(Integer.parseInt(academicYear.split("/")[0]));

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
    public List<Apunte> findApuntesByPost(Long id) {
        return apunteDao.findByPost_Id(id);
    }

    @Override
    public Block<Post> findPostsByEtiquetaId(Long etiquetaId, int page, int size) throws InstanceNotFoundException {

        Optional<Etiqueta> etiquetaOptional = etiquetaDao.findById(etiquetaId);
        if (etiquetaOptional.isEmpty())
            throw new InstanceNotFoundException("project.entities.etiqueta", etiquetaId);

        Slice<EtiquetaOfPost> slice = etiquetaOfPostDao.findByEtiquetaId(etiquetaId, PageRequest.of(page, size));

        List<Post> posts = slice.getContent().stream()
                .map(EtiquetaOfPost::getPost).toList();

        return new Block<>(posts, slice.hasNext());
    }

    @Override
    public Block<Post> findPosts(String keywords, Long universityId, Long subjectId, String minYear, String maxYear, String order, int page, int size) {

        int minYear2, maxYear2;
        if (minYear != null) minYear2 = Integer.parseInt(minYear.split("/")[0]);
        else minYear2 = 0;
        if (maxYear != null)
            maxYear2 = Integer.parseInt(maxYear.split("/")[0]);
        else maxYear2 = 0;

        Slice<Post> slice = postDao.find(keywords, universityId, subjectId, minYear2, maxYear2, order, page, size);

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