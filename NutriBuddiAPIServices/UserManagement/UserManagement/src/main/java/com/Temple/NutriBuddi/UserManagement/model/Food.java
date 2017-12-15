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

@Entity
public class Food {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@Column(unique = true)
	private String foodName;

	private String servingUnit;

	private Integer calories;

	private Double protein;

	private Double totalFat;

	private Double satFat;

	private Double transFat;

	private Double carbohydrates;

	private Double sodium;

	private Double sugar;

	private Double fiber;

	private Double vitaminC;

	private Double vitaminD;

	private Double iron;

	private Double magnesium;

	private Double phospherous;

	private Double potassium;

	private Double zinc;

	@JsonBackReference
	@OneToMany(mappedBy = "food")
    private List<Eats> eats;

	@OneToMany(mappedBy="food")
	private List<ObjectImage> objectImages;

	public Food(){}

	public Food(String foodName, String servingUnit, int calories, double carbohydrates, double fiber, double iron, double magnesium, double phospherous,
				double potassium, double protein,	double satFat, double sodium, double sugar, double totalFat, double transFat, double vitaminC, double vitaminD, double zinc) {
		this.foodName = foodName;
		this.servingUnit = servingUnit;
		this.calories = calories;
		this.protein = protein;
		this.totalFat = totalFat;
		this.satFat = satFat;
		this.transFat = transFat;
		this.carbohydrates = carbohydrates;
		this.sodium = sodium;
		this.sugar = sugar;
		this.fiber = fiber;
		this.vitaminC = vitaminC;
		this.vitaminD = vitaminD;
		this.iron = iron;
		this.magnesium = magnesium;
		this.phospherous = phospherous;
		this.potassium = potassium;
		this.zinc = zinc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServingUnit() {
		return servingUnit;
	}

	public void setServingUnit(String servingUnit) {
		this.servingUnit = servingUnit;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	public Double getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(Double totalFat) {
		this.totalFat = totalFat;
	}

	public Double getSatFat() {
		return satFat;
	}

	public void setSatFat(Double satFat) {
		this.satFat = satFat;
	}

	public Double getTransFat() {
		return transFat;
	}

	public void setTransFat(Double transFat) {
		this.transFat = transFat;
	}

	public Double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(Double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public Double getSodium() {
		return sodium;
	}

	public void setSodium(Double sodium) {
		this.sodium = sodium;
	}

	public Double getSugar() {
		return sugar;
	}

	public void setSugar(Double sugar) {
		this.sugar = sugar;
	}

	public Double getFiber() {
		return fiber;
	}

	public void setFiber(Double fiber) {
		this.fiber = fiber;
	}

	public Double getVitaminC() {
		return vitaminC;
	}

	public void setVitaminC(Double vitaminC) {
		this.vitaminC = vitaminC;
	}

	public Double getVitaminD() {
		return vitaminD;
	}

	public void setVitaminD(Double vitaminD) {
		this.vitaminD = vitaminD;
	}

	public Double getIron() {
		return iron;
	}

	public void setIron(Double iron) {
		this.iron = iron;
	}

	public Double getMagnesium() {
		return magnesium;
	}

	public void setMagnesium(Double magnesium) {
		this.magnesium = magnesium;
	}

	public Double getPhospherous() {
		return phospherous;
	}

	public void setPhospherous(Double phospherous) {
		this.phospherous = phospherous;
	}

	public Double getPotassium() {
		return potassium;
	}

	public void setPotassium(Double potassium) {
		this.potassium = potassium;
	}

	public Double getZinc() {
		return zinc;
	}

	public void setZinc(Double zinc) {
		this.zinc = zinc;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
}
	
