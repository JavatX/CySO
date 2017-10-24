<?php 

include_once("connect.php");

if(isset($_POST['dni'])){
	$dni = $_POST['dni'];
	$result = mysqli_query($conn, "SELECT numDenuncia FROM denuncias WHERE Inspector_Usuario_NIF = '$dni'");
	if($result->num_rows > 0){
			// echo "La selección devolvió " . $result->num_rows;
		while($row = $result->fetch_assoc()) {
			echo $row['numDenuncia'] . "&";
		}
		echo ":";
	}
	$result = mysqli_query($conn, "SELECT idConstruccion FROM construcciones");
	if($result->num_rows > 0){
			// echo "La selección devolvió " . $result->num_rows;
		while($row = $result->fetch_assoc()) {
			echo $row['idConstruccion'] . "&";
		}
		exit;
	}
}else{
	$result = mysqli_query($conn, "SELECT numDenuncia FROM denuncias");
	if($result->num_rows > 0){
			// echo "La selección devolvió " . $result->num_rows;
		while($row = $result->fetch_assoc()) {
			echo $row['numDenuncia'] . "&";
		}
		echo ":";
	}
	$result = mysqli_query($conn, "SELECT idConstruccion FROM construcciones");
	if($result->num_rows > 0){
			// echo "La selección devolvió " . $result->num_rows;
		while($row = $result->fetch_assoc()) {
			echo $row['idConstruccion'] . "&";
		}
		exit;
	}
}	