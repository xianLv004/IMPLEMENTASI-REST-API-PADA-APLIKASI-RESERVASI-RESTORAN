<?php
session_start();
require_once "function.php";
$id_fas = $_GET["id_fas"];
if (isset($_GET["id_fas"])) $hapus = hapus_data_fasilitas();
echo $hapus > 0
?> "<script>
alert('Data berhasil dihapus!');
location.href = 'fasilitas.php';
</script>" 
:"<script>
alert('Data gagal dihapus!');
location.href = 'fasilitas.php';
</script>";