<?php 

include_once("connect.php");

if(isset($_POST['cons'])){
	// $user = "CONSTRUC01";
	$user = $_POST['cons'];
	$result = mysqli_query($conn, "SELECT * FROM construcciones WHERE `idConstruccion` = '$user'");
	if($result->num_rows > 0){
		// echo "La selección devolvió " . $result->num_rows;
		$row = $result->fetch_assoc();
		echo $row['idConstruccion'];
		echo "&";
		echo $row['direccion'];
		echo "&";
		echo $row['superficie'];
		echo "&";
		echo $row['descripcion'];
		echo "&";

		$cons_nif = $row['Constructor_Usuario_NIF'];
		//exit;

		$result2 = mysqli_query($conn, "SELECT nombre, apellidos FROM usuarios WHERE `NIF` = '$cons_nif'");
		if($result2->num_rows > 0){
			$row2 = $result2->fetch_assoc();
			echo $row2['nombre'];
			echo "&";
			echo $row2['apellidos'];
			exit;
		}
	}
}
	// mysqli_free_result($result);
	// echo $user;
	// $query = "SELECT `contraseña` FROM `usuarios` WHERE NIF LIKE '44489189J'";
	// echo $query,
	// $sql = "SELECT contaseña FROM usuarios WHERE DNI = '$user'";
	// echo $sql; 
	// $ei = $conn->prepare($query);
	// $result = mysqli_query($conn, $ei);
	// echo "RESULTADO :" . $result;
	// if($result->num_rows > 0){
	// 	$is = password_verify($pass, $result);
	// }

//$si = password_verify("admin", '$2y$10$YmEi4xCC3CokObgcU5kLLeb3cnFOR8GTv62y1lgQOSWTn19moMV3S');
// $result = mysqli_query($conn, $qry);
// if ($si == true)
// 	echo "SIIII";
// else
// 	echo "NOOOOO";

?>