package com.Temple.NutriBuddi.UserManagement.repository;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.Temple.NutriBuddi.UserManagement.model.Food;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.Temple.NutriBuddi.UserManagement.model.User;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class EatsRepositoryTest {
	
    private static Logger log = LoggerFactory.getLogger(EatsRepositoryTest.class);

    @Autowired
	private UserRepository userRepository;
    @Autowired
    private FoodRepository foodRespository;
	@Autowired
    private EatsRepository eatsRepository;

	private String testEmail = "test@testing.com";
	private String testFood = "wapple";
	private String testEmail2 = "pencilpusher@staples.com";
	private String testFood2 = "planck's peach";

//	private User user;
//	private Food food;
	private Eats eat;
	private List<User> users;
	private List<Food> foods;
	private List<Eats> eats;

	@Before
	public void setUp() throws Exception {
		users = new ArrayList<>();
		//Insert test data into user repository
		users.add(new User(testEmail, "thisisatest;", "test1", "boo",
				"blah", 5, 180, 30, "F"));
		users.add(new User(testEmail2, "thisisnotatest;", "pencilpusher", "john",
				"wick", 6, 180, 42, "M"));
		userRepository.save(users);


		Integer num1 = new Integer(23);
		Integer num2 = new Integer(1);

		//Insert test data for food repository
		foods = new ArrayList<>();
		foods.add(new Food(testFood, "2", 100, 100, num2, num2, num2, num2, num2, num2,
				num2, num2, num2, num2, num2, num2, num2, num2));
		foods.add(new Food(testFood2, "1", 150, 100, num1, num2, num2, num2, num2, num2,
				num2, num2, num2, num2, num2, num2, num2, num2));
		foodRespository.save(foods);



		Date d1 = Date.valueOf("2017-10-17");
		Date d2 = Date.valueOf("2017-11-6");
		System.out.println("date2: " + d2.toString());

//		LocalDateTime dateTime = LocalDateTime.of(2017, 10, 17, 10, 50, 20, 0);
//		Date date =  Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
//		Date d1 = df.parse(df.format(date));
//
//		LocalDate localDate = LocalDate.of(2017, Month.NOVEMBER, 6);
//		Date date2 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		Date d2 = df.parse(df.format(date2));

		//Insert test data for eats repository
		eats = new ArrayList<>();
		eat = new Eats(users.get(0), 17, foods.get(0));
		eats.add(new Eats(users.get(0), 17, foods.get(0), d1));
		eats.add(new Eats(users.get(0), 10, foods.get(1), d2));
		eats.add(new Eats(users.get(1), 10, foods.get(0)));
		eatsRepository.save(eats);
	}

	@Test
	public void testFindByUserId() {
		//Find user by unique email in user repository
		User user = userRepository.findByEmail(testEmail);
		//Use the user's id to find the list of eaten records
		List<Eats> response = eatsRepository.findByUserId(user.getId());
        log.info("findByUserId Response: " + response);
        log.info("response id: " + response.get(0).getId() + "user id: " + user.getId());
        assert(response.get(0).getUser().getId() == user.getId());
        assert(response.get(0).getNumServings() == 17);

	}
	
	@Test
	public void testFindByFoodId() {
		//Find food by unique food name in food repository
		Food food = foodRespository.findByFoodName(testFood);
		//Use food id to find food item in list of eaten records
		List<Eats> response = eatsRepository.findByFoodId(food.getId());
        log.info("findByFoodId Response: " + response);
        assert(response.get(0).getFood().getProtein() == 1);
        assert(response.get(0).getFood().getFoodName().equals(testFood));

	}

	@Test
	public void testFindByFoodName(){
		Food food = foodRespository.findByFoodName(testFood);
		List<Eats> response = eatsRepository.findByFoodName(testFood);
		log.info("findByFoodName Response id: " + response.get(0).getId());
		assert(food.getId() == response.get(0).getFood().getId());
		assertNotNull(response);
		assertTrue("List size is greater than 0",response.size() > 0);

	}

	@Test
	public void testFindByFoodNameWithExampleMatchers(){
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id")
				.withIgnorePaths("transactionDate");
		Example<Eats> example = Example.of(eat, matcher);
		List<Eats> response =  eatsRepository.findByFoodName(testFood);
		Eats responseExample = Example.of(response.get(0), matcher).getProbe();
		log.info("response: " + responseExample.toString());
		log.info("repo: " + eatsRepository.findOne(example));
		assert(eatsRepository.findOne(example).equals(responseExample));
	}

	@Test
	public void testFindBetweenDateRangeAndName(){
		ArrayList<Eats> arrayList = (ArrayList<Eats>) eatsRepository.findAll();
		for(Eats e: arrayList){
			log.info(e.toString());
		}
		List<Eats> response = eatsRepository.findBetweenDateRangeAndEmail("2017/10/5", "2017/11/6", testEmail);
		log.info("response:" + response.get(0).toString());
		assertEquals(2, response.size());
	}

}
