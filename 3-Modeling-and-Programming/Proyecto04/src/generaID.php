<html>	

	<head>	
		<link rel="Icon" href="images/icon.png">
		<title>Cafeteria</title>
		<style>

			
			.menu{
				width: 100%;
				height: 40px;
				font-family: Helvetica;
				font-size: 20px;
				background-color: #531D00;
			}

			.menu th:hover{
				background-color: gray;
			}

			.menu tr a{
				text-decoration: none;
				color: white;
			}

			.back{
				background-color: #531D00;
				color: white;
				width: 95%;
				font-family: Helvetica;
				font-size: 16px;
			}

			.info{
				font-family: Helvetica;
				font-size: 14px;
				color: black;
				text-align: justify;
				margin-left: 15px;
				margin-right: 15px;
			}

			.title{
				font-family: Helvetica;
				font-size: 24px;
				color: black;
				text-align: center;
				margin-left: 15px;
				margin-right: 15px;
			}

			#contenido {
				background-image: url("images/opaco.png");
				width: 80%;
			}

			body {
				background-image: url("images/background.jpg");
			}
		</style>
	</head>

<?php

	include("conexion.php");
		
	// Recibe los parámetros necesarios.
	$nombre = $_POST['nombre'];
	$paterno = $_POST['paterno'];
	$materno = $_POST['materno'];
	/*$dia = (strlen($_POST['dia']) == 1) ? '0'.$_POST['dia'] : $_POST['dia'];
	$mes = (strlen($_POST['mes']) == 1) ? '0'.$_POST['mes'] : $_POST['mes'];
	$ano = $_POST['ano'];*/

	// Función para quitar los acentos contenidos en una cadena.
	function quitaAcentos($palabra) {
	    $palabra = trim($palabra);
	    $palabra = str_replace(
	    	array('á', 'Á', 'é', 'É', 'í', 'Í', 'ó', 'Ó', 'ú', 'Ú'),
	    	array('A', 'A', 'E', 'E', 'I', 'I', 'O', 'O', 'U', 'U'),
	    	$palabra);
	    return $palabra;
	}

	// Función para quitar artículos contenidos en una cadena.
	function quitaArticulos($palabra) {
		$palabra = str_replace("DEL ", "", $palabra); 
		$palabra = str_replace("LAS ", "", $palabra); 
		$palabra = str_replace("DE ", "", $palabra); 
		$palabra = str_replace("LA ", "", $palabra); 
		$palabra = str_replace("Y ", "", $palabra); 
		$palabra = str_replace("A ", "", $palabra); 
		return $palabra; 
	}

	// Función que revisa si la letra dada es vocal o no.
	function esVocal($letra) { 
		if($letra == 'A' || $letra == 'E' || $letra == 'I' || $letra == 'O' || $letra == 'U' || $letra == 'a' || $letra == 'e' || $letra == 'i' || $letra == 'o' || $letra == 'u')
			return 1;
		else
			return 0; 
	} 

	// Función que genera un ID dados ciertos parámetros.
	function generaID($nombre, $paterno, $materno) { 
		$nombre = strtoupper(trim($nombre)); 
		$paterno = strtoupper(trim($paterno)); 
		$materno = strtoupper(trim($materno)); 
		$paterno = quitaAcentos($paterno);
		$materno = quitaAcentos($materno);
		$ID = ""; 

		// Agregamos el primer caracter del apellido paterno.
		$ID = substr($paterno, 0, 1); 

		//Buscamos y agregamos al ID la primera vocal del primer apellido.
		for($n = 1; $n < strlen($paterno); $n++) { 
			$letra = substr($paterno, $n, 1);
			if(esVocal($letra)) { 
				$ID .= $letra; 
				break; 
			} 
	}

	function agregaDigito($nombreCompleto, $fecha, &$ID) { 
		$modulo = $suma % 10; 
		if ($modulo != 0) 
			$ID .= "0"; 
		else { 
			$suma = 10 - $modulo; 
			if ($suma == 10) 
				$ID .= ""; 
			else 
				$ID .= ""; 
		}
	}

	// Agregamos el primer caracter del apellido materno. 
	$ID .= substr($materno, 0, 1); 

	// Agregamos el primer caracter del primer nombre.
	$ID .= substr($nombre,0, 1); 

	// Agregamos la fecha de nacimiento.
	$ID .= substr($fecha, 6, 2).substr($fecha, 2, 2).substr($fecha, 0, 2); 

	// Le agregamos la homoclave al ID.
	agregaDigito($paterno." ".$materno." ".$nombre, $fecha, $ID); 
		return $ID; 
	}

	// Concatenamos la fecha del día de hoy.
	$hoy = getdate();
	$date = $hoy[mday].$hoy[mon].$hoy[year];
	/*$date = '-'.$hoy[mday].'-'.$hoy[mon].'-'.$hoy[year];*/

	$ID = generaID($nombre, $paterno, $materno).$date;
	$consulta= "INSERT INTO afiliados(nombre, paterno, materno, ID, password) VALUES ('$nombre','$paterno','$materno', '$ID','$ID')";

	echo "<body>";
		echo "<center>";
			echo "<table class='menu'>";
				echo "<tr>";
					echo "<th><a href='index.php'>Inicio</a></th>";
					echo "<th><a href='menu.php'>Nuestro Menú</a></th>";
					echo "<th><a href='ubicacion.php'>Ubicacion</a></th>";
					if (isset($_COOKIE['IDafiliado'])){
					} else {
					echo "<th><a href='registro.php'>Registrate</a></th>";
					}
					if (isset($_COOKIE['IDafiliado'])){
						echo "<th><a href='salir.php'>Salir</a></th>";
					} else {
						echo "<th><a href='ingresoAfiliado.php'>Inicia Sesión</a></th>";	
					}
					if (isset($_COOKIE['IDempleado'])){
						echo "<th><a href='salir.php'>Salir</a></th>";
					} else {
					echo "<th><a href='ingresoEmpleados.php'>Empleados</a></th>";
					}
				echo "</tr>";
			echo "</table>";
			
			echo "<div id='contenido'>";
				if($conexion->query($consulta)==true) {
					echo "<p class='info'> Usuario agregado exitosamente</p>";
					echo "<br>";
					echo "<p class='info'> Guarda bien estos datos para poder iniciar sesión </p>";
					echo "<p class='info'> ID: ";
					echo "$ID";
					echo "<p class='info'> Contraseña: ";
					echo "$ID";
				} else {
					echo "<p class='info'> Algo salió mal registrandote, intentalo nuevamente</p>";
				}
			echo "</div>";
		echo "</center>";
	echo "</body>";

?>