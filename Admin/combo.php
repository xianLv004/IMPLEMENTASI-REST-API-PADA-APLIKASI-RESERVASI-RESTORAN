<!DOCTYPE html>
<html>
<head>
    <!-- Load file CSS Bootstrap dan Select2 melalui CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
    <!-- Load file JS untuk JQuery dan Selec2.js melalui CDN -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
    
    <script>
        $(document).ready(function () {
            $(".select2").select2({
            });
        });
    </script>
    
</head>
<body>
<div class="container">
    <br>
    <h4>Multiple Select (Combo Box) di PHP</h4>
    <form  id="form" method="post">
    <div class="form-group">
        <label for="sel1">Select list:</label>
        <select class="form-control select2" multiple="multiple" name="jurusan[]">
            <?php
            include "database.php";
            //Perintah sql untuk menampilkan semua data pada tabel jurusan
            $sql="select * from jurusan";

            $hasil=mysqli_query($kon,$sql);
            $no=0;
            while ($data = mysqli_fetch_array($hasil)) {
            $no++;

            ?>
            <option  value="<?php echo $data['kode_menu'];?>"><?php echo $data['nama_menu'];?></option>
            <?php
	}
  ?>
        </select>
    </div>
    <div class="form-group">
        <input type="button" class="btn btn-info" id="Pilih" value="Pilih">
    </div>
    </form>

    <div id="tampil">
    </div>

    <script type="text/javascript">
        $(document).ready(function(){

            $("#Pilih").click(function(){

                var data = $('#form').serialize();
                $.ajax({
                    type	: 'POST',
                    url	: "ambil.php",
                    data: data,
                    cache	: false,
                    success	: function(data){
                        $("#tampil").html(data);
                    }
                });
            });
        });

    </script>
</div>
</body>
</html>