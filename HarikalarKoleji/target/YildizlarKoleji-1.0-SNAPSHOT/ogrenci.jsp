<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Öğrenci Bilgileri</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .kutu_ogrenci {
            max-width: 600px;
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
        .btn-container {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="kutu_ogrenci">
        <h2 class="text-center">Öğrenci Bilgileri</h2>
        <form action="OgrenciServlet" method="post">
            <input type="hidden" name="islem" value="${ogrenci != null ? 'guncelle' : 'ekle'}">
            <input type="hidden" name="id" value="${ogrenci.id}">
            <div class="mb-3">
                <label for="tck_no" class="form-label">TC Kimlik No</label>
                <input type="text" class="form-control" id="tck_no" name="tck_no" value="${ogrenci.tck_no}" required>
            </div>
            <div class="mb-3">
                <label for="kullaniciAdi" class="form-label">Kullanıcı Adı</label>
                <input type="text" class="form-control" id="kullaniciAdi" name="kullaniciAdi" value="${ogrenci.kullaniciAdi}" required>
            </div>
            <div class="mb-3">
                <label for="sifre" class="form-label">Şifre</label>
                <input type="password" class="form-control" id="sifre" name="sifre" value="${ogrenci.sifre}" required>
            </div>
            <div class="mb-3">
                <label for="telefon" class="form-label">Telefon</label>
                <input type="text" class="form-control" id="telefon" name="telefon" value="${ogrenci.telefon}" required>
            </div>
            <div class="mb-3">
                <label for="adres" class="form-label">Adres</label>
                <input type="text" class="form-control" id="adres" name="adres" value="${ogrenci.adres}" required>
            </div>
            <div class="mb-3">
                <label for="girisTarihi" class="form-label">Giriş Tarihi</label>
                <input type="date" class="form-control" id="girisTarihi" name="girisTarihi" value="${ogrenci.girisTarihi}" required>
            </div>
            <div class="mb-3">
                <label for="cikisTarihi" class="form-label">Çıkış Tarihi</label>
                <input type="date" class="form-control" id="cikisTarihi" name="cikisTarihi" value="${ogrenci.cikisTarihi}">
            </div>
            <div class="btn-container">
                <input type="submit" class="btn btn-primary" value="${ogrenci != null ? 'Güncelle' : 'Ekle'}">
            </div>
        </form>
    </div>
</body>
</html>
