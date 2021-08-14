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

			echo "<div id='contenido'>";
				echo "<h1 class='title'>Ubicacion de la Cafeteria</h1>";

				echo "<iframe src='https://www.google.com/maps/embed?pb=!1m0!3m2!1ses!2smx!4v1466486963123!6m8!1m7!1saNoJaXf1FmgRu3HcbVLmLA!2m2!1d19.32243542407476!2d-99.1868377878452!3f230!4f0!5f0.7820865974627469' width='600' height='450' frameborder='0' style='border:1N' allowfullscreen></iframe>";
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