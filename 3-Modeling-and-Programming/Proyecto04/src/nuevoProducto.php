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

			.lista{
				font-family: Helvetica;
				font-size: 14px;
				color: black;
				text-align: justify;
				margin-left: 15px;
				margin-right: 15px;
				list-style-type: none;
				text-decoration: none;
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

			/* Contenido de la página. */
			echo "<div id='contenido'>";
				echo "<h1 class='title'> Pagina del administrador</h1>";
				
				echo "<form method='POST' action='agregaProducto.php'>";
					echo "<table class='info'>";
						echo "<tr>";
							echo "<td>Nombre:</td>";
							echo "<td><input type='text' name='nombre'/></td>";
						echo "</tr>";
						echo "<tr>";
							echo "<td>Precio:</td>";
							echo "<td><input type='text' name='precio'/></td>";
						echo "</tr>";
						echo "<tr>";
							echo "<td>Cantidad en stock:</td>";
							echo "<td><input type='text' name='cantidad'/></td>";
						echo "</tr>";
						echo "<tr>";
							echo "<td>Imagen (por favor introduce la direccion de su ubicacion en tus directorios</td>";
							echo "<td><input type='text' name='imagen'/></td>";
						echo "</tr>";
						echo "<tr>";
							echo "<td><ul class='lista'>
										<li>Escribe el tipo de producto que deseas agregar:</li>
										<li>Bebidas Calientes</li>
										<li>Bebidas Frias</li>
										<li>Pasteles</li>
										<li>Crepas</li>
										<li>Refrescos</li>
										<li>Sandwiches</li></ul></td>";
							echo "<td><input type='text' name='bdd'/></td>";
						echo "<tr>";
							echo "<td></td>";
							echo "<td><input type='submit' value='Agregar Producto'/></td>";
						echo "</tr>";
					echo "</table>";
				echo "</form>";
					

				echo "<p class='info'>Selecciona una categoría conde agregar el producto</p>";
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


?>Pphp