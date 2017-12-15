package com.Temple.NutriBuddi.UserManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Eats {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private Integer numServings;

    /*
     * 0 for no
     * 1 for yes
     * 2 for unassigned
     * optional
     */
    private Integer classificationValidity;

    @Column(name = "transaction_date", columnDefinition="DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    public Eats(){}

    public Eats(User user, int numServings, Food food) {
    	this.user = user;
    	this.numServings = numServings;
    	this.food = food;
    	this.transactionDate = new Date();
    }

    public Eats(User user, int numServings, Food food, Image image) {
        this.user = user;
        this.numServings = numServings;
        this.food = food;
        this.transactionDate = new Date();
        this.image = image;

    }

    public Eats(User user, int numServings, Food food, Image image, int classificationValidity) {
        this.user = user;
        this.numServings = numServings;
        this.food = food;
        this.transactionDate = new Date();
        this.image = image;
        this.classificationValidity = classificationValidity;

    }

    public Eats(User user, int numServings, Food food, Date date) {
        this.user = user;
        this.numServings = numServings;
        this.food = food;
        this.transactionDate = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumServings() {
		return numServings;
	}

	public void setNumServings(Integer numServings) {
		this.numServings = numServings;
	}

    public Date getDate() {
        return transactionDate;
    }

    public void setDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Eats{" +
                "id=" + id +
                ", numServings=" + numServings +
                ", transactionDate=" + transactionDate +
                ", user=" + user +
                ", food=" + food +
                '}';
    }
}
