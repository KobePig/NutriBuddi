package com.Temple.NutriBuddi.UserManagement.repository;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.model.Image;
import com.Temple.NutriBuddi.UserManagement.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ImageRepositoryTest {
    private static Logger log = LoggerFactory.getLogger(ImageRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private FoodRepository foodRespository;
    @Autowired
    private EatsRepository eatsRepository;

    private String testEmail = "test@testing.com";
    private String testFood = "Irradiated Pudding";
    private String testFileName = "puddingdust.png";
    private int testNumServings = 5;
    private String testEmail2 = "pencilpusher@staples.com";
    private String testFood2 = "puddlewater";
    private String testFileName2 = "puddles.jpg";
    private int testNumServings2 = 1;
    private String testFood3 = "swoosh";
    private String testFileName3 = "swoosh.jpg";
    private int testNumServings3 = 1;
    private List<User> users;
    private List<Image> images;
    private List<Food> foods;
    private List<Eats> eats;
    private List<Image> expectedImagesByEmail;

    @Before
    public void setUp() throws Exception {
        users = new ArrayList<>();
        //Insert test data into user repository
        users.add(new User(testEmail, "thisisatest;", "test1", "boo",
                "blah", 5, 180, 30, "N"));
        users.add(new User(testEmail2, "thisisnotatest;", "pencilpusher", "john",
                "wick", 6, 180, 42, "M"));
        userRepository.save(users);

        images = new ArrayList<>();
        Image img1 = new Image(testFood, testFileName);
        Image img3 = new Image(testFood3, testFileName3);
        images.add(img1);
        images.add(new Image(testFood2, testFileName2));
        images.add(img3);
        imageRepository.save(images);

        foods = new ArrayList<>();
        foods.add(new Food(testFood, "g", 58, 10, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0));
        foods.add(new Food(testFood2, "g", 100, 0, 0, 100, 100, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        foods.add(new Food(testFood3, "g", 125, 12, 12, 100, 100, 100, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        foodRespository.save(foods);

        eats = new ArrayList<>();
        eats.add(new Eats(users.get(0), testNumServings, foods.get(0),  images.get(0)));
        eats.add(new Eats(users.get(1), testNumServings2, foods.get(1),  images.get(1)));
        eats.add(new Eats(users.get(0), testNumServings3, foods.get(2),  images.get(2)));
        eatsRepository.save(eats);

        expectedImagesByEmail = new ArrayList<>();
        expectedImagesByEmail.add(img1);
        expectedImagesByEmail.add(img3);

    }

    @Test
    public void findByFileName() throws Exception {
        Image image = imageRepository.findByFileName(testFileName);
        assertEquals(image, images.get(0));
    }

    @Test
    public void findByEmail() throws Exception {
        List<Image> image = imageRepository.findByEmail(testEmail);
        assertEquals(image.size(),  expectedImagesByEmail.size());
        int i = 0;
        for(Image img: image){
            assertEquals(img, expectedImagesByEmail.get(i));
            i++;
        }
    }

    @Test
    public void deleteByFileName() throws Exception {

    }

}