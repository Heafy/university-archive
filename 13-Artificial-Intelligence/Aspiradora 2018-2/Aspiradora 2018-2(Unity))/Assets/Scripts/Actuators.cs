using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Actuators : MonoBehaviour {

    // Variable speed para determinar la velocidad a la que se mueve la aspiradora
	public float speed;
    //Variable rotatepeed para determina la velocidad de rotacion de la aspiradora
    public float rotateSpeed;
    // Componente para modificar los sensores una vez que aspira el actuador
    private Sensors sensor;

     // Metodo que se ejecuta al inicio del script.
    void Start () {
        sensor = GetComponent<Sensors>();
    }

    // Mueve el objeto en la dirección hacia adelante con respecto a su vector de dirección
	public void MoveForward(){
        Vector3 v = new Vector3(0, 0, speed);
        transform.Translate(v * Time.deltaTime);
	}

    // Mueve el objeto en la dirección hacia atras con respecto a su vector de dirección
	public void MoveBackward(){
        Vector3 v = new Vector3(0, 0, -speed);
        transform.Translate(v * Time.deltaTime);
	}

    // Mueve el objeto en la direccion izquierda con respecto a su vector de direccion
    public void MoveLeft(){
        Vector3 v = new Vector3(-speed, 0, 0);
        transform.Translate(v * Time.deltaTime);
    }
    // Mueve el objeto en la direccion derecha con respecto a su vector de direccion
    public void MoveRight(){
        Vector3 v = new Vector3(speed, 0, 0);
        transform.Translate(v * Time.deltaTime);
    }

    // Metodo auxiliar para desplazarse una gran distancia hacia enfrente
    // Para alejarse de la estacion de carga
    // El movimiento es brusco para que los sensores no sigan detectanto que esta cerca
    // De la base y lo manden de vuelta al objeto
    public void MoveAwayStation(){
        float s = speed * 20;
        Vector3 v = new Vector3(0, 0, s);
        transform.Translate(v * Time.deltaTime);
    }


    // Mueve el objeto en la dirección hacia la izquierda con respecto a su posición actual
	public void RotateLeft(){
        Vector3 v = new Vector3(0, -rotateSpeed, 0);
        transform.Rotate(v * Time.deltaTime);
	}

    // Mueve el objeto en la dirección hacia la izquierda con respecto a su posición actual
	public void RotateRight(){
        Vector3 v = new Vector3(0, rotateSpeed, 0);
        transform.Rotate(v * Time.deltaTime);
	}

    // Rota el objeto aproximadamente 45 grados a la derecha
    // Se usa cuando el objeto tiene que rotar desoues de golpear con una pared 
    public void Rotate45Left(){
        Vector3 v = new Vector3(0, -rotateSpeed, 0);
        for(int i = 0; i < 60; i++){
            transform.Rotate(v * Time.deltaTime);
        }
    }

    // Rota el objeto aproximadamente 45 grados a la derecha
    // Se usa cuando el objeto tiene que rotar desoues de golpear con una pared
    public void Rotate45Right(){
        Vector3 v = new Vector3(0, rotateSpeed, 0);
        for(int i = 0; i < 60; i++){
            transform.Rotate(v * Time.deltaTime);
        }
    }

    // Metodo para decidir un giro aleatorio hacia la izquierda o a la derecha
    public void RotateRandom(){
        // bool aleatorio
        bool random = (Random.Range(0, 2) == 0);
        if(random){
            Rotate45Left();
        }else{
            Rotate45Right();
        }
    }

    // Aspira el objeto que recibe como parametro    
	public void Aspire(GameObject trash){
		trash.gameObject.SetActive(false);
        sensor.SetTouchTrash(false);
        sensor.SetNearTrash(false);
        Debug.Log("Aspiring trash");
	}
}
