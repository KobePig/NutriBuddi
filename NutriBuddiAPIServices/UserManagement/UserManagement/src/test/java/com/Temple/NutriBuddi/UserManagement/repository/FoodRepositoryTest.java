package com.Temple.NutriBuddi.UserManagement.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class FoodRepositoryTest {
	@Test
	public void dummyTest() throws Exception {
        assert(true);
    }
}