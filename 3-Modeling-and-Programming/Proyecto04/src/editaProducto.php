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

			include("conexion.php");

			echo "<div id='contenido'>";

				echo "<h1 class='title'> Pagina del administrador</h1>";
				echo "<h2 class='title'> Da click en la imagen del elemento que quieras editar</h2>";
				
				echo "<form method='POST' action=''>";
					$consulta="SELECT * FROM bebidasCalientes";
						$resultado=$conexion->query($consulta);

					echo "<table class='info'>";
						while($row=$resultado->fetch_assoc()) {
							$nombre=$row["nombre"];

							$precio=$row["precio"];
							$imagen=$row["imagen"];
							echo "<tr>";
								echo "<td><a href=''><img src='images/$imagen' width='300' height=200 border='2'></a></td>";
								echo "<td>";
									echo "<p>".$nombre."</p>";
									echo "<p>Precio: $".$precio."</p>";
								echo "</td>";
							echo "</tr>";
						}
						echo "</table>";

					$consulta="SELECT * FROM bebidasFrias";
						$resultado=$conexion->query($consulta);

					echo "<table class='info'>";
						while($row=$resultado->fetch_assoc()) {
							$nombre=$row["nombre"];
							$precio=$row["precio"];
							$imagen=$row["imagen"];
							echo "<tr>";
								echo "<td><a href=''><img src='images/$imagen' width='300' height=200 border='2'></a></td>";
								echo "<td>";
									echo "<p>".$nombre."</p>";
									echo "<p>Precio: $".$precio."</p>";
								echo "</td>";
							echo "</tr>";
						}
						echo "</table>";

					$consulta="SELECT * FROM pasteles";
						$resultado=$conexion->query($consulta);

					echo "<table class='info'>";
						while($row=$resultado->fetch_assoc()) {
							$nombre=$row["nombre"];
							$precio=$row["precio"];
							$imagen=$row["imagen"];
							echo "<tr>";
								echo "<td><a href=''><img src='images/$imagen' width='300' height=200 border='2'></a></td>";
								echo "<td>";
									echo "<p>".$nombre."</p>";
									echo "<p>Precio: $".$precio."</p>";
								echo "</td>";
							echo "</tr>";
						}
						echo "</table>";

					$consulta="SELECT * FROM crepas";
						$resultado=$conexion->query($consulta);

					echo "<table class='info'>";
						while($row=$resultado->fetch_assoc()) {
							$nombre=$row["nombre"];
							$precio=$row["precio"];
							$imagen=$row["imagen"];
							echo "<tr>";
								echo "<td><a href=''><img src='images/$imagen' width='300' height=200 border='2'></a></td>";
								echo "<td>";
									echo "<p>".$nombre."</p>";
									echo "<p>Precio: $".$precio."</p>";
								echo "</td>";
							echo "</tr>";
						}
						echo "</table>";

					$consulta="SELECT * FROM refrescos";
						$resultado=$conexion->query($consulta);

					echo "<table class='info'>";
						while($row=$resultado->fetch_assoc()) {
							$nombre=$row["nombre"];
							$precio=$row["precio"];
							$imagen=$row["imagen"];
							echo "<tr>";
								echo "<td><a href=''><img src='images/$imagen' width='300' height=200 border='2'></a></td>";
								echo "<td>";
									echo "<p>".$nombre."</p>";
									echo "<p>Precio: $".$precio."</p>";
								echo "</td>";
							echo "</tr>";
						}
						echo "</table>";

					$consulta="SELECT * FROM sandwiches";
						$resultado=$conexion->query($consulta);

					echo "<table class='info'>";
						while($row=$resultado->fetch_assoc()) {
							$nombre=$row["nombre"];
							$precio=$row["precio"];
							$imagen=$row["imagen"];
							echo "<tr>";
								echo "<td><a href=''><img src='images/$imagen' width='300' height=200 border='2'></a></td>";
								echo "<td>";
									echo "<p>".$nombre."</p>";
									echo "<p>Precio: $".$precio."</p>";
								echo "</td>";
							echo "</tr>";
						}
						echo "</table>";

				echo "</form>";
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