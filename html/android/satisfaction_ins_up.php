<?php 

include_once("connect.php");

if(isset($_POST['cons'])){
	// $user = "CONSTRUC01";
	$cons = $_POST['cons'];
	$aux = "";
	$result = mysqli_query($conn, "SELECT codigo, nombre FROM exigencias");
	$result2 = mysqli_query($conn, "SELECT Exigencia_codigo, notas, cumplimiento FROM cumple WHERE `Construccion_idConstruccion` = '$cons'");
	//$result = mysqli_query($conn, "SELECT Exigencia_codigo FROM cumple WHERE `Construccion_idConstruccion` = '$cons' AND `cumplimiento`= 1" );
	if($result->num_rows > 0){
		// echo "La selección devolvió " . $result->num_rows;
	
		while($row = $result->fetch_assoc()){
			$num = $row['codigo'];
			$aux = $num;
			$aux = $aux . "&";
			$aux = $aux . $row['nombre'];
			$aux = $aux . "&";
			//exit;
			if($result2->num_rows > 0){
				while ( $row2 = $result2->fetch_assoc() ) {
					if ($row2['Exigencia_codigo'] == $num) {
						$aux = $aux . $row2['cumplimiento'];
						$aux = $aux . "&";
						if ($row2['notas']=="") {
							$aux = $aux . "nada";
						}else{
							$aux = $aux . $row2['notas'];
						}
					}
				}
			}else{
				$aux = $aux . "0";
				$aux = $aux . "&";
				$aux = $aux . "nada";
			}
			if (substr_count($aux, '&') == 2) {
				echo $aux . "0&nada";
			}else{
				echo $aux;
			}
			
			echo ":";
			$result2->data_seek(0);
		}
		exit;
	}else{
		echo "vacio";
		exit;
	}
}