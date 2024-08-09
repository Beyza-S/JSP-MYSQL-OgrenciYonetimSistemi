<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Giriş</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .kutu_login {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #dddddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-label {
            font-weight: bold;
        }
        .form-control {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="kutu_login">
        <h2 class="text-center">HARİKALAR KOLEJİ</h2>
        <h2 class="text-center">Giriş</h2>
        <form action="LoginServlet" method="post">
            <div class="mb-3">
                <label for="kullaniciAdi" class="form-label">Kullanıcı Adı</label>
                <input type="text" class="form-control" id="kullaniciAdi" name="kullaniciAdi" required>
            </div>
            <div class="mb-3">
                <label for="sifre" class="form-label">Şifre</label>
                <input type="password" class="form-control" id="sifreforLogin" name="sifreforLogin" required>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary" value="Giriş">
            </div>
        </form>
    </div>
</body>
</html>
