<?php 
session_start();
if (!isset($_SESSION["akun-admin"])) {
    header("Location: index.php");
    exit;
} 
session_unset();
session_destroy();
?>
<script>
    alert('Berhasil Logout!');
    location.href = 'index.php';
</script>