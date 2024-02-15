<?php
session_start();
require_once "function.php";
if (isset($_POST["tambah_fas"])) {
    $tambah_fas = tambah_data_fasilitas();
    echo $tambah_fas > 0
        ? "<script>
        alert('Data berhasil ditambah!');
        location.href = 'fasilitas.php';
    </script>"
        : "<script>
        alert('Data gagal ditambah!');
        location.href = 'fasilitas.php';
    </script>";
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./src/css/bootstrap-5.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="./src/css/bootstrap-icons-1.8.3/bootstrap-icons.css">
    <title>Tambah Data Fasilitas</title>
</head>

<body class="bg-dark text-light" >
    <div class="container">
        <h1>Tambah Data Fasilitas</h1>
        <a class="btn btn-warning fw-bold" href="fasilitas.php">Kembali</a>
        <form action="tambahfas.php" method="POST" enctype="multipart/form-data">
            <div class="table-responsive-md my-3">
                <table class="table text-light">                    
                    <tr>
                        <td><label for="nama_fas">Nama Fasilitas</label></td>
                        <td>:</td>
                        <td><input autocomplete="off" type="text" name="nama_fas" id="nama_fas" required></td>
                    </tr>
                    <tr>
                        <td><label for="ket_fas">Keterangan</label></td>
                        <td>:</td>
                        <td><input autocomplete="off" type="text" name="ket_fas" id="ket_fas" required></td>
                    </tr>
                    <tr>
                        <td><label for="gambar">Gambar</label></td>
                        <td>:</td>
                        <td>
                            <input type="file" name="gambar" accept="image/*" required>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td><button class="btn btn-warning" name="tambah_fas">Tambah</button></td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <script src="./src/css/bootstrap-5.2.0/js/bootstrap.min.js"></script>
</body>

</html>