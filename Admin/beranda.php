<?php
session_start();
require_once "function.php";
if (!isset($_SESSION["akun-admin"]) ) {
    header("Location: index.php");
    exit;
} 
?>
<!DOCTYPE html>

<html lang="en">



<head>

<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="src/css/bootstrap-5.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/css/bootstrap-icons-1.8.3/bootstrap-icons.css">
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

    <h2 class ="text-white"> Order Details</h2>
    <table class="table table-striped">

    <thead class ="bg-warning" >
      <tr>
        <th>N.O</th>
        <th>Id Pelangan</th>
        <th>No Handphone</th>
        <th>Tanggal Reservasi</th>
        <th>Jumlah Orang</th>
        <th>Waktu Reservasi</th>
        <th>Order Status</th>
     </tr>
    </thead>
     <?php
      include_once "database.php";
      $sql="SELECT * from reservasi WHERE status = 0" ;
      $result=$conn-> query($sql);
      
      if ($result-> num_rows > 0){
        while ($row=$result-> fetch_assoc()) {
    ?>
       <tr class ="bg-light">
          <td><?=$row["id_reservasi"]?></td>
          <td><?=$row["id_user"]?></td>
          <td><?=$row["no_hp"]?></td>
          <td><?=$row["tanggal_reservasi"]?></td>
          <td><?=$row["jumlah"]?></td>
          <td><?=$row["jam_reservasi"]?></td>
           <td>
           <?php 
                if($row["status"]==0){    
                   echo '<p><a href="status.php?id_reservasi='.$row['id_reservasi']. '&status=1" class="btn btn-danger">Pending</a></p>';
                    }else{
                    echo '<p><a href="status.php?id_reservasi='.$row['id_reservasi']. '&status=0" class="btn btn-warning">Accept</a></p>';
            ?>
               </td>
        

            <?php
                }
            ?>
              

    <?php
            
        }
      }
    ?>
     
  </table>

		</main>
	</div>
</section>

</div><!-- container //  -->

</body>
</html>

<script src="css/js/bootstrap.min.js"></script>  


