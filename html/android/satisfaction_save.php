<?php 

include_once("connect.php");

if(isset($_POST['exigencias'])){
	// $user = "CONSTRUC01";
	$cons = $_POST['exigencias'];
	error_log($cons);
	$constr = $_POST['constr'];
	error_log($constr);
	//error_log($cons);
	preg_match_all("#\[(.*?)\]#", $cons, $array);
	$a = $array['1'];
    foreach($a as $oa){
        $porciones = explode(", ", $oa);
        $nom = $porciones['0'];
        if($nom == "[HE1")
        	$nom = "HE1";
        error_log("NOM " . $nom);
        $des = $porciones['1'];
        error_log("DES " . $des);
        $cump = $porciones['2'];
        error_log("CUMP " . strval($cump));
        if($porciones['3'] == "nada")
        	$notas = "";
        else
        	$notas = $porciones['3'];
        error_log("NOTAS " . $notas);
        if(mysqli_query($conn, "UPDATE `cumple` SET notas = '" . $notas. "', `cumplimiento` = '" . $cump . "' WHERE `Exigencia_Codigo` = '" . $nom . "' AND `Construccion_idConstruccion` = '" . $constr . "'")){
        	error_log("Actualizado");
        	echo "exito";
        }else{
        	if($notas != "nada" || $cump == "1"){
        		if(mysqli_query($conn, "INSERT INTO `cumple` (`Construccion_idConstruccion`, `Exigencia_codigo`, `notas`, `cumplimiento`) VALUES ('" . $constr . "', '" . $nom . "', '" . $notas . "', '" . $cump . "')")){
        			error_log("Insertado");
        			echo "exito";
        		}else{
        			error_log("Fallo de insercion");
        			echo "error";
        		}
        	}else{
        		error_log("Sin cambios");
        		echo "no_cam";
        	}
        }
    }
    exit;
	// $aux = "";
	// $result = mysqli_query($conn, "SELECT codigo, nombre FROM exigencias");
	// $result2 = mysqli_query($conn, "SELECT Exigencia_codigo, notas, cumplimiento FROM cumple WHERE `Construccion_idConstruccion` = '$cons'");
	// //$result = mysqli_query($conn, "SELECT Exigencia_codigo FROM cumple WHERE `Construccion_idConstruccion` = '$cons' AND `cumplimiento`= 1" );
	// if($result->num_rows > 0){
	// 	// echo "La selección devolvió " . $result->num_rows;
	
	// 	while($row = $result->fetch_assoc()){
	// 		$num = $row['codigo'];
	// 		$aux = $num;
	// 		$aux = $aux . "&";
	// 		$aux = $aux . $row['nombre'];
	// 		$aux = $aux . "&";
	// 		//exit;
	// 		if($result2->num_rows > 0){
	// 			while ( $row2 = $result2->fetch_assoc() ) {
	// 				if ($row2['Exigencia_codigo'] == $num) {
	// 					$aux = $aux . $row2['cumplimiento'];
	// 					$aux = $aux . "&";
	// 					if ($row2['notas']=="") {
	// 						$aux = $aux . "nada";
	// 					}else{
	// 						$aux = $aux . $row2['notas'];
	// 					}
	// 				}
	// 			}
	// 		}else{
	// 			$aux = $aux . "0";
	// 			$aux = $aux . "&";
	// 			$aux = $aux . "nada";
	// 		}
	// 		if (substr_count($aux, '&') == 2) {
	// 			echo $aux . "0&nada";
	// 		}else{
	// 			echo $aux;
	// 		}
			
	// 		echo ":";
	// 		$result2->data_seek(0);
	// 	}
	// 	exit;
	// }else{
	// 	echo "vacio";
	// 	exit;
	// }
}