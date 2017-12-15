using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TypeDropdownScript : MonoBehaviour {

	public Dropdown typeDropDown;

	public GameObject statsPanel;
	public GameObject logPanel;

	// Use this for initialization
	void Start () {
		typeDropDown.onValueChanged.AddListener(delegate{
			checkOptionSelected(typeDropDown);
			}); 
	}
	
	// Update is called once per frame
	void Update () {
		
	}
	void checkOptionSelected(Dropdown clickedDropdown){
		if(clickedDropdown.value == 0){
			statsPanel.SetActive(true);
			logPanel.SetActive(false);
		}
		if(clickedDropdown.value == 1){
			statsPanel.SetActive(false);
			logPanel.SetActive(true);
		}
	}
}
