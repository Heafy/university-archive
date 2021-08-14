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

	$nombre = $_POST['nombre'];
	$precio = $_POST['precio'];
	$cantidad = $_POST['cantidad'];
	$imagen = $_POST['imagen'];
	$bdd = $_POST['bdd'];

	$consulta= "INSERT INTO $bdd(nombre, precio, cantidad, imagen) VALUES ('$nombre','$precio','$cantidad', '$imagen')";

	echo "<body>";
		echo "<center>";
			/* La barra superior con el menu*/
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

			/* Contenido de la página. */
			echo "<div id='contenido'>";
				if($conexion->query($consulta)==true) {
					echo "<p class='info'> Producto agregado correctamente</p>";
				} else {
					echo "<p class='info'> Algo salio mal, intentalo de nuevo</p>";
				}
			echo "</div>";

			/* La barra con las redes sociales. */
			echo "<div id='contenido'>";
				echo "<td class='logos'>";
					echo "<a href='http://www.facebook.com'/><img src='images/facebook.png' width='100px' height='100px' alt='Facebook'/></a>";
					echo "<a href='http://www.twitter.com'/><img src='images/twitter.png' width='100px' height='100px' alt='Twitter'/></a></td>";
					echo "<a href='http://www.gmail.com'/><img src='images/email.png' width='100px' height='100px' alt='EMail'/></a></td>";
				echo "</td>";
			echo "</div>";

			/* La barra inferior con el pie de pagina. */
			echo "<table class='back'>";
				echo "<tr>";
					echo "<th>Página creada por:</th>";
				echo "</tr>";
			echo "</table>";
			echo "<table class='back'>";
				echo "<tr>";
					echo "<th>Jorge Martínez</th>";
					echo "<th>Rodrigo Sanchez</th>";
				echo "</tr>";
			echo "</table>";
		echo "</center>";
	echo "</body>";

echo "</html>";


?>