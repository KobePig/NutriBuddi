package com.Temple.NutriBuddi.UserManagement.model;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
 	@Column(unique = true)
    private String email;
    
    private String password;

    private String userName;
    
    private String firstName;
    
    private String lastName;
    
    private int height;
    
    private int weight;
    
    private int age;
    
    private String gender;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Eats> eats;

	@JsonBackReference
	@OneToMany(mappedBy = "user")
    private List<UserGoal> userGoals;

	public User(){}

	public User(String email, String password, String userName, String firstName, String lastName, int height, int weight, int age, String gender) {
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.gender = gender;
	}

	public User(String email, String password, int height, int weight, int age, String gender) {
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.gender = gender;
	}


	public User(String email, String password, String userName, String firstName, String lastName, int height, int weight, int age, String gender, List<Eats> eats, List<UserGoal> userGoals) {
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.gender = gender;
		this.eats = eats;
		this.userGoals = userGoals;
	}

	public int getId() {
 		return id;
 	}

 	public void setId(int id) {
 		this.id = id;
 	}

 	public List<Eats> getEats() {
 		return eats;
 	}

 	public void setEats(List<Eats> eats) {
 		this.eats = eats;
 	}
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserGoal> getUserGoals() {
		return userGoals;
	}

	public void setUserGoals(List<UserGoal> userGoals) {
		this.userGoals = userGoals;
	}
}
