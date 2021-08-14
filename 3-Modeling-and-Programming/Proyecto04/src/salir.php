<?php

if (isset($_COOKIE['IDafiliado'])){
	setCookie('IDafiliado', '', time() - 10000);
}
if (isset($_COOKIE['IDempleado'])){
	setCookie('IDempleado', '', time() - 10000);
}
header("Location:index.php");
session_start();
session_unset();
session_destroy();

?>