using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class HomePanel : MonoBehaviour
{

    public Button homeButton;
    public GameObject journalPanel;
    public GameObject zoomPanel;

    // Use this for initialization
    void Start ()
    {
        APICall userAPICall = gameObject.AddComponent<APICall>();
        userAPICall.startRetrieveData();
        Debug.Log("Here are the goals:" + UserSession.session.sessionUserGoal.calories);
        Debug.Log("Here are the eats:" + UserSession.session.sessionAllUserEats.Length);


        homeButton.onClick.AddListener(call: hideJournal);
	}
	
	// Update is called once per frame
	void Update ()
    {
		
	}

    public void hideJournal()
    {
        zoomPanel.SetActive(true);
        journalPanel.SetActive(false);
    }
}
