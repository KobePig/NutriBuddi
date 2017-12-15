using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class JournalPanel : MonoBehaviour
{

    public Button journalButton;
    public GameObject journalPanel;
    public GameObject zoomInPanel;

    public StatsPanel journalStatsPanel;

    public PopulateLogPanel journalPopulateLogPanel;

	// Use this for initialization
	void Start ()
    {
                journalButton.onClick.AddListener(call: openJournal);

	}
	
	// Update is called once per frame
	void Update ()
    {
		
	}

    public void openJournal()
    {
            // Debug.Log("Here are the goals:" + UserSession.session.sessionUserGoal.calories);
            // Debug.Log("Here are the eats:" + UserSession.session.sessionAllUserEats[0].food);
            // Debug.Log("Here are the goals:" + UserSession.session.sessionUserGoal.calories);
            // Debug.Log("Here are the eats:" + UserSession.session.sessionAllUserEats.Length);
        StatsPopulateObject myPopulateObject = new StatsPopulateObject();
        myPopulateObject.populateStatBars(journalStatsPanel);

        journalPopulateLogPanel.putFoodsInPanel();


        zoomInPanel.SetActive(false);
        journalPanel.SetActive(true);
    }
}
