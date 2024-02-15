<?php
session_start();
require_once "function.php";
if (isset($_GET["id_reservasi"])) $hapus = hapus_data_reservasi();
echo $hapus > 0
?> "<script>
    alert('Data berhasil dihapus!');
    location.href = 'history.php';
</script>" 
:"<script>
    alert('Data gagal dihapus!');
    location.href = 'history.php';
</script>";