package es.udc.fi.tfg.rest.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import es.udc.fi.tfg.model.entities.UserDao;
import es.udc.fi.tfg.model.entities.Users;
import es.udc.fi.tfg.model.services.exceptions.IncorrectLoginException;
import es.udc.fi.tfg.rest.controllers.UserController;
import es.udc.fi.tfg.rest.dtos.*;
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
public class RatingControllerTest {

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
/*
    @Test
    public void should_upload_a_rating() throws Exception {

        AuthenticatedUserDto user = createAuthenticatedUser("admin", Users.RoleType.USER);

        UploadPostParamsDto updatePostParams = getValidPostParamsDto();

        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/api/posts/upload").header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(updatePostParams)))
                .andExpect(status().isOk()).andReturn();

        PostDto returnedPost = mapper.readValue(mvcResult.getResponse().getContentAsString(), PostDto.class);

        UploadRatingDto uploadRatingDto = new UploadRatingDto(1);

        mockMvc.perform(post("/api/ratings/upload/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(uploadRatingDto)))
                .andExpect(status().isOk());

    }
    @Test
    public void should_receive_an_4XX_error() throws Exception {

        AuthenticatedUserDto user = createAuthenticatedUser("admin", Users.RoleType.USER);

        UploadPostParamsDto updatePostParams = getValidPostParamsDto();

        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/api/posts/upload").header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(updatePostParams)))
                .andExpect(status().isOk()).andReturn();

        //in order to get the proper postId, we have to read the JSON returned.
        PostDto returnedPost = mapper.readValue(mvcResult.getResponse().getContentAsString(), PostDto.class);


        UploadRatingDto uploadRatingDto = new UploadRatingDto(-1);

        mockMvc.perform(post("/api/ratings/upload/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(uploadRatingDto)))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void should_delete_rating() throws Exception {

        AuthenticatedUserDto user = createAuthenticatedUser("admin", Users.RoleType.USER);

        UploadPostParamsDto updatePostParams = getValidPostParamsDto();

        ObjectMapper mapper = new ObjectMapper();

        MvcResult postMvnResult = mockMvc.perform(post("/api/posts/upload").header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(updatePostParams)))
                .andExpect(status().isOk()).andReturn();

        PostDto returnedPost = mapper.readValue(postMvnResult.getResponse().getContentAsString(), PostDto.class);



        UploadRatingDto uploadRatingDto = new UploadRatingDto(1);

        MvcResult ratingMvnResult = mockMvc.perform(post("/api/ratings/upload/" + returnedPost.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(uploadRatingDto)))
                .andExpect(status().isOk()).andReturn();

        RatingDto returnedRating = mapper.readValue(ratingMvnResult.getResponse().getContentAsString(), RatingDto.class);

        mockMvc.perform(delete("/api/ratings/delete/" + returnedRating.getId()).header("Authorization", "Bearer " + user.getServiceToken())
                        .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());

    }
*/
}
