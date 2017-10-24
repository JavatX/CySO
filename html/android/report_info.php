<?php 

include_once("connect.php");

if(isset($_POST['cons'])){
	// $user = "CONSTRUC01";
	$user = $_POST['cons'];
	$result = mysqli_query($conn, "SELECT * FROM denuncias WHERE `numDenuncia` = '$user'");
	if($result->num_rows > 0){
		// echo "La selección devolvió " . $result->num_rows;
		$row = $result->fetch_assoc();
		echo $row['numDenuncia'];
		echo "&";
		echo $row['fecha'];
		echo "&";
		echo $row['foto'];
		echo "&";
		echo $row['descripcion'];
		echo "&";
		echo $row['Inspector_Usuario_NIF'];
		echo "&";
		echo $row['construcciones_idConstruccion'];
		exit;
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