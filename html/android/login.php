<?php 

include_once("connect.php");

if(isset($_POST['user']) && isset($_POST['pass'])){
	$user = $_POST['user'];
	$pass = $_POST['pass'];
	if(mysqli_query($conn, "SELECT * FROM inspectores WHERE `Usuario_NIF` = '$user'")){
		$result = mysqli_query($conn, "SELECT * FROM usuarios WHERE `NIF` = '$user'");
		if($result->num_rows > 0){
			// echo "La selección devolvió " . $result->num_rows;
			$row = $result->fetch_assoc();
			$is = password_verify($pass, $row['contrasena']);
	        if($is){
	        	echo "true";
	        	exit;
	        }else{
	        	echo "false_pass";
	        	exit;
	        }
		}else{
			echo "false_DNI";
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