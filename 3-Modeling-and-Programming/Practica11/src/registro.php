<html>	
	<meta charset='utf-8' />
	<head>
		<link rel="Icon" href="images/icon.png">
		<title>Registrate</title>	
		<style>

			.cover td{
				background-color: #B60101;;
			}

			.menu{
				width: 100%;
				height: 40px;
				font-family: Helvetica;
				font-size: 20px;
				background-color: #B60101;
			}

			.menu th:hover{
				background-color: gray;
			}

			.menu tr a{
				text-decoration: none;
				color: white;
			}

			.info{
				font-family: Helvetica;
				font-size: 14px;
				margin-left: 15px;
				margin-right: 15px;
				color: white;
			}

			body{
				background-image: url("images/bg.jpg");
			}

			.back{
				background-color: #B60101;
				color: white;
				width: 95%;
			}

		</style>

		<script>
			function myFunction() {
    		alert("Esto debería ser una alerta.");
			}
		</script>
	</head>

<?php
	echo "<body>";

		echo "<center>";
			echo "<table class='cover'>";
				echo "<tr>";
					echo "<td><a href='index.php'><img src='images/cover.jpg' width='100%' height='280px' border='3'/></a></td>";
				echo "<tr>";
			echo "</table>";

			echo "<table class='menu'>";
				echo "<tr>";
					echo "<th><a href='movimientos.php'>Movimientos</a></th>";
					echo "<th><a href='aprende.php'>Aprende a Hacerlo</a></th>";
					echo "<th><a href='registro.php'>Registrate</a></th>";
				echo "</tr>";
			echo "</table>";

			echo "<p class='info'>Registrate para obtener mas información de Cardistry en tu correo.</p>";

			echo "<form method='POST' action='registrando.php'/>";
				echo "<table class='info'>";
					echo "<tr>";
						echo "<td> Nombre: </td>";
						echo "<td><input type='text' name='nombre' /></td>";
					echo "</tr>";
					echo "<tr>";
						echo "<td> Apellido Paterno: </td>";
						echo "<td><input type='text' name='apPaterno' /></td>";
					echo "</tr>";
					echo "<tr>";
						echo "<td> Apellido Materno: </td>";
						echo "<td><input type='text' name='apMaterno' /></td>";
					echo "</tr>";
					echo "<tr>";
						echo "<td> Fecha de Nacimiento: </td>";
						echo "<td><input type='date' name='bDay' /></td>";
					echo "</tr>";
					echo "<tr>";
						echo "<td> Correo Electrónico: </td>";
						echo "<td><input type='text' name='email' /></td>";
					echo "</tr>";
					echo "<tr>";
						echo "<td>  </td>";
						echo "<td><input type='submit' name='Registrate' /></td>";
					echo "</tr>";

				echo "</table>";
			echo "</form>";


		


			echo "<table class='back'>";
				echo "<tr>";
					echo "<th>Página creada por:</th>";
				echo "</tr>";
				echo "<tr>";
					echo "<th>Jorge Martínez</th>";
				echo "</tr>";
			echo "</table>";

		echo "</center>";

	echo "</body>";

echo "</html>";


?>