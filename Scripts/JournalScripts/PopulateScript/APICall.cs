using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class APICall : MonoBehaviour {
//public class APICall {

public string userGoalURL= "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/userGoal/getUserGoals?";
public string userEatsURL= "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/eats/getEatsByEmail?";

public GameObject StatsPanel;
public GameObject NoGoalPanel;

	// Use this for initialization
	void Start () {
        totalGoalValues userTotals = new totalGoalValues();
        //startRetrieveData(userTotals);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    // public class healthSliders{
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

    // }

    public class totalGoalValues{

        public float protein;
        public float carbs;
        public float calories;
        public float sodium;
        public float totalFat;

        public totalGoalValues(){
            this.protein = 0;
            this.carbs= 0;
            this.calories = 0;
            this.sodium = 0;
            this.totalFat = 0;
        }
        public totalGoalValues(float startingProtein , float startingCarbs , float startingCalories , float startingSodium , float startingTotalFat){
            this.protein = startingProtein;
            this.carbs= startingCarbs;
            this.calories = startingCalories;
            this.sodium = startingSodium;
            this.totalFat = startingTotalFat;
        }

    }

    

    [System.Serializable]
    public class userGoal{
        public int id;
        public float protein;
        public float carbs;
        public float calories;
        public float sodium;
        public float totalFat;

        public static userGoal CreateFromJSON(string jsonString){
            return JsonUtility.FromJson<userGoal>(jsonString);
        }

        public userGoal(){
            this.protein = 0;
            this.carbs= 0;
            this.calories = 0;
            this.sodium = 0;
            this.totalFat = 0;
        }
        public userGoal(float startingProtein , float startingCarbs , float startingCalories , float startingSodium , float startingTotalFat){
            this.protein = startingProtein;
            this.carbs= startingCarbs;
            this.calories = startingCalories;
            this.sodium = startingSodium;
            this.totalFat = startingTotalFat;
        }
    }

    [System.Serializable]
    public class User
    {
        public int id;
        public string email;
        public string password;
        public string userName;
        public string firstName;
        public string lastName;
        public int height;
        public int weight;
        public int age;
        public int gender;
    }

    [System.Serializable]
    public class Food
    {
        public int id;
        public string foodName;
        public string servingUnit;
        public int calories;
        public int protein;
        public int totalFat;
        public int satFat;
        public int transFat;
        public int carbohydrates;
        public int sodium;
        public int sugar;
        public int fiber;
        public int vitaminC;
        public int vitaminD;
        public int iron;
        public int magnesium;
        public int phospherous;
        public int potassium;
        public int zinc;
    }

    [System.Serializable]
    public class eatTransaction {
        public int id;
        public int numServings;
        public User user;
        public Food food;
        public long date;

            public static eatTransaction CreateFromJSON(string jsonString){
                return JsonUtility.FromJson<eatTransaction>(jsonString);
            }
    }

    [System.Serializable]
    public class JsonWrapper{

        public eatTransaction[] allEats;
        public static JsonWrapper CreateFromJSON(string jsonString){
            return JsonUtility.FromJson<JsonWrapper>(jsonString);
        }
    }

// public static class JsonHelper
// {
//     public static T[] FromJson<T>(string json)
//     {
//         Wrapper<T> wrapper = JsonUtility.FromJson<Wrapper<T>>(json);
//         return wrapper.Items;
//     }
//     [System.Serializable]
//     private class Wrapper<T>
//     {
//         public T[] Items;
//     }
// }



//         // Given JSON input:
//         // {"name":"Dr Charles","lives":3,"health":0.8}
//         // this example will return a PlayerInfo object with
//         // name == "Dr Charles", lives == 3, and health == 0.8f.



    //public void startRetrieveData(totalGoalValues t){
    public void startRetrieveData(){
       //StartCoroutine(StartGoalsRequest(t));
        string goalsURL = userGoalURL+"email="+UserSession.session.userEmailAddress;
	
		//string url = userGoalURL+"email="+"test@test.com";	//Testing URL

        BuildWWW myRequest = new BuildWWW();
        Debug.Log("Made it to startRetrieveData");
        StartCoroutine(getUserGoal(myRequest.BuildRequest(goalsURL)));
    }


    // IEnumerator StartGoalsRequest(totalUserEats t)  {


	
    //     string url = userGoalURL+"email="+UserSession.session.userEmailAddress;
	
	// 	//string url = userGoalURL+"email="+"test@test.com";	//Testing URL

    //     BuildWWW myRequest = new BuildWWW();

    //     StartCoroutine(getUserGoal(myRequest.BuildRequest(url)));

    // }
    IEnumerator getUserGoal(WWW builtRequest){
        Debug.Log("Made it to getUserGoal");
        yield return builtRequest;
        Debug.Log("Made it to getUserGoal2");

        userGoal retrievedUserGoal = userGoal.CreateFromJSON(builtRequest.text);

        UserSession.session.sessionUserGoal = retrievedUserGoal;
        
        // if(retrievedUserGoal.calories == 928049){
        //     Debug.Log("WHY YOU HERE BRO");
        //     UserSession.session.callDone = true; //call will finish here
        //     //User doesn't have a goal
        //     // StatsPanel.SetActive(false);
        //     // NoGoalPanel.SetActive(true);
        // }
        // else{
        //     // StatsPanel.SetActive(true);
        //     // NoGoalPanel.SetActive(false);
        //     //StartCoroutine(StartEatsRequest(retrievedUserGoal))
        //     string eatsURL = userEatsURL+"email="+UserSession.session.userEmailAddress;

        //     BuildWWW myEatsRequest = new BuildWWW();
        //     Debug.Log("Finished getUserGoal");
        //     StartCoroutine(getEats(myEatsRequest.BuildRequest(eatsURL)));
        // }

        string eatsURL = userEatsURL+"email="+UserSession.session.userEmailAddress;

        BuildWWW myEatsRequest = new BuildWWW();
        Debug.Log("Finished getUserGoal");

        StartCoroutine(getEats(myEatsRequest.BuildRequest(eatsURL)));

    }

     IEnumerator StartEatsRequest(userGoal currentUserGoal)  {

        string eatsURL = userEatsURL+"email="+UserSession.session.userEmailAddress;

		//string url = userEatsURL+"email="+"test@test.com";	//Testing URL

        BuildWWW myEatsRequest = new BuildWWW();
        Debug.Log("Made it to startEatsRequest");
        yield return StartCoroutine(getEats(myEatsRequest.BuildRequest(eatsURL))); //I think this is a waste of computation time
    }

    IEnumerator getEats(WWW builtRequest){
        yield return builtRequest;

        string newJsonString = "{\"allEats\":" + builtRequest.text + "}" ;
        JsonWrapper retrievedJsonWrapper = JsonWrapper.CreateFromJSON(newJsonString);

        Debug.Log("Made it to getEats");
        UserSession.session.sessionAllUserEats = retrievedJsonWrapper.allEats;

        UserSession.session.callDone = true; //all the calls are finished now
    }
}

