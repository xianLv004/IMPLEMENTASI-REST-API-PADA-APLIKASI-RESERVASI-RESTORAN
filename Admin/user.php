<!DOCTYPE html>

<html lang="en">



<head>

<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./src/css/bootstrap-5.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="./src/css/bootstrap-icons-1.8.3/bootstrap-icons.css">

    <title>Beranda</title>

</head>
<style type="text/css">

.sidebar .nav-link {
    font-weight: 500;
    color: var(--bs-dark);
}
.sidebar .nav-link:hover {
    background: var(--bs-light);
    color: var(--bs-primary);
}

</style>
</head>

<body class="bg-dark">

<header class="section-header ">
  
<div class="container-fluid position-fixed top-0 bg-warning p-2 d-flex justify-content-between" style="z-index: 2;">

<div class="text-dark h3 d-flex">

    <span id="menu-list" role="button"><i class="bi bi-list"></i></span>

    <span >
    <img src="src/img/logo2.png" alt="Logo" class="rounded" width="40px" style="padding-left:15px">
    </span>

    <span class="mx-3">Bebek Hitam Favorite Asli Madura</span>
   
</div>
<a class="btn btn-dark fw-bold" href="logout.php" onclick="return confirm('Ingin Logout?')">Logout</a>

    </div>

</header> <!-- section-header.// -->
<br>
<br>
<br>

<div class="container">

<section class="section-content ">

	<div class="row clearfix ">
    
		<aside class="col-lg-3"> 


<nav class="sidebar card bg-light">

<ul class="nav flex-column">

	<li class="nav-item list-group-item">
		<a class="nav-link" href="beranda.php"> List Reservasi </a>
	</li>
	<li class="nav-item list-group-item">
		<a class="nav-link" href="menu.php"> Menu </a>
	</li>
	<li class="nav-item list-group-item">
		<a class="nav-link" href="fasilitas.php"> Fasilitas </a>
	</li>
	<li class="nav-item list-group-item">
    <a class="nav-link" href="history.php"> Histori Reservasi </a>
	</li>
    <li class="nav-item list-group-item">
    <a class="nav-link" href="user.php"> Data Pelangan </a>
	</li>

</ul>

</nav>

</aside>
<main class="col-lg-9">
    <div class="d-flex justify-content-between mb-3">
    <h2 class ="text-white"> Daftar Pelangan</h2>
    <nav class="navbar navbar-light d-flex justify-content-end">
    </nav>
  </div>
  <table class="table table-striped">
    <thead class ="bg-warning" >
      <tr>
        <th>ID PELANGAN</th>
        <th>NAMA</th>
        <th>EMAIL</th>
        <th>Edit Data
        </th>
     </tr>
    </thead>
    <?php
      include_once "database.php";
      $sql="SELECT * from users_table";
      $result=$conn-> query($sql);
      
      if ($result-> num_rows > 0){
        while ($row=$result-> fetch_assoc()) {
    ?>
       <tr class ="bg-light">
          <td><?=$row["id"]?></td>
          <td><?=$row["name"]?></td>
          <td><?=$row["email"]?></td>
          <td>
                        <a class="btn btn-lg btn-dark" title="Hapus" href="hapususer.php?id=<?= $row["id"]; ?>" onclick="return confirm('Ingin Menghapus Menu?')">
                            <i class="bi bi-trash3-fill"></i>
                        </a>
          </td>

    <?php
            
        }
      }
    ?>
     