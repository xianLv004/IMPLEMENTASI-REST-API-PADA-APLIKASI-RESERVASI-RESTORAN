<?php
$koneksi = mysqli_connect("localhost", "root", "", "setik");

   function login_akun()

    {
    
        global $koneksi;
        $username = htmlspecialchars($_POST["username"]);
        $password = md5(htmlspecialchars($_POST["password"]));
        $cek_akun_admin = mysqli_fetch_assoc(mysqli_query($koneksi, "SELECT * FROM `admin`  WHERE username = '$username' AND `password` = '$password'
    
        "));
               if ($cek_akun_admin != null) {
    
            $_SESSION["akun-admin"] = [
    
                "username" => $username,
    
                "password" => $password
    
            ];
        }
    
        header("Location: index.php");
    
        exit;
    }


// Function Select Data
function ambil_data($query)
{
    global $koneksi;
    $db = [];
    $sql_query = mysqli_query($koneksi, $query);
    while ($row = mysqli_fetch_assoc($sql_query)) {
        array_push($db, $row);
    }
    return $db;
}
// Function Tambah Data

function tambah_data_menu()
{
 global $koneksi;
    $nama = htmlspecialchars($_POST["nama"]);
    $harga = (int) htmlspecialchars($_POST["harga"]);
    $gambar = htmlspecialchars($_FILES["gambar"]["name"]);
    $status = htmlspecialchars($_POST["status"]);
    // Generate Kode Menu
    $query = mysqli_query($koneksi, "SELECT max(kode_menu) as kodebesar FROM menu");
	$data = mysqli_fetch_array($query);
	$kode_menu = $data['kodebesar'];
	$urutan = (int) substr($kode_menu, 3, 3);
	$urutan++;
 	$huruf = "MN";
	$kode_menu = $huruf. sprintf("%03s", $urutan);

    // cek format gambar
    $format_gambar = ["jpg", "jpeg", "png", "gif"];
    $cek_gambar = explode(".", $gambar);
    $cek_gambar = strtolower(end($cek_gambar));
    if (!in_array($cek_gambar, $format_gambar)) {
        echo "<script>
            alert('File yang diupload bukan merupakan image!');
        </script>";
        return -1; 
    }
   // upload file
    $nama_gambar = uniqid() . ".$cek_gambar";
    move_uploaded_file($_FILES["gambar"]["tmp_name"], "src/img/$nama_gambar");


    // eksekusi query insert
      $id_menu = ambil_data("SELECT MAX(SUBSTR(kode_menu, 3)) AS kode FROM menu")[0]["kode"] + 1;
    mysqli_query($koneksi, "INSERT INTO menu
                            VALUES ('', '$kode_menu', '$nama', $harga, '$nama_gambar', '$status')
    ");
    return mysqli_affected_rows($koneksi);
}

// Function Edit Data Menu

function edit_data_menu()
{

    global $koneksi;
    $id_menu = $_POST["id_menu"];
    $nama = htmlspecialchars($_POST["nama"]);
    $harga = (int) htmlspecialchars($_POST["harga"]);
    $gambar = htmlspecialchars($_FILES["gambar"]["name"]);
    $kategori = htmlspecialchars($_POST["kategori"]);
    $status = htmlspecialchars($_POST["status"]);
    $kode_menu = htmlspecialchars($_POST["kode_menu"]);
    // cek format gambar
    $format_gambar = ["jpg", "jpeg", "png", "gif"];
    $cek_gambar = explode(".", $gambar);
    $cek_gambar = strtolower(end($cek_gambar));
    if (!in_array($cek_gambar, $format_gambar) && strlen($gambar) != 0) {
        echo "<script>
            alert('File yang diupload bukan merupakan image!');
        </script>";
        return -1;
    }

    $gambar_lama = $_POST["gambar-lama"];

    if (strlen($gambar) == 0) {
        $gambar = $gambar_lama;
    } else if ($gambar != $gambar_lama && strlen($gambar) != 0) {
        move_uploaded_file($_FILES["gambar"]["tmp_name"], "src/img/$gambar");
        unlink("src/img/$gambar_lama");
    }
   
    // eksekusi query update

mysqli_query($koneksi, "UPDATE menu
                            SET kode_menu = '$kode_menu',
                                nama = '$nama',
                                harga = $harga,
                                gambar = '$gambar',
                                `status` = '$status'
                            WHERE id_menu = $id_menu
    ");
    return mysqli_affected_rows($koneksi);
}

// Function Hapus Data Menu

function hapus_data_menu()

{
    global $koneksi;

    $id_menu = $_GET["id_menu"];
    // hapus file gambar
    $file_gambar = ambil_data("SELECT * FROM menu WHERE id_menu = $id_menu")[0]["gambar"];
    unlink("src/img/$file_gambar");
    // eksekusi query delete
    mysqli_query($koneksi, "DELETE FROM menu
                            WHERE id_menu = $id_menu

    ");
    return mysqli_affected_rows($koneksi);
}

//Change Order Status

function change_order_status()
{
    global $koneksi;
    $id_reservasi= $_GET["id_reservasi"];
    $sql = "SELECT status from reservasi where id_reservasi='$id_reservasi'"; 
    $result=$koneksi-> query($sql);
    $row=$result-> fetch_assoc();
    
    
    if($row["status"]==0){
         $update = mysqli_query($koneksi,"UPDATE reservasi SET status = 1 where id_reservasi='$id_reservasi'");

         return mysqli_affected_rows($koneksi);
    }
}
    
// Function Edit Data Menu

function edit_data_fasilitas()
{

    global $koneksi;
    $id_fas = $_POST["id_fas"];
    $nama_fas = htmlspecialchars($_POST["nama_fas"]);
    $ket_fas = htmlspecialchars($_POST["ket_fas"]);
    $gambar = htmlspecialchars($_FILES["gambar"]["name"]);
    // cek format gambar
    $format_gambar = ["jpg", "jpeg", "png", "gif"];
    $cek_gambar = explode(".", $gambar);
    $cek_gambar = strtolower(end($cek_gambar));
    if (!in_array($cek_gambar, $format_gambar) && strlen($gambar) != 0) {
        echo "<script>
            alert('File yang diupload bukan merupakan image!');
        </script>";
        return -1;
    }

    $gambar_lama = $_POST["gambar-lama"];

    if (strlen($gambar) == 0) {
        $gambar = $gambar_lama;
    } else if ($gambar != $gambar_lama && strlen($gambar) != 0) {
        move_uploaded_file($_FILES["gambar"]["tmp_name"], "src/img/$gambar");
        unlink("src/img/$gambar_lama");
    }
    // eksekusi query update

mysqli_query($koneksi, "UPDATE fasilitas
                            SET nama_fas = '$nama_fas',
                                ket_fas = '$ket_fas',
                                gambar = '$gambar'
                            WHERE id_fas = $id_fas
    ");
    return mysqli_affected_rows($koneksi);
} 

// Fasilitas
function tambah_data_fasilitas()
{
 global $koneksi;
    $id_fas = $_POST["id_fas"];
    $nama_fas = htmlspecialchars($_POST["nama_fas"]);
    $ket_fas = htmlspecialchars($_POST["ket_fas"]);
    $gambar = htmlspecialchars($_FILES["gambar"]["name"]);
    // cek format gambar
    // cek format gambar
    $format_gambar = ["jpg", "jpeg", "png", "gif"];
    $cek_gambar = explode(".", $gambar);
    $cek_gambar = strtolower(end($cek_gambar));
    if (!in_array($cek_gambar, $format_gambar)) {
        echo "<script>
            alert('File yang diupload bukan merupakan image!');
        </script>";
        return -1; 
    }
   // upload file
    $nama_gambar = uniqid() . ".$cek_gambar";
    move_uploaded_file($_FILES["gambar"]["tmp_name"], "src/img/$nama_gambar");


    // eksekusi query insert
      
    mysqli_query($koneksi, "INSERT INTO fasilitas
                            VALUES ('','$nama_fas', '$ket_fas','$nama_gambar')
    ");
    return mysqli_affected_rows($koneksi);
}

// Function Hapus Data Fasilitas

function hapus_data_fasilitas()

{
    global $koneksi;
    $id_fas = $_GET["id_fas"];

    mysqli_query($koneksi, "DELETE FROM fasilitas
                            WHERE id_fas = $id_fas

    ");
    return mysqli_affected_rows($koneksi);
}


// Function Hapus Data RESERVASi

function hapus_data_reservasi()

{
    global $koneksi;
    $id_reservasi = $_GET["id_reservasi"];

    mysqli_query($koneksi, "DELETE FROM reservasi
                            WHERE id_reservasi = $id_reservasi

    ");
    return mysqli_affected_rows($koneksi);
}
// Function Hapus Data RESERVASi

function hapus_data_user()

{
    global $koneksi;
    $id = $_GET["id"];

    mysqli_query($koneksi, "DELETE FROM users_table
                            WHERE id = $id

    ");
    return mysqli_affected_rows($koneksi);
}
?>



