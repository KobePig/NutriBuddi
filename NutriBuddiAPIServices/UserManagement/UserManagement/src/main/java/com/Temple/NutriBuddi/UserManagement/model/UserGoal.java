package com.Temple.NutriBuddi.UserManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user_goal")
public class UserGoal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

   	private int protein;
    private int carbs;
    private int calories;
    private int sodium;
    private int totalFat;
    
    //0 : gain weight
    //1 : lose weight
    //2 : maintain weight
    //>=3 : no goal set
    private int weightGoal;
    
	@JsonManagedReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public UserGoal(){}

    public UserGoal(User user, int carbs, int protein, int calories, int sodium, int totalFat, int weightGoal){
        this.user = user;
        this.carbs = carbs;
        this.protein = protein;
        this.calories = calories;
        this.sodium = sodium;
        this.totalFat = totalFat;
        this.weightGoal = weightGoal;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getSodium() {
		return sodium;
	}

	public void setSodium(int sodium) {
		this.sodium = sodium;
	}
	
	public int getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(int totalFat) {
		this.totalFat = totalFat;
	}
	
   	public int getWeight() {
		return weightGoal;
	}

	public void setWeight(int weightGoal) {
		this.weightGoal = weightGoal;
	}
   
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
