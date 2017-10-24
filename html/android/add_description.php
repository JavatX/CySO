<?php 

include_once("connect.php");

if(isset($_POST['description'])){
    if(isset($_POST['id'])){
        // $user = "CONSTRUC01";
        $des = $_POST['description'];
        $id = $_POST['id'];
        error_log($id);
        error_log($des);
        error_log("UPDATE denuncias SET descripcion = '$des' WHERE `numDenuncia` = '$id'");
        if(mysqli_query($conn, "UPDATE denuncias SET descripcion = '$des' WHERE `numDenuncia` = '$id'")){
            error_log("EXITO");
            echo "success";
        }else{
            $result = mysqli_query($conn, "SELECT foto FROM denuncias WHERE `numDenuncia` = '$id'");
            if($result->num_rows > 0){
                $row = $result->fetch_assoc();
                $ruta = mb_substr($row[foto], 29);
                unlink($ruta);
                mysqli_query($conn, "DELETE FROM denuncias WHERE `numDenuncia` = '$id'");
                error_log("BORRADO ERROR");
                echo "erased";
            }
        }
    }
}else{
    if(isset($_POST['id'])){
        $result = mysqli_query($conn, "SELECT foto FROM denuncias WHERE `numDenuncia` = '$id'");
        if($result->num_rows > 0){
            $row = $result->fetch_assoc();
            $ruta = mb_substr($row[foto], 29);
            unlink($ruta);
            mysqli_query($conn, "DELETE FROM denuncias WHERE `numDenuncia` = '$id'");
            error_log("BORRADO NO DES");
            echo "erased";
        }
    }else{
        echo "no_id";
    }
}
