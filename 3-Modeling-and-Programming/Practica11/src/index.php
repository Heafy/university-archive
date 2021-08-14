<html>	

	<head>	
		<link rel="Icon" href="images/icon.png">
		<title>Cardistry</title>
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

			#titleAux0{
				font-family: Helvetica;
				font-size: 14px;
				color: white;
			}

			#titleAux1{
				font-family: Helvetica;
				font-size: 40px;
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

		
			echo "<p id='titleAux0'> Lo primero que debes estar pensando al leer este artículo es:</p>";
			echo "<p id='titleAux1'> ¿Y qué es el Cardistry?</p>";
			echo "<p class='info'> Hay que ser un poco teóricos con ese asunto.</p>";
			echo "<p class='info'> El Cardistry se define como los movimientos artísticos que se pueden llevar a cabo con una baraja de cartas o con parte de una baraja de cartas. En Magia en muchas de las ocasiones, el Mago persigue que los movimientos que realiza con la baraja queden invisibles para el espectador y que no se vean, para dotar de más fuerza su Magia. Sin embargo, en Cardistry se busca la belleza externa y la exposición de esos movimientos teniendo la intensión clara de que el espectador pueda verlos y apreciarlos.</p>";
			echo "<p class='info'> Mas sin embargo para mí es una manera de crear arte, es una manera de expresarte a traves del movimiento y de la rutina con la manipulación con cartas. Yo no soy Mago y no se si algún día tenga interés en la Magia, pero en el Cardistry encontré una manera de expresar lo que sentía y transmitir esa sensación hacia mis espectadores, a través de un espectáculo poco conocido, pero increible de ver.</p>";

			echo "<table class='images'>";
				echo "<tr>";
					echo "<th><img src='images/indexIMG0.jpg' width=300px height=200px border='2'></th>";
					echo "<th><img src='images/indexIMG1.gif' width=300px height=200px border='2'></th>";
					echo "<th><img src='images/indexIMG2.jpg' width=300px height=200px border='2'></th>";
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