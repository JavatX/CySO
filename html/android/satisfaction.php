<?php 

include_once("connect.php");

if(isset($_POST['cons'])){
	// $user = "CONSTRUC01";
	$cons = $_POST['cons'];
	$result = mysqli_query($conn, "SELECT Exigencia_codigo FROM cumple WHERE `Construccion_idConstruccion` = '$cons' AND `cumplimiento`= 1" );
	if($result->num_rows > 0){
		// echo "La selección devolvió " . $result->num_rows;
	
		while($row = $result->fetch_assoc()){
			$num = $row['Exigencia_codigo'];
			echo $num;
			echo "&";
			//exit;

			$result2 = mysqli_query($conn, "SELECT nombre, descripcion FROM exigencias WHERE `codigo` = '$num'");
			if($result2->num_rows > 0){
				while ( $row2 = $result2->fetch_assoc() ) {
					echo $row2['nombre'];
					echo "&";
					echo $row2['descripcion'];
					echo ":";
					
				}
			}
		}
		exit;
	}else{
		echo "vacio";
		exit;
	}
}