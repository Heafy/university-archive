<html>	

	<head>
		<link rel="Icon" href="images/icon.png">
		<title>Movimientos</title>
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
				color: white;
				text-align: justify;
				margin-left: 15px;
				margin-right: 15px;
			}

			.flourishes{
				font-family: Helvetica;
				font-size: 16px;
				width: 60%;
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


			echo "<p class='info'> Existen una enorme cantidad de movimientos y día a día son mas, todos son creadores por los Cardist (Las personas que se dedican a practicar Cardistry), por mencionar algunos:</p>";
		echo "</center>";


			echo "<ul class='info'>";
				echo "<li>Corte Charlier</li>";
				echo "<li>The Werm</li>";
				echo "<li>Twirl</li>";
				echo "<li>Corte a dos manos</li>";
				echo "<li>Cyclone</li>";
				echo "<li>TG UB</li>";
				echo "<li>Corte Swing</li>";
				echo "<li>etc, etc...</li>";
			echo "</ul>";

		echo "<center>";

			echo "<p class='info'>Pero quiero mostrarte mis 3 florituras favoritas:</p>";
			echo "<table class='flourishes'>";
				echo "<tr>"; 
					echo "<th>Corte Tornado</th>";
					echo "<th><a href='https://youtu.be/lj3WIinGHKI' target='_blank'><img src='images/tornadoCut.jpg' width=300px height=200px border='2'></a></th>";
				echo "</tr>";
					echo "<th>Benwade Evolution</th>";
					echo "<th><a href='https://youtu.be/RLyvF1Fqd90' target='_blank'><img src='images/benWadeEvolution.jpg' width=300px height=200px border='2'></a></th>";
				echo "<tr>";
					echo "<th>Dynamo Shuffle</th>";
					echo "<th><a href='https://youtu.be/G9wc6jeXPhY' target='_blank'><img src='images/dynamoShuffle.jpg' width=300px height=200px border='2'></a></th>";
				echo "</tr>";
			echo "</table>";


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