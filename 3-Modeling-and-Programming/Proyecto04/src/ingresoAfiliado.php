<html>
	<head>

		<meta charset='utf-8'/>
		<link rel="Icon" href="images/icon.png">
		<title>Ingresa</title>
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
?>

<div id='contenido'>
	<form method="POST" action="validaAfiliado.php">
		<table class='info'>
			<tr>
				<h1 class='title'>¡Acceso a miembros!</h1>
			</tr>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="ID"/></td>
			</tr>
			<tr>
				<td>Contraseña:</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Acceder"/><input type="submit" value="Registrar"/></td>
			</tr>
		</table>
	</form>
</div>

<?php
		/* La barra inferior con el pie de página. */				
		echo "<table class='back'>";
			echo "<tr>";
				echo "<th>Página creada por:</th>";
			echo "</tr>";
		echo "</table>";
		echo "<table class='back'>";
			echo "<tr>";
				echo "<th>Jorge Martínez</th>";
				echo "<th>Rodrigo Sánchez</th>";
			echo "</tr>";
		echo "</table>";

		echo "</center>";
	echo "</body>";

echo "</html>";

?>