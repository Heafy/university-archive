using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Script auxiliar que agrega comprobaciones de colisión adicionales
// Útil para tener más de un collider en un gameObject (sin que actuen como unión de geometrias)
public class VisionController : MonoBehaviour {

	// Variables auxiliares para recordar si hay colisiones
	public bool isNearTrash = false;
	public bool isNearWall = false;
	public bool isNearStation = false;

	// Los siguientes métodos verifican las posibles colisiones del gameObject (la esfera que brinda vision a la aspiradora)
	// con otros objetos tales como las Trashs y Walles.
	void OnTriggerEnter(Collider other) {
		if(other.gameObject.CompareTag("Trash"))
			isNearTrash = true;
		if(other.gameObject.CompareTag("Wall"))
			isNearWall = true;
		if(other.gameObject.CompareTag("Station"))
			isNearStation = true;
	}

	
	void OnTriggerStay(Collider other) {
		if(other.gameObject.CompareTag("Trash"))
			isNearTrash = true;
		if(other.gameObject.CompareTag("Wall"))
			isNearWall = true;
		if(other.gameObject.CompareTag("Station"))
			isNearStation = true;
	}

	void OnTriggerExit(Collider other) {
		if(other.gameObject.CompareTag("Trash"))
			isNearTrash = false;
		if(other.gameObject.CompareTag("Wall"))
			isNearWall = false;
		if(other.gameObject.CompareTag("Station"))
			isNearStation = false;
	}

	// Los siguientes métodos son públicos y su intención es brindar información:
	public bool NearTrash(){
		return isNearTrash;
	}

	public bool NearWall(){
		return isNearWall;
	}

	public bool NearStation(){
		return isNearStation;
	}

	public void setNearTrash(bool value){
		isNearTrash = value;
	}

}
