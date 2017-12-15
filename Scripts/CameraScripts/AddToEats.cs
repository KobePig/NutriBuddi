using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.IO;

//For purposes of this demo i simply added the canvast to this scrpit
//This canvas is the one tod display nutriotion after the user clicks yes
public class AddToEats : MonoBehaviour {

	public Canvas resultCanvas;
	public Canvas unknownFoodCanvas;
	public Canvas FoodInfoCanvas;
	public Button postToDatabaseButton;
	public Button yesButton;
	public Button noButton;
	public Button retakeButton;
	public Button submitButton;
	public InputField foodName;
	public Text status;

	//string dummy = "apple";


	public string userInfoURL= "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/eats/addNewEats?";
	public string addNewImageUrl = "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/imageClassifier/addNewImage?";

								
	
	// Use this for initialization
	void Start () {
		//Debug.Log (UserSession.session.currentClassifiedFood);
		yesButton.onClick.AddListener(call: showNutrition);
		postToDatabaseButton.onClick.AddListener(call: PostToEats);
		retakeButton.onClick.AddListener(call: backToCameraScene);
		noButton.onClick.AddListener(call: manualSubmit);
		submitButton.onClick.AddListener (call: submitUnknownFood);

		// if the classifier returns "" enable unknownfoodcanvas
		if (UserSession.session.currentClassifiedFood.Length == 0) {
			//Debug.Log (UserSession.session.currentClassifiedFood);
			resultCanvas.enabled = false;
			FoodInfoCanvas.enabled = false;
			unknownFoodCanvas.enabled = true;
		// otherwise show result canvas
		} else {
			unknownFoodCanvas.enabled = false;
			FoodInfoCanvas.enabled = false;
			resultCanvas.enabled = true;
		}

	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void showNutrition () {
		resultCanvas.enabled = false;
		unknownFoodCanvas.enabled = false;
		FoodInfoCanvas.enabled = true;
		
	}

	void PostToEats(){

	 	string url = userInfoURL+"email="+UserSession.session.userEmailAddress+"&numServings=1" + "&foodName="+UserSession.session.currentClassifiedFood;

		BuildWWW myRequest = new BuildWWW();
		StartCoroutine(startMyComplicatedTask(myRequest.BuildRequest(url)));
		
		
	}

	IEnumerator startMyComplicatedTask(WWW builtRequest){
		yield return builtRequest;

		//this is where you do all the complicated stuff like normal(if you actually need to do anything after the call(ex: check for 404 error))
		
	
		Debug.Log(builtRequest.text);
		//SceneManager.LoadScene("MainLanding");
		//Need to opend the foodInfo canvas before going back to the other page
		
		//Enable food nutrition canvas 

		SceneManager.LoadScene("MainLanding");

	}

	void backToCameraScene() {

		SceneManager.LoadScene ("CameraScene");
	}

	void submitUnknownFood() {

		// do something
		// append the filename?

		// change the file name and reupload to aws?
		// change it to email@email.com_timestamp_REVIEW_CARROT ?
		// get the new file name
		// string newFileName = UserSession.session.fileName + "_REVIEW_" + foodName.text;
		// change the file name
		// System.IO.File.Move(Application.persistentDataPath + Path.DirectorySeparatorChar + UserSession.session.fileName, Application.persistentDataPath + Path.DirectorySeparatorChar + newFileName);

	
		//send success
		//status.text = foodName.text + " submitted!";
		string newUrl = addNewImageUrl + "email="+UserSession.session.userEmailAddress+"&foodName="+foodName.text+"&fileName="+UserSession.session.fileName+"&numServing=1&latitude=0&longitude=0";
		//string newUrl = addNewImageUrl + "email="+UserSession.session.userEmailAddress+"&foodName="+foodName.text+"&fileName="+UserSession.session.fileName+"&numServing=1&latitude=0&longitude=0";
		BuildWWW newRequest = new BuildWWW ();
		StartCoroutine (postNewFood (newRequest.BuildRequest (newUrl)));
		//status.text = foodName.text + " submitted!";
		//submitButton.enabled = false;
		submitButton.gameObject.SetActive(false);

		//send success
		status.text = foodName.text + " food submitted!";

	}
	IEnumerator postNewFood(WWW builtRequest) {
		yield return builtRequest;

		Debug.Log(builtRequest.text);
		//SceneManager.LoadScene("MainLanding");

	}


	void manualSubmit() {
		
		resultCanvas.enabled = false;
		unknownFoodCanvas.enabled = true;
	}

}
