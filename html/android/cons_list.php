<?php 

/*
include_once("connect.php");

if(isset($_POST['cons'])){
	// $user = "CONSTRUC01";
	$user = $_POST['cons'];
	$result = mysqli_query($conn, "SELECT * FROM construcciones WHERE `idConstruccion` = '$user'");
	if($result->num_rows > 0){
		// echo "La selección devolvió " . $result->num_rows;
		$row = $result->fetch_assoc();
		echo $row['idConstruccion'];
		echo "_";
		echo $row['direccion'];
		echo "_";
		echo $row['superficie'];
		echo "_";
		echo $row['descripcion'];
		echo "_";
		echo $row['Constructor_Usuario_NIF'];
		exit;
	}
}
*/
include_once("connect.php");

$result = mysqli_query($conn, "SELECT idConstruccion FROM construcciones");
if($result->num_rows > 0){
		// echo "La selección devolvió " . $result->num_rows;
	while($row = $result->fetch_assoc()) {
        echo $row['idConstruccion'] . "&";
    }
		// $row = $result->fetch_assoc();
		// // foreach ($result as $r) {
		// // 	echo 
		// // }
		// echo $row['numDenuncia'];
		// echo "_";
		// echo $row['fecha'];
		// echo "_";
		// echo $row['foto'];
		// echo "_";
		// echo $row['descripcion'];
		// echo "_";
		// echo $row['Inspector_Usuario_NIF'];
		// echo "_";
		// echo $row['Construcciones_idConstruccion'];
		exit;
	}