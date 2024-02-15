<?php
session_start();
require_once "function.php";
if (isset($_GET["id"])) $hapus = hapus_data_user();
echo $hapus > 0
?> "<script>
    alert('Data berhasil dihapus!');
    location.href = 'user.php';
</script>" 
:"<script>
    alert('Data gagal dihapus!');
    location.href = 'user.php';
</script>";