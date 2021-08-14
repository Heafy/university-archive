<?php
header("Content-disposition: attachment; filename=cafeteria.sql");
header("Content-type: application/sql");
readfile("cafeteria.sql");
?>