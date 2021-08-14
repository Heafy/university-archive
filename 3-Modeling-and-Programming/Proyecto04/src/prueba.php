<html>	

	<head>	
		<!--Pone un icono-->
		<!-- Implementarlo en la lista con su correcto formato-->
		<title>Prueba</title>
		<style>
			.botonDespliega {
   				background-color: #4CAF50;
    			color: white;
    			padding: 16px;
    			font-size: 16px;
    			border: none;
    			cursor: pointer;
			}

			.boton {
    			position: relative;
    			display: inline-block;
			}

			.boton-contenido {
    			display: none;
    			position: absolute;
    			background-color: #f9f9f9;
    			min-width: 160px;
    			box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
			}

			.boton-contenido a {
    			color: black;
    			padding: 12px 16px;
    			text-decoration: none;
    			display: block;
			}

			.boton-contenido a:hover {background-color: #f1f1f1}

			.boton:hover .boton-contenido {
    			display: block;
			}

			.boton:hover .botonDespliega {
    			background-color: #3e8e41;
			}

		</style>
	</head>

<?php
	echo "<body>";
	echo "<center>";
	echo "<div class='boton'>";
  		echo "<button class='botonDespliega'>boton0</button>";
  		echo "<div class='boton-contenido'>";
    		echo "<a href='#'>Link 0.1</a>";
    		echo "<a href='#'>Link 0.2</a>";
    		echo "<a href='#'>Link 0.3</a>";
  		echo "</div>";
	echo "</div>";
	echo "<div class='boton'>";
  		echo "<button class='botonDespliega'>boton0</button>";
  		echo "<div class='boton-contenido'>";
    		echo "<a href='#'>Link 0.1</a>";
    		echo "<a href='#'>Link 0.2</a>";
    		echo "<a href='#'>Link 0.3</a>";
  		echo "</div>";
	echo "</div>";
	echo "</center>";
	echo "</body>";

echo "</html>";


?>