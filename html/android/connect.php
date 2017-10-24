<?php
header("Content-Type: text/html;charset=utf-8");
$servername = "83.165.253.135";
$username = "root";
$password = "kakashi";
$dbname = "cake";
$conn = mysqli_connect("127.0.0.1", "root", "kakashi", "cake");
if(!$conn):
	error_log("Connection failed", 0);
	//echo "Connection failed: " . mysqli_connect_error();
else:
	//echo mysqli_get_host_info($conn);
endif;
?>