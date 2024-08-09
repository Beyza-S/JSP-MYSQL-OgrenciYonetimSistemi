<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Personel Ana Sayfa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .kutu_ana_sayfa {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #dddddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-label {
            font-weight: bold;
        }
        .btn-container {
            text-align: center;
            margin-bottom: 20px;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
    <div class="kutu_ana_sayfa">
        <h2 class="text-center">Personel Ana Sayfa</h2>
        <h3 class="text-center">Personel Bilgileri</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>TC Kimlik No</th>
                    <th>Kullanıcı Adı</th>
                    <th>Telefon</th>
                    <th>Adres</th>
                    <th>Giriş Tarihi</th>
                    <th>Çıkış Tarihi</th>
                    <th>Departman</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${personel.id}</td>
                    <td>${personel.tck_no}</td>
                    <td>${personel.kullaniciAdi}</td>
                    <td>${personel.telefon}</td>
                    <td>${personel.adres}</td>
                    <td>${personel.girisTarihi}</td>
                    <td>${personel.cikisTarihi}</td>
                    <td>${departman.departman}</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
