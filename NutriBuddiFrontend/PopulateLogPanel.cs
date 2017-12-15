using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PopulateLogPanel : MonoBehaviour {

	public GameObject foodLogPanel;

	public Text FoodTypeText;
	public Text DateText;
	public Text CaloriesText;
	public Text CarbsText;
	public Text SodiumText;
	public Text ProteinText;
	public Text TotalFatText;

	
	// Use this for initialization
	void Start () {
//		Invoke(putFoodsInPanel , 20f);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	public void putFoodsInPanel(){

            EpochConverter myEpochConverter = new EpochConverter();

                // if(myEpochConverter.isToday(UserSession.session.allUserEats[0].date)){
				// 	FoodTypeText.text = UserSession.session.allUserEats[0].food.foodName;
				// 	CaloriesText.text = UserSession.session.allUserEats[0].food.calories.ToString();
				// 	CarbsText.text = UserSession.session.allUserEats[0].food.carbohydrates.ToString();
				// 	SodiumText.text = UserSession.session.allUserEats[0].food.sodium.ToString();
				// 	ProteinText.text = UserSession.session.allUserEats[0].food.protein.ToString();
				// 	TotalFatText.text = UserSession.session.allUserEats[0].food.totalFat.ToString();
                // }
				foreach(APICall.eatTransaction eatTransaction in UserSession.session.sessionAllUserEats){
					if(myEpochConverter.isToday(eatTransaction.date)){
						FoodTypeText.text = eatTransaction.food.foodName;
						CaloriesText.text = eatTransaction.food.calories.ToString();
						CarbsText.text = eatTransaction.food.carbohydrates.ToString();
						SodiumText.text = eatTransaction.food.sodium.ToString();
						ProteinText.text = eatTransaction.food.protein.ToString();
						TotalFatText.text = eatTransaction.food.totalFat.ToString();
					}

				}
                // if(myEpochConverter.isToday(UserSession.session.sessionAllUserEats[0].date)){
				// 	FoodTypeText.text = UserSession.session.sessionAllUserEats[0].food.foodName;
				// 	CaloriesText.text = UserSession.session.sessionAllUserEats[0].food.calories.ToString();
				// 	CarbsText.text = UserSession.session.sessionAllUserEats[0].food.carbohydrates.ToString();
				// 	SodiumText.text = UserSession.session.sessionAllUserEats[0].food.sodium.ToString();
				// 	ProteinText.text = UserSession.session.sessionAllUserEats[0].food.protein.ToString();
				// 	TotalFatText.text = UserSession.session.sessionAllUserEats[0].food.totalFat.ToString();
                // }
	} 
}
