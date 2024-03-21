package es.udc.fi.tfg.rest.controllers;


import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Apunte;
import es.udc.fi.tfg.model.entities.Post;
import es.udc.fi.tfg.model.entities.Rating;
import es.udc.fi.tfg.model.entities.Subject;
import es.udc.fi.tfg.model.services.Block;
import es.udc.fi.tfg.model.services.CommentService;
import es.udc.fi.tfg.model.services.PostService;
import es.udc.fi.tfg.model.services.RatingService;
import es.udc.fi.tfg.model.services.exceptions.AlreadyFollowingException;
import es.udc.fi.tfg.model.services.exceptions.NoRatingException;
import es.udc.fi.tfg.model.services.exceptions.NotFollowingException;
import es.udc.fi.tfg.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/upload")
    public PostDto uploadPost(@RequestAttribute Long userId,
                              @RequestParam Map<String, MultipartFile> files,
                              @RequestParam("titulo") String titulo,
                              @RequestParam("descripcion") String descripcion,
                              @RequestParam("academicYear") String academicYear,
                              @RequestParam("subjectId") String subjectId)
            throws InstanceNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeyException {

        List<MultipartFile> fileList = new ArrayList<>(files.values());

        Post post = postService.uploadPost(userId,titulo,descripcion,academicYear, Long.valueOf(subjectId), fileList);

        List<Apunte> apunteList = new ArrayList<>(post.getApuntes());

        return PostConversor.toPostDto(post, apunteList);
    }


    /**
     * Find posts by user id.
     *
     * @param userId the user id
     * @param page   the page
     * @return the block dto
     */
    @GetMapping("/myFeed")
    public BlockDto<PostDto> findPostsByUserId(@RequestAttribute Long userId,
                                               @RequestParam(defaultValue = "0") int page) throws IOException {

        Block<Post> postBlock = postService.findPostsByUserId(userId, page, 2);

        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : postBlock.getItems()) {
            List<Apunte> apuntes = postService.findApuntesByPost(post.getId());
            PostDto postDto = PostConversor.toPostDto(post, apuntes);
            postDtoList.add(postDto);
        }
        return new BlockDto<>(postDtoList, postBlock.getExistMoreItems());
    }


    @GetMapping("/feed/{userId}")
    public BlockDto<PostDto> findPostsByOtherUserId(@PathVariable Long userId,
                                               @RequestParam(defaultValue = "0") int page) throws IOException {

        Block<Post> postBlock = postService.findPostsByUserId(userId, page, 2);

        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : postBlock.getItems()) {
            List<Apunte> apuntes = postService.findApuntesByPost(post.getId());
            PostDto postDto = PostConversor.toPostDto(post,apuntes);
            postDtoList.add(postDto);
        }
        return new BlockDto<>(postDtoList, postBlock.getExistMoreItems());
    }


    @GetMapping("/feed")
    public BlockDto<PostFeedDto> findPosts(@RequestParam(required=false) String keywords, @RequestParam(required=false) Long universityId,
                                       @RequestParam(required=false) Long subjectId, @RequestParam(required=false) String minYear,
                                       @RequestParam(required=false) String maxYear, @RequestParam(required=false) String order,
                                       @RequestParam(defaultValue="0") int page) throws IOException {   //Meter universityId y subjectId

        Block<Post> postBlock = postService.findPosts(keywords != null ? keywords.trim() : null,
                universityId, subjectId, minYear, maxYear, order, page, 2);

        List<PostFeedDto> postDtoList = new ArrayList<>();

        for (Post post : postBlock.getItems()) {
            PostFeedDto postDto = PostConversor.toPostFeedDto(post);
            postDtoList.add(postDto);
        }

        return new BlockDto<>(postDtoList, postBlock.getExistMoreItems());
    }


    /**
     * Update post.
     *
     * @param userId the user id
     * @param id     the id
     * @param params the params
     * @return the post dto
     * @throws InstanceNotFoundException the instance not found exception
     */
    @PutMapping("/update/{id}")
    public PostDto updatePost(@RequestAttribute Long userId, @PathVariable Long id,
                              @Validated @RequestBody UploadPostParamsDto params)

            throws InstanceNotFoundException, IOException {

        Post post = postService.updatePost(userId, id, params.getTitulo(),params.getDescripcion(),params.getAcademicYear(), params.getSubjectId());

        List<Apunte> apunteList = new ArrayList<>(post.getApuntes());
        return PostConversor.toPostDto(post, apunteList);
    }


    /**
     * Delete post.
     *
     * @param id     the id
     * @param userId the user id
     * @throws InstanceNotFoundException the instance not found exception
     */
    @PostMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id, @RequestAttribute Long userId) throws InstanceNotFoundException {
        postService.deletePost(userId, id);
    }


    /**
     * Find post by id.
     *
     * @param id the id
     * @return the post dto
     * @throws InstanceNotFoundException the instance not found exception
     */
    @GetMapping("/{id}")
    public PostDtoReturn findPostById(@PathVariable Long id,
                                      @RequestAttribute(required = false) Long userId,
                                      @RequestParam(defaultValue = "0") int page) throws InstanceNotFoundException, IOException {

        Post post = postService.findPostById(id);

        Rating rating;
        if(userId != null)
            try {
                rating = ratingService.findPostRatingbyUser(post.getId(), userId);
            } catch (NoRatingException e) {
                rating = new Rating(0L, 0, post.getUser(), post);
            }
        else rating = new Rating(0L, 0, post.getUser(), post);

        return PostConversor.toPostDtoReturn(post, RatingConversor.toRatingDto(rating));
    }

    @GetMapping("/universities")
    public List<UniversityDto> findAllUniversities() {
        return UniversityConversor.toUniversityDtos(postService.findAllUniversities());
    }

    @GetMapping("/subjects/{id}")
    public List<SubjectDto> findAllSubjects(@PathVariable Long id) {
        return SubjectConversor.toSubjectDtos(postService.findAllSubjectsByUni(id));
    }

    @PostMapping("/follow/{id}")
    public void followSubject(@PathVariable Long id, @RequestAttribute Long userId) throws InstanceNotFoundException, AlreadyFollowingException {
        postService.followSubject(userId, id);
    }

    @DeleteMapping("/unfollow/{id}")
    public void unfollowSubject(@PathVariable Long id, @RequestAttribute Long userId) throws InstanceNotFoundException, NotFollowingException {
        postService.unfollowSubject(userId, id);
    }
}
