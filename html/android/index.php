<html>
<meta charset="utf-8">
<body>
<p>
	Voy a conectarme
</p>
<?php 

include_once("connect.php");

echo "<p>Conectado. Ahora la consulta</p>";
echo "Consulta de prueba<br>";
$result = mysqli_query($conn, "SELECT numDenuncia FROM denuncias");
while($row = $result->fetch_assoc()) {
        echo "Denuncia: " . $row['numDenuncia'] . "<br>";
    }
$user = "44489189J";
if($result = mysqli_query($conn, "SELECT * FROM usuarios WHERE `NIF` = '$user'")){
	echo "La selección devolvió " . $result->num_rows;
	while($row = $result->fetch_assoc()) {
        echo "Contraseña: " . $row['contrasena'] . "<br>";
    }
	mysqli_free_result($result);
}else{
	echo "nanai de la china";
}
$si = password_verify("admin", '$2y$10$YmEi4xCC3CokObgcU5kLLeb3cnFOR8GTv62y1lgQOSWTn19moMV3S');
// $result = mysqli_query($conn, $qry);
if ($si == true)
	echo "SIIII";
else
	echo "NOOOOO";
echo "<br>Constructores y Obras<br>";
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
		//exit;
	}
	echo "<br>";
	echo "11111111111111111111111111111111111111111111111111111111111111111111111";
	echo "<br>";
	echo "<br>";
	$hhh = "[[HE1, Limitaci\xef\xbf\xbdn de demanda energ\xef\xbf\xbdtica, 0, La capa envolvente est\xef\xbf\xbd mal aplicada en ventanas y puertas.], [HE2, Rendimiento de las instalaciones t\xef\xbf\xbdrmicas, 0, nada], [HE3, Eficiencia energ\xef\xbf\xbdtica de las instalaciones de iluminaci\xef\xbf\xbdn, 0, nada], [HE4, Contribuci\xef\xbf\xbdn solar m\xef\xbf\xbdnima de agua caliente sanitaria, 0, nada], [HE5, Contribuci\xef\xbf\xbdn fotovoltaica m\xef\xbf\xbdnima de energ\xef\xbf\xbda el\xef\xbf\xbdctrica, 0, nada], [HR, Protecci\xef\xbf\xbdn frente al ruido, 0, nada], [HS1, Protecci\xef\xbf\xbdn frente a la humedad, 0, nada], [HS2, Recogida y evacuaci\xef\xbf\xbdn de residuos, 0, nada], [HS3, Calidad del aire interior, 0, nada], [HS4, Suministro de agua, 0, nada], [HS5, Evacuaci\xef\xbf\xbdn de aguas, 0, nada], [SE1, Resistencia y estabilidad, 1, nada], [SE2, Aptitud al servicio, 1, nada], [SI1, Propagaci\xef\xbf\xbdn interior, 1, nada], [SI2, Propagaci\xef\xbf\xbdn exterior, 0, Carece de cortafuegos ante la posible propagaci\xef\xbf\xbdn de incendios con respecto al edificio colindante.], [SI3, Evacuaci\xef\xbf\xbdn de ocupantes, 0, nada], [SI4, Instalaciones de protecci\xef\xbf\xbdn contra incendios, 0, nada], [SI5, Intervenci\xef\xbf\xbdn de bomberos, 0, nada], [SI6, Resistencia estructural al incendio, 0, nada], [SUA1, Seguridad frente al riesgo de ca\xef\xbf\xbddas, 0, nada], [SUA2, Seguridad frente al riesgo de impacto o de atrapamiento, 0, nada], [SUA3, Seguridad frente al riesgo de aprisionamiento, 0, nada], [SUA4, Seguridad frente al riesgo causado por iluminaci\xef\xbf\xbdn inadecuada, 0, nada], [SUA5, Seguridad frente al riesgo causado por situaciones con alta ocupaci\xef\xbf\xbdn, 0, nada], [SUA6, Seguridad frente al riesgo de ahogamiento, 0, nada], [SUA7, Seguridad frente al riesgo causado por veh\xef\xbf\xbdculos en movimiento, 0, nada], [SUA8, Seguridad frente al riesgo relacionado con la acci\xef\xbf\xbdn del rayo, 0, nada], [SUA9, Accesibilidad, 0, nada]]";
	preg_match_all("#\[(.*?)\]#", $hhh, $array);
	$a = $array['1'];
	echo "<br>";
    foreach($a as $oa){
        $porciones = explode(", ", $oa);
        echo "Porciones: <br>";
        print_r($porciones);
        echo "<br>";
        foreach($porciones as $p){
            echo $p;
            echo "<br>";
        }
    }
    echo "<br>";
    echo "<br>";
    echo "2222222222222222222222222222<br>";
	$aux = "";
	$result = mysqli_query($conn, "SELECT codigo, nombre FROM exigencias");
	$result2 = mysqli_query($conn, "SELECT Exigencia_codigo, notas, cumplimiento FROM cumple WHERE `Construccion_idConstruccion` = 'CONSTRUC01'");
	$resultAux = $result2;
	//$result = mysqli_query($conn, "SELECT Exigencia_codigo FROM cumple WHERE `Construccion_idConstruccion` = '$cons' AND `cumplimiento`= 1" );
	if($result->num_rows > 0){
		// echo "La selección devolvió " . $result->num_rows;
	
		while($row = $result->fetch_assoc()){
			$num = $row['codigo'];
			$aux = $num;
			// error_log("Solo codigo" . $aux);
			$aux = $aux . "&";
			echo $aux;
			echo "<br>";
			// error_log("Codigo y &" . $aux);
			$aux = $aux . $row['nombre'];
			echo $aux;
			echo "<br>";
			// error_log("Codigo & y nombre" . $aux);
			$aux = $aux . "&";
			echo $aux;
			echo "<br>";
			// error_log("Codigo & nombre y &" . $aux);
			//exit;
			echo "<br>Num.Rows: " . $result2->num_rows . "<br>";
			if($result2->num_rows > 0){
				echo "Estoy dentro<br>";
				while ( $row2 = $result2->fetch_assoc() ) {
					if ($row2['Exigencia_codigo'] == $num) {
						echo "<br><br>";
						echo "Cumplimeinto: " . $row2['cumplimiento'] . "<br>";
						$aux = $aux . $row2['cumplimiento'];
						$aux = $aux . "&";
						if ($row2['notas']=="") {
							$aux = $aux . "nada";
						}else{
							$aux = $aux . $row2['notas'];
						}
					}else{
						echo "<br>Puta vida tete<br>";
					}
				}
			}else{//
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
			echo "<br><br>";
			$result2->data_seek(0);
			//$result2 = $resultAux;
		}
		exit;
	}else{
		echo "vacio";
		exit;
	}
?>
</body>
</html>