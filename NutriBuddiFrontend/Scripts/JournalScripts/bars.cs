using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class bars : MonoBehaviour {

	 public Slider m_Slider;




	// Use this for initialization
	void Start () {
		
	}
	private void SetHealthUI ()
     {
         // Set the slider's value appropriately.
         m_Slider.value = 50;
     }	
	// Update is called once per frame
	void Update () {
			
	}
}
