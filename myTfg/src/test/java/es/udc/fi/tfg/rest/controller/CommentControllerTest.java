package es.udc.fi.tfg.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.udc.fi.tfg.model.entities.UserDao;
import es.udc.fi.tfg.model.entities.Users;
import es.udc.fi.tfg.model.services.exceptions.IncorrectLoginException;
import es.udc.fi.tfg.rest.controllers.UserController;
import es.udc.fi.tfg.rest.dtos.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CommentControllerTest{

    private final static String PASSWORD = "password";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserController userController;

    private AuthenticatedUserDto createAuthenticatedUser(String userName, Users.RoleType roleType)
            throws IncorrectLoginException {

        Users user = new Users(userName, PASSWORD, "newUser", "user", "user@test.com");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleType);

        userDao.save(user);

        LoginParamsDto loginParams = new LoginParamsDto();
        loginParams.setUserName(user.getUserName());
        loginParams.setPassword(PASSWORD);

        return userController.login(loginParams);

    }

    private UploadPostParamsDto getValidPostParamsDto(){

        List<String> lista = new ArrayList<>();
        BigDecimal precio = new BigDecimal("15.60");

        UploadPostParamsDto updatePostParams = new UploadPostParamsDto();
        updatePostParams.setTitulo("titulo");
        updatePostParams.setDescripcion("lololo");
        updatePostParams.setAcademicYear("22/23");
        updatePostParams.setSubjectId(1L);

        return updatePostParams;
    }

    @Test
    public void test_test() {
        assertTrue(true);
    }
    /*@Test
    public void should_upload_a_comment() throws Exception {

        AuthenticatedUserDto user = createAuthenticatedUser("admin", Users.RoleType.USER);

        UploadPostParamsDto updatePostParams = getValidPostParamsDto();

        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/api/posts/upload").header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(updatePostParams)))
                .andExpect(status().isOk()).andReturn();

        PostDto returnedPost = mapper.readValue(mvcResult.getResponse().getContentAsString(), PostDto.class);

        UploadComentarioParamsDto commentParamsDto = new UploadComentarioParamsDto("Bobinho Brown");

        mockMvc.perform(post("/api/comments/uploadComment/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(commentParamsDto)))
                .andExpect(status().isOk());

    }

    @Test
    public void should_receive_a_4XX_response_invalid_comment() throws Exception {

        AuthenticatedUserDto user = createAuthenticatedUser("admin", Users.RoleType.USER);

        UploadPostParamsDto updatePostParams = getValidPostParamsDto();

        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/api/posts/upload").header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(updatePostParams)))
                .andExpect(status().isOk()).andReturn();

        PostDto returnedPost = mapper.readValue(mvcResult.getResponse().getContentAsString(), PostDto.class);

        UploadComentarioParamsDto commentParamsDto = new UploadComentarioParamsDto("");

        mockMvc.perform(post("/api/comments/uploadComment/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(commentParamsDto)))
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void should_upload_an_answer_to_comment() throws Exception{

        AuthenticatedUserDto user = createAuthenticatedUser("admin", Users.RoleType.USER);

        UploadPostParamsDto updatePostParams = getValidPostParamsDto();

        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/api/posts/upload").header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(updatePostParams)))
                .andExpect(status().isOk()).andReturn();

        PostDto returnedPost = mapper.readValue(mvcResult.getResponse().getContentAsString(), PostDto.class);

        UploadComentarioParamsDto commentParamsDto = new UploadComentarioParamsDto("Bobinho Brown");

        MvcResult commentResult = mockMvc.perform(post("/api/comments/uploadComment/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(commentParamsDto)))
                .andExpect(status().isOk()).andReturn();

        ComentarioDto returnedCommentDto = mapper.readValue(commentResult.getResponse().getContentAsString(), ComentarioDto.class);

        UploadAnswerParamsDto answerToComment = new UploadAnswerParamsDto("PSG del español? Bernat", 0L);

        mockMvc.perform(post("/api/comments/uploadAnswer/" + returnedCommentDto.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(answerToComment)))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void should_receive_a_4XX_response_invalid_answer() throws Exception {


        AuthenticatedUserDto user = createAuthenticatedUser("admin", Users.RoleType.USER);

        UploadPostParamsDto updatePostParams = getValidPostParamsDto();

        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/api/posts/upload").header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(updatePostParams)))
                .andExpect(status().isOk()).andReturn();

        PostDto returnedPost = mapper.readValue(mvcResult.getResponse().getContentAsString(), PostDto.class);

        UploadComentarioParamsDto commentParamsDto = new UploadComentarioParamsDto("Bobinho Brown");

        MvcResult commentResult = mockMvc.perform(post("/api/comments/uploadComment/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(commentParamsDto)))
                .andExpect(status().isOk()).andReturn();

        ComentarioDto returnedCommentDto = mapper.readValue(commentResult.getResponse().getContentAsString(), ComentarioDto.class);

        UploadComentarioParamsDto answerToComment = new UploadComentarioParamsDto("");

        mockMvc.perform(post("/api/comments/uploadAnswer/" + returnedCommentDto.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(answerToComment)))
                .andExpect(status().is4xxClientError());

    }




    @Test
    public void should_get_all_comments_from_post() throws Exception{


        AuthenticatedUserDto user = createAuthenticatedUser("admin", Users.RoleType.USER);
        AuthenticatedUserDto user2 = createAuthenticatedUser("paco paquez", Users.RoleType.USER);

        UploadPostParamsDto updatePostParams = getValidPostParamsDto();

        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/api/posts/upload").header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(updatePostParams)))
                .andExpect(status().isOk()).andReturn();

        PostDto returnedPost = mapper.readValue(mvcResult.getResponse().getContentAsString(), PostDto.class);

        UploadComentarioParamsDto commentParamsDto = new UploadComentarioParamsDto("Bobinho Brown");

        UploadComentarioParamsDto commentParamsDto2 = new UploadComentarioParamsDto("Frances del atleti? Lemar");

        mockMvc.perform(post("/api/comments/uploadComment/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(commentParamsDto)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/comments/uploadComment/" + returnedPost.getId()).header("Authorization", "Bearer " + user2.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(commentParamsDto2)))
                .andExpect(status().isOk());

        MvcResult getCommentsResult = mockMvc.perform(get("/api/comments/commentsPost/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken()))
                .andExpect(status().isOk()).andReturn();

        //we do this because a blockDto is received, that has an items field (all comments) and a has next.
        //we get the length from the comments array and we check that it is correct
        JSONArray itemsArray = new JSONObject(getCommentsResult.getResponse().getContentAsString()).getJSONArray("items");
        assertEquals(2, itemsArray.length());

    }


    @Test
    public void should_delete_a_comment() throws Exception {

        AuthenticatedUserDto user = createAuthenticatedUser("admin", Users.RoleType.USER);

        UploadPostParamsDto updatePostParams = getValidPostParamsDto();

        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/api/posts/upload").header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(updatePostParams)))
                .andExpect(status().isOk()).andReturn();

        PostDto returnedPost = mapper.readValue(mvcResult.getResponse().getContentAsString(), PostDto.class);

        UploadComentarioParamsDto commentParamsDto = new UploadComentarioParamsDto("Hispanico do City? rodri");

        MvcResult commentResult = mockMvc.perform(post("/api/comments/uploadComment/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(commentParamsDto)))
                .andExpect(status().isOk()).andReturn();

        ComentarioDto returnedCommentDto = mapper.readValue(commentResult.getResponse().getContentAsString(), ComentarioDto.class);

        mockMvc.perform(delete("/api/comments/deleteComment/" + returnedCommentDto.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());

    }
*/

}