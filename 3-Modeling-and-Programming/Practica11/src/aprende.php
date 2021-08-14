<html>	

	<head>
		<link rel="Icon" href="images/icon.png">
		<title>Aprende a Hacerlo</title>	
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

			.t1{
				font-family: Helvetica;
				font-size: 14px;
				color: white;
				width: 90%;
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

			echo "<p class='info'>Nunca habŕa un manual definitivo o una guía para aprender Cardistry, uno aprende lo que desea y lo que quiere, no lo que debe.</p>";
			echo "<p class='info'>Mas sin embargo puedo recomendarte un par de cosas que realmente me ayudaron a despegar:</<p>";

			echo "<table class ='t1'>";
				echo "<tr>";
					echo "<td>Miquel Roman es un Mago y Cardist de España dedicado a enseñar todo lo que sabe a cualquier persona que desee aprender y crear arte con este conocimiento, además de enseñar por internet y dar cursos de vez en cuando, vende una parte de lo que enseña y regala mucho más de su nocimiento. He aprendido mucho de el y pienso que su libro es un excelente punto de partida.</th>";
					echo "<td><a href='http://trucosconcartas.com/libro-de-trucos-con-cartas-gratis/' target='_blank'><img src='images/libro.jpg' width=200px height=250px border='2'></a></th>";
				echo "</tr>";
			echo "</table>";

			echo "<p class='info'>Además me he tomado la libertad de crear una playlist en Youtube con todo lo que he aprendido, la voy actualizando día con día, si te interesa aprender Cardistry, es algo que no te puedes perder.</p>";
			echo "<iframe width='700' height='400' src='https://www.youtube.com/embed/videoseries?list=PLouMxHSvlhHqBdCwttpzbJk9A9p8xXV6n' frameborder='0' allowfullscreen></iframe>";

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