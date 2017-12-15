package com.Temple.NutriBuddi.UserManagement.repository;

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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private ArrayList<User> users;
    private User expectedUser;
    private static Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Before
    public void setup(){
        expectedUser = new User("test1@test.com", "asdfjkl;", "username1", "boo",
                                "blah", 5, 123, 26, "N");
        //Populate db entries to insert
        users = new ArrayList<>();
        users.add(new User("something@23532.com", "qwerty;", "something", "why",
                           "what", 13, 200, 53, "F"));
        users.add(expectedUser);
        users.add(new User("deleteme@23532.com", "lkljfsfdg;", "llaf", "why",
                "what", 13, 200, 53, "B"));
        userRepository.save(users);
    }

    @Test
    public void testFindByEmail() throws Exception {
        User actualUser = userRepository.findByEmail(expectedUser.getEmail());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }

    @Test
    public void testFindById() throws Exception {
        User expected = userRepository.findByEmail("test1@test.com");
        User actual = userRepository.findById(expected.getId());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void testFindByEmailAndPassword() throws Exception {
        User actualUser = userRepository.findByEmailAndPassword("test1@test.com", "asdfjkl;");
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }
    
    @Test
    public void testDeleteByEmail() throws Exception {
        Long response = userRepository.deleteByEmail("deleteme@23532.com");
        assert(response == 1);
    }

}