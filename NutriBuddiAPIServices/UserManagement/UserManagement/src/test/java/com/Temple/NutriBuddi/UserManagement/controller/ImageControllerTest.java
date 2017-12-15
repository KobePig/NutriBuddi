package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.UserManagementApplication;
import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.model.User;
import com.Temple.NutriBuddi.UserManagement.repository.EatsRepository;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = UserManagementApplication.class
)
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EatsRepository eatsRepository;

    private String authorization;
    private String testEmail1;
    private String testFood1;
    private String unknownFood;
    private Logger log = LoggerFactory.getLogger(ImageControllerTest.class);


    @Before
    public void setUp() throws Exception {
        authorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        testEmail1 = "jUnitTester@tester.com";
        User user = new User(testEmail1, "qualitypasssword;","username1", "boo","blah", 5, 123, 26, "M");
        userRepository.save(user);
        testFood1 = "quantumKumquat";
        unknownFood = "somethingsomething";
        Food quantumKumquat = new Food(testFood1, "mg", 150, 2, 3, 4,5, 6, 7, 21, 23, 99, 53, 15, 10, 10, 69, 12);
        foodRepository.save(quantumKumquat);
    }

    @Test
    public void addNewImage() throws Exception {
        String response = mockMvc.perform(get("/imageClassifier/addNewImage")
                .header("Authorization", authorization)
                .param("email", testEmail1)
                .param("foodName", testFood1)
                .param("fileName", "ChihuahuaOrMuffin")
                .param("numServing", "100")
                .param("latitude", "")
                .param("longitude", ""))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("response: " + response);
    }

    @Test
    public void addNewImageWithLocation() throws Exception {
        String response = mockMvc.perform(get("/imageClassifier/addNewImage")
                .header("Authorization", authorization)
                .param("email", testEmail1)
                .param("foodName", testFood1)
                .param("fileName", "ChihuahuaOrMuffin")
                .param("numServing", "100")
                .param("latitude", "73293.2323")
                .param("longitude", "123432.32"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("response: " + response);
    }

    @Test
    public void addNewImageWithUnknownFood() throws Exception {
        String response = mockMvc.perform(get("/imageClassifier/addNewImage")
                .header("Authorization", authorization)
                .param("email", testEmail1)
                .param("foodName", unknownFood)
                .param("fileName", "i have no clue what this abomination is")
                .param("numServing", "100")
                .param("latitude", "23192.23")
                .param("longitude", "12432.32"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("response: " + response);
    }

    @Test
    public void deleteImageResource() throws Exception {
        addNewImage();
        String response = mockMvc.perform(get("/imageClassifier/deleteImage")
                .header("Authorization", authorization)
                .param("email", testEmail1)
                .param("fileName", "ChihuahuaOrMuffin"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("response: " + response);
    }

    @Test
    public void updateImageResource() throws Exception {
        addNewImage();
        String response = mockMvc.perform(get("/imageClassifier/updateImage")
                .header("Authorization", authorization)
                .param("email", testEmail1)
                .param("oldFileName", "ChihuahuaOrMuffin")
                .param("newFileName", "MuffinOrChihuahua")
                .param("latitude", "")
                .param("longitude", ""))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("response: " + response);
    }

    @Test
    public void queryForImageResourceByEmail() throws Exception {
        addNewImage();
        String response = mockMvc.perform(get("/imageClassifier/findImagesByEmail")
                .header("Authorization", authorization)
                .param("email", testEmail1))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("response: " + response);
    }
}