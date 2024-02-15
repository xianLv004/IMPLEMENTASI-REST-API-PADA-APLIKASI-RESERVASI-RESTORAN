<?php

session_start();

require_once "function.php";

if (isset($_POST["login"])) {

    $login = login_akun();

} ?>

<!DOCTYPE html>

<html lang="en">



<head>

    <meta charset="UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="src/css/bootstrap-5.2.0/css/bootstrap.min.css">

    <title>Login</title>

</head>



<body class="bg-dark">
    <div class="container">
        <div id="judul-form" class="text-center text-warning h1 mt-5">LOGIN</div><br>
        <div class="mx-auto rounded p-5" style="width: 500px; background-color: white;">
            <div class="d-flex justify-content-between">
                <button id="tab-login" class="btn btn-warning fw-bold" style="width: 500px;">LOGIN</button>            
            </div><br>
                        <div class="text-center">
                <img src="src/img/logo.png" alt="Logo" class="rounded" width="150px">
                            </div><br>
                            
            <!-- Jika Username & Password Login Salah -->

            <?php if (isset($_POST["login"])) {
                    if (!$login) { ?>
                    <div class="alert alert-warning alert-dismissible">
                        * username/password salah
                        <button class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
            <?php }
            } ?>

<!-- Form Login -->
            <form id="form" action="index.php" method="POST">
                <input class="form-control shadow-warning mx-auto d-block" type="text" autocomplete="off" name="username" placeholder="Username" required><br>
                <input class="form-control shadow-warning mx-auto d-block" type="password" autocomplete="off" name="password" placeholder="Password" required><br>
                <button class="btn btn-warning" name="login">Login</button>
            </form>
        </div>           
    </div>

    <script src="src/css/bootstrap-5.2.0/js/bootstrap.bundle.min.js"></script>
    <script src="src/js/login.js"></script>
</body>

</html>