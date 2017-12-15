using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class NutritionPopulate : MonoBehaviour {

[System.Serializable]
    public class userNutrition
	{
		public string foodName;
        public int calories;
		public float totalFat;
		public float satFat;
		public float transFat;
		public float sodium;
		public float carbohydrates;
		public float fiber;
		public float sugar;
		public float protein;
		public float vitaminC;
		public float vitaminD;
		public float iron;
		public float magnesium;
		public float phospherous;
		public float potassium;
		public float zinc;

        public static userNutrition CreateFromJSON(string jsonString){
            return JsonUtility.FromJson<userNutrition>(jsonString);
        }
    }

	public Text foodName;
	public Text calories;
	public Text TotalFat;
	public Text SaturatedFat;
	public Text TextTransFat;
	public Text Sodium;
	public Text Carbs;
	public Text Fiber;
	public Text Sugar;
	public Text Protein;
	public Text vitC;
	public Text vitD;
	public Text Iron;
	public Text Magnesium;
	public Text phospherous;
	public Text potassium;
	public Text zinc;

	//need the public string for the food
	//foodName=food example: foodName=apple
	//This will be in another script that i will attach to the foodInfoCanvas
	public string foodInfoURL ="http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1."+
								"elasticbeanstalk.com//food/getFoodNutrition?foodName=";
								
	//UserSession.session.currentClassifiedFood <<Will be used to get the info of current food
	// Use this for initialization
	void Start () 
	{
		//This is where ill populte
		BuildWWW myRequest = new BuildWWW();
		Debug.Log ("before couroutine");
        StartCoroutine(startMyComplicatedTask(myRequest.BuildRequest(foodInfoURL+UserSession.session.currentClassifiedFood)));
		Debug.Log ("after couroutine");
	}
	
	IEnumerator startMyComplicatedTask(WWW builtRequest){
        yield return builtRequest;

		Debug.Log ("inside couroutine");
        //this is where you do all the complicated stuff like normal(if you actually need to do anything after the call(ex: check for 404 error))
        Debug.Log(builtRequest.text);

		userNutrition nutrition = new userNutrition();
		//nutrition.CreateFromJSON(builtRequest.text);
		nutrition = userNutrition.CreateFromJSON(builtRequest.text);

		foodName.text = nutrition.foodName.ToString();
		calories.text = nutrition.calories.ToString();
		TotalFat.text = nutrition.totalFat.ToString();
		SaturatedFat.text = nutrition.satFat.ToString();
		TextTransFat.text = nutrition.transFat.ToString();
		Sodium.text = nutrition.sodium.ToString();
		Carbs.text = nutrition.carbohydrates.ToString();
		Fiber.text = nutrition.fiber.ToString();
		Sugar.text = nutrition.sugar.ToString();
		Protein.text = nutrition.protein.ToString();
		vitC.text = nutrition.vitaminC.ToString();
		vitD.text = nutrition.vitaminD.ToString();
		Iron.text = nutrition.iron.ToString();
		Magnesium.text = nutrition.magnesium.ToString();
		phospherous.text = nutrition.phospherous.ToString();
		potassium.text = nutrition.potassium.ToString();
		zinc.text = nutrition.zinc.ToString();


    }
	// Update is called once per frame
	void Update () {
		
	}
}
