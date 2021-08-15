using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerController : MonoBehaviour {

	// La velocidad a la que se mueve Player
	public float speed;
	// Text para mostrar los cubos recogidos
	public Text countText;
	// Text para mostrar cuando el jugador gana
	public Text winText;
	// Rigidbody para Player
	private Rigidbody rb;
	// Int para contar los cubos recogidos
	private int count;
	
	// Inicializa los componentes
	void Start(){
		rb = GetComponent<Rigidbody>();
		count = 0;
		setCountText();
		winText.text = "";
	}

	// Le da el movimiento correcto a Player
	void FixedUpdate(){
		float moveHorizontal = Input.GetAxis("Horizontal");
		float moveVertical = Input.GetAxis("Vertical");
		Vector3 movement = new Vector3(moveHorizontal, 0.0f, moveVertical);
		rb.AddForce(movement*speed);
	}

	// Cuando detecta una colision con un objeto Pickup aumenta la cuenta
	// y desaparece el objeto de la escena
	void OnTriggerEnter(Collider other){
		if(other.gameObject.CompareTag("Pick Up")){
			other.gameObject.SetActive(false);
			count = count + 1;
			setCountText();
		}
	}

	// Mantiene la informacion actualizada de los cubos y muestra el 
	// mensaje ganador
	void setCountText(){
		countText.text = "Cubos recogidos: " + count.ToString();
		if(count >= 12){
			winText.text = "¡Ganaste!";
		} 
	}
}
