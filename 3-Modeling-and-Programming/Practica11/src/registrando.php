<?php

	include("conexion.php");

	$nombre=$_POST["nombre"];
	$apPaterno=$_POST["apPaterno"];
	$apMaterno=$_POST["apMaterno"];
	$bDay=$_POST["bDay"];
	$email=$_POST["email"];

	$consulta= "INSERT INTO usuarioRegistrado(nombre, apPaterno, apMAterno, bDay, email) VALUES ('$nombre','$apPaterno','$apMaterno', '$bDay','$email')";

	if($conexion->query($consulta)==true) {
		echo "<script language='javascript'>";
			echo "alert('Usuario agregado exitosamente')";
		echo "</script>";
		echo "<p>Por favor regresa a la página anteior para seguir aprendiendo sobre Cardistry.</p>";
	} else {
		echo "<script language='javascript'>";
			echo "alert('No se pudo agregar al usuario')";
		echo "</script>";
		echo "<p>Hubo un error agregando al usuario, por favor intenta de nuevo.</p>";
	}







?>