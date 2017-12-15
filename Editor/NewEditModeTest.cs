using UnityEngine;
using UnityEditor;
using UnityEngine.TestTools;
using NUnit.Framework;
using System.Collections;
using UnityEngine.UI;
public class NewPlayModeTest {

	[Test]
	public void NewPlayModeTestSimplePasses() 
	{
		//Debug.Log("About to test");
		//Debug.Log(GameObject.FindWithTag("mountain").GetComponent<Canvas>());
		//Debug.Log(GameObject.FindWithTag("Canvas").GetComponent<Canvas>());

		// Use the Assert class to test conditions.
		Debug.Log("NewPlayModeTestSimplePassed");
	}

	// A UnityTest behaves like a coroutine in PlayMode
	// and allows you to yield null to skip a frame in EditMode
	[UnityTest]
	public IEnumerator TestSceneSetup() 
	{
		// Use the Assert class to test conditions.
		// yield to skip a frame
			Debug.Log("TestSceneSetup");

		SetupScene();



		yield return new WaitForSeconds(3);
	}

    void SetupScene()
    {
		Debug.Log(GameObject.FindWithTag("Canvas").GetComponent<Canvas>());

		Canvas homeScreen = GameObject.FindWithTag("Canvas").GetComponent<Canvas>();

		if(!homeScreen.Equals(null))
		{
				Assert.Pass("success");
				
		}

		

			 

    }
}
