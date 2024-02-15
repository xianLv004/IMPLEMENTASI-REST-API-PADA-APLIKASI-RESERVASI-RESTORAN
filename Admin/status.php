
<?php
$koneksi = mysqli_connect("localhost", "root", "", "taasu");

$id=$_GET ['id_reservasi'];
$status=$_GET ['status'];

$q= "UPDATE reservasi SET status =$status where id_reservasi='$id'";

mysqli_query($koneksi,$q);

header('location:history.php')
?>