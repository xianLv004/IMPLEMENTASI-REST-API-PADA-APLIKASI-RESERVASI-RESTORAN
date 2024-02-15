<?php

session_start();

require_once "function.php";

if (isset($_POST["edit"])) {
    $edit = edit_data_fasilitas();
    if ($edit > 0) {
        echo "<script>
                alert('Data berhasil diubah!');
                location.href = 'fasilitas.php';
            </script>";
    } else if ($edit == 0) {
        echo "<script>
                alert('Data tidak ada yang diubah!');
                location.href = 'fasilitas.php';
            </script>";
    } else {
        echo "<script>
                alert('Data gagal diubah!');
                location.href = 'fasilitas.php';
            </script>";
    }
}
$id_fas = $_GET["id_fas"];
$fasilitas = ambil_data("SELECT * FROM fasilitas WHERE id_fas = $id_fas")[0];
?>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./src/css/bootstrap-5.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="./src/css/bootstrap-icons-1.8.3/bootstrap-icons.css">
    <title>Edit Data</title>

</head>

<body class="bg-dark text-white">
    <div class="container">
           <h1>Edit Data Fasilitas</h1>
        <a class="btn btn-warning fw-bold" href="beranda.php">Kembali</a>
        <form action="editfasi.php" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id_fas" value="<?= $fasilitas["id_fas"]; ?>">
            <input type="hidden" name="gambar-lama" value="<?= $fasilitas["gambar"]; ?>">
            <div class="table-responsive-md my-3">
                <table class="table text-white">
                    <tr>
                        <td><label for="nama">Nama Fasilitas</label></td>
                        <td>:</td>
                        <td><input type="text" name="nama_fas" id="nama_fas" value="<?= $fasilitas["nama_fas"]; ?>" required></td>
                    </tr>
                    <tr>
                        <td><label for="harga">Keterangan</label></td>
                        <td>:</td>
                        <td><input min="0" type="text" name="ket_fas" id="ket_fas" value="<?= $fasilitas["ket_fas"]; ?>" required></td>
                    </tr>
                    <tr>
                        <td><label for="gambar">Gambar</label></td>
                        <td>:</td>
                        <td>
                            <img src="src/img/<?= $fasilitas["gambar"]; ?>" width="70"><br><br>
                            <input type="file" name="gambar" accept="image/*">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td><button class="btn btn-warning" name="edit">Simpan</button></td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <script src="./src/css/bootstrap-5.2.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>