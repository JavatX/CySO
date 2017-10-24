<?php 
$link = mysqli_connect("77.27.171.70", "root", "kakashi", "cake");
if($link)
	printf("Conectado");
if ($mysqli->connect_errno) {
	printf("Falló la conexión: %s\n", mysqli_connect_error());
    exit();
}
if($result = $mysqli_query($link, "SELECT NIF FROM usuarios")){
	    printf("La selección devolvió %d filas.\n", mysqli_num_rows($resultado));
	mysqli_free_result($result);
}