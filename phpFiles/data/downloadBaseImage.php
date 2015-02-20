<?php
/*
    Page Name: downloadBaseImage.php
    Author: Ryan Forrester
    Version: 1.0
    Description: This page takes a files and retrieves the base64 encoded string.
    Dependencies: This page is dependent on the user trying to download an image.
    Change History: 2015.02.08 Original version by RF
*/

    $q = $_REQUEST["q"];
    $q = base64_decode($q);
    $type = pathinfo($q, PATHINFO_EXTENSION);
    $image = file_get_contents($q);
	$base64 = 'data:image/' . $type . ';base64,' . base64_encode($image);
	echo($base64);
?>