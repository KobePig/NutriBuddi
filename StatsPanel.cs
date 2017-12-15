using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class StatsPanel : MonoBehaviour {
		public Slider caloriesSlider;
        public Text caloriesValueText;
        public Text caloriesPercentText;


        public Slider proteinSlider;
        public Text proteinValueText;
        public Text proteinPercentText;



        public Slider carbsSlider;
        public Text carbsValueText;
        public Text carbsPercentText;


        public Slider sodiumSlider;
        public Text sodiumValueText;
        public Text sodiumPercentText;


        public Slider totalFatSlider;
        public Text totalFatValueText;
        public Text totalFatPercentText;
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    void updateStatsPanel(){
        APICall.eatTransaction[] userEatsTransactions= UserSession.session.sessionAllUserEats;

		//We're able to do this because at this point we know the User has a goal(the null check was earlier on click of the Journal Panel button)
		APICall.userGoal currentUserGoalTotals = new APICall.userGoal(
			UserSession.session.sessionUserGoal.protein ,
			UserSession.session.sessionUserGoal.carbs ,
			UserSession.session.sessionUserGoal.calories ,
			UserSession.session.sessionUserGoal.sodium ,
			UserSession.session.sessionUserGoal.totalFat
		);

		APICall.userGoal currentUserGoal = UserSession.session.sessionUserGoal; 

		APICall.userGoal myUserTotals = new APICall.userGoal();

		EpochConverter myEpochConverter = new EpochConverter();
        
            foreach(APICall.eatTransaction eats in userEatsTransactions){

                // bool test = myEpochConverter.isToday(eats.date);
                // Debug.Log(test);
                if(myEpochConverter.isToday(eats.date)){
                    myUserTotals.calories += (eats.food.calories * eats.numServings);
                    myUserTotals.carbs += (eats.food.carbohydrates * eats.numServings);
                    myUserTotals.protein += (eats.food.protein * eats.numServings);
                    myUserTotals.sodium += (eats.food.sodium * eats.numServings);
                    myUserTotals.totalFat += (eats.food.totalFat * eats.numServings);
                }
            }
            float caloriesPercent = (float)(100.0/(currentUserGoal.calories/myUserTotals.calories));
            // caloriesSlider.value = (caloriesPercent);
            // caloriesValueText.text = myUserTotals.calories +"/" + currentUserGoal.calories;
            // caloriesPercentText.text = caloriesPercent + "%";
            caloriesSlider.value = (caloriesPercent);
            caloriesValueText.text = myUserTotals.calories +"/" + currentUserGoal.calories;
            caloriesPercentText.text = caloriesPercent + "%";

            //caloriesSlider.value = 15;
            float carbsPercent = (float)(100.0/(currentUserGoal.carbs/myUserTotals.carbs));
            carbsSlider.value = (carbsPercent);
            carbsValueText.text = myUserTotals.carbs +"/" + currentUserGoal.carbs;
            carbsPercentText.text = carbsPercent + "%";

            float proteinPercent = (float)(100.0/(currentUserGoal.protein/myUserTotals.protein));
            proteinSlider.value = (proteinPercent);
            proteinValueText.text = myUserTotals.protein + "/" + currentUserGoal.protein;
            proteinPercentText.text = proteinPercent + "%";

            float sodiumPercent = (float)(100.0/(currentUserGoal.sodium/myUserTotals.sodium));
            sodiumSlider.value = (sodiumPercent);
            sodiumValueText.text = myUserTotals.sodium +"/" + currentUserGoal.sodium;
            sodiumPercentText.text = sodiumPercent + "%";

            float totalFatPercent = (float)(100.0/(currentUserGoal.totalFat/myUserTotals.totalFat));
            totalFatSlider.value = (totalFatPercent);
            totalFatValueText.text = myUserTotals.totalFat+"/" + currentUserGoal.totalFat;
            totalFatPercentText.text = totalFatPercent + "%";
    }
}
