<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Ana Sayfa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .kutu_ana_sayfa {
            max-width: 1200px;
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
        <h2 class="text-center">Admin Ana Sayfa</h2>
        <div class="btn-container">
            <form action="personel.jsp" method="get" style="display:inline;">
                <input type="submit" class="btn btn-primary" value="Personel Ekle">
            </form>
            <form action="ogrenci.jsp" method="get" style="display:inline;">
                <input type="submit" class="btn btn-success" value="Öğrenci Ekle">
            </form>
        </div>
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
                    <th>Güncelle</th>
                    <th>Sil</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="personel" items="${personeller}">
                <tr>
                    <td>${personel.id}</td>
                    <td>${personel.tck_no}</td>
                    <td>${personel.kullaniciAdi}</td>
                    <td>${personel.telefon}</td>
                    <td>${personel.adres}</td>
                    <td>${personel.girisTarihi}</td>
                    <td>${personel.cikisTarihi}</td>
                    
                    <td>
                        <c:forEach var="departman" items="${departmanlar}">
                            <c:if test="${departman.id == personel.departmanId}">
                                ${departman.ad}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <form action="PersonelServlet" method="get" style="display:inline;">
                            <input type="hidden" name="islem" value="guncelle">
                            <input type="hidden" name="id" value="${personel.id}">
                            <input type="submit" class="btn btn-warning" value="Güncelle">
                        </form>
                    </td>
                    <td>
                        <form action="PersonelServlet" method="get" style="display:inline;">
                            <input type="hidden" name="islem" value="sil">
                            <input type="hidden" name="id" value="${personel.id}">
                            <input type="submit" class="btn btn-danger" value="Sil">
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <h3 class="text-center">Öğrenci Bilgileri</h3>
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
                    <th>Bölüm</th>
                    <th>Güncelle</th>
                    <th>Sil</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ogrenci" items="${ogrenciler}">
                <tr>
                    <td>${ogrenci.id}</td>
                    <td>${ogrenci.tck_no}</td>
                    <td>${ogrenci.kullaniciAdi}</td>
                    <td>${ogrenci.telefon}</td>
                    <td>${ogrenci.adres}</td>
                    <td>${ogrenci.girisTarihi}</td>
                    <td>${ogrenci.cikisTarihi}</td>
                   
                    <td>
                        <c:forEach var="bolum" items="${bolumler}">
                            <c:if test="${bolum.id == ogrenci.bolumId}">
                                ${bolum.ad}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <form action="OgrenciServlet" method="get" style="display:inline;">
                            <input type="hidden" name="islem" value="guncelle">
                            <input type="hidden" name="id" value="${ogrenci.id}">
                            <input type="submit" class="btn btn-warning" value="Güncelle">
                        </form>
                    </td>
                    <td>
                        <form action="OgrenciServlet" method="get" style="display:inline;">
                            <input type="hidden" name="islem" value="sil">
                            <input type="hidden" name="id" value="${ogrenci.id}">
                            <input type="submit" class="btn btn-danger" value="Sil">
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
