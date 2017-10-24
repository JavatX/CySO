<?php 

include_once("connect.php");

$target_path  = "/var/www/html/obras/webroot/img/Denuncias/";
$target_path_simp = "/obras/webroot/img/Denuncias/";
$target_path = $target_path . basename( $_FILES['uploadedfile']['name']);
$target_path_simp = $target_path_simp . basename( $_FILES['uploadedfile']['name']);

error_log("Targetpath: " . $target_path);
error_log("Targetpath simplificado: " . $target_path_simp);
error_log("Nombre archivo: " . $_FILES['uploadedfile']['name']);

$corte = substr($_FILES['uploadedfile']['name'], 0, 23);
$nombreFinal = str_replace($_FILES['uploadedfile']['name'], $corte, $target_path);
$nombreFinalBD = str_replace($_FILES['uploadedfile']['name'], $corte, $target_path_simp);
$cons = substr($corte, 13);
$date = substr($corte, 0, 6);
$nombreFinal = $nombreFinal . ".jpg";
$nombreFinalBD = $nombreFinalBD . ".jpg";

error_log("Nombre cortado: " . $corte);
error_log("Nombre final: " . $nombreFinal);
error_log("Nombre final BD: " . $nombreFinalBD);
error_log("Construccion: " . $cons);
error_log("Fecha: " . $date);

error_log("A ver que sale de aqui: " . implode(", ", $_FILES['uploadedfile']));
if($_FILES['uploadedfile']['error'] == 1){
    echo "tam_exc";
    exit;
}
if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $nombreFinal)) 
{
	$sql = "INSERT INTO `denuncias` (`numDenuncia`, `fecha`, `foto`, `construcciones_idConstruccion`) VALUES ('" . $corte . "', STR_TO_DATE('" . $date . "', '%d%m%y'), '" . $nombreFinalBD . "', '" . $cons . "')";
	
	error_log($sql);
	if($conn->query($sql) === TRUE)
    	echo "success";
    else{
    	unlink($nombreFinal);
    	echo "sql_exception";
    }
} 
else
{
    echo "exception";
}

?>