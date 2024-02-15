<?php
session_start();
require_once "function.php";
if (isset($_POST["tambah"])) {
    $tambah = tambah_data_menu();
    echo $tambah > 0
        ? "<script>
        alert('Data berhasil ditambah!');
        location.href = 'menu.php';
    </script>"
        : "<script>
        alert('Data gagal ditambah!');
        location.href = 'menu.php';
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
    <title>Tambah Data</title>
</head>

<body class="bg-dark text-light" >
    <div class="container">
        <h1>Tambah Data Masakan</h1>
        <a class="btn btn-warning fw-bold" href="menu.php">Kembali</a>
        <form action="tambah.php" method="POST" enctype="multipart/form-data">
            <div class="table-responsive-md my-3">
                <table class="table text-light">                    
                    <tr>
                        <td><label for="nama">Nama Makanan</label></td>
                        <td>:</td>
                        <td><input autocomplete="off" type="text" name="nama" id="nama_menu" required></td>
                    </tr>
                    <tr>
                        <td><label for="harga">Harga</label></td>
                        <td>:</td>
                        <td><input min="0" type="number" name="harga" id="harga_menu" required></td>
                    </tr>
                    <tr>
                        <td><label for="gambar">Gambar</label></td>
                        <td>:</td>
                        <td>
                            <input type="file" name="gambar" accept="image/*" required>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="status">Status</label></td>
                        <td>:</td>
                        <td>
                            <label for="tersedia"><input type="radio" name="status" id="tersedia" value="tersedia" checked>Tersedia</label>
                            <label for="tidak-tersedia"><input type="radio" name="status" id="tidak-tersedia" value="tidak tersedia">Tidak Tersedia</label>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td><button class="btn btn-warning" name="tambah">Tambah</button></td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <script src="./src/css/bootstrap-5.2.0/js/bootstrap.min.js"></script>
</body>

</html>