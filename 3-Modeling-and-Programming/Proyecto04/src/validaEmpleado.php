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
					echo "<th><a href='registro.php'>Registrate</a></th>";
					echo "<th><a href='ingreso.php'>Inicia Sesión</a></th>";
					echo "<th><a href=''>Empleados</a></th>";
				echo "</tr>";
			echo "</table>";

			include("conexion.php");

			$u=$_POST["ID"];
			$c=$_POST["password"];

			$estuvo=false;
			$consulta="SELECT * FROM empleados";
			$resultado=$conexion->query($consulta);
			while($row=$resultado->fetch_assoc()) {
				$ID=$row["ID"];
				$password=$row["password"];
				if($ID=='admin' && $password=='adminPass'){
					header("Location:administrador.php");
					setcookie('IDempleado', '$ID', time() + 4800);
					break;
				}
				if($ID==$u && $password==$c) {
					$estuvo=true;
				break;
				} 	
			}
			/* Se crea la cookie si se inicia sesión */
			if($estuvo) {
				setcookie('IDempleado', '$ID', time() + 4800);
				header("Location:index.php");


			} else {
				echo "<p class='info'>Este usuario no existe</p>";
			}
			

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