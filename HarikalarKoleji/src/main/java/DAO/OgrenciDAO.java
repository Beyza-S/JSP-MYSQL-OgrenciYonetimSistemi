package DAO;

import Entity.Ogrenci;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.Veritabani.getConnection;
import Entity.Bolum;
import Entity.OgrenciBolum;

public class OgrenciDAO {

    private static final String DOGRU_OGRENCI = "SELECT o.id, o.tck_no, o.kullanici_adi, o.sifre, o.telefon, o.adres, o.giris_tarihi, o.cikis_tarihi, b.ad AS bolum " +
            "FROM ogrenci o " +
            "LEFT JOIN ogrenci_bolum ob ON o.id = ob.ogrenci_id " +
            "LEFT JOIN bolum b ON ob.bolum_id = b.id " +
            "WHERE o.kullanici_adi = ? AND o.sifre = ?";

    private static final String SELECT_ALL_OGRENCI = "SELECT o.id, o.tck_no, o.kullanici_adi, o.sifre, o.telefon, o.adres, o.giris_tarihi, o.cikis_tarihi, b.ad AS bolum , b.id AS bolumId " +
            "FROM ogrenci o " +
            "LEFT JOIN ogrenci_bolum ob ON o.id = ob.ogrenci_id " +
            "LEFT JOIN bolum b ON ob.bolum_id = b.id";

    private static final String SELECT_OGRENCI_BY_ID = "SELECT o.id, o.tck_no, o.kullanici_adi, o.sifre, o.telefon, o.adres, o.giris_tarihi, o.cikis_tarihi, b.ad AS bolum " +
            "FROM ogrenci o " +
            "LEFT JOIN ogrenci_bolum ob ON o.id = ob.ogrenci_id " +
            "LEFT JOIN bolum b ON ob.bolum_id = b.id WHERE o.id = ?";

    private static final String EKLE_OGRENCI_SQL = "INSERT INTO ogrenci (tck_no, kullanici_adi, sifre, telefon, adres, giris_tarihi, cikis_tarihi) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GUNCELLE_OGRENCI_SQL = "UPDATE ogrenci SET tck_no = ?, kullanici_adi = ?, sifre = ?, telefon = ?, adres = ?, giris_tarihi = ?, cikis_tarihi = ? WHERE id = ?";

    private static final String SIL_OGRENCI_SQL = "DELETE FROM ogrenci WHERE id = ?";

    public Ogrenci dogrulaOgrenci(String kullaniciAdi, String sifre) {
        Ogrenci ogrenci = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DOGRU_OGRENCI)) {
            preparedStatement.setString(1, kullaniciAdi);
            preparedStatement.setString(2, sifre);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String tckNo = rs.getString("tck_no");
                String telefon = rs.getString("telefon");
                String adres = rs.getString("adres");
                Date girisTarihi = rs.getDate("girisTarihi");
                Date cikisTarihi = rs.getDate("cikisTarihi");
                ogrenci = new Ogrenci(id, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
                System.out.println("Öğrenci bulundu: " + kullaniciAdi);
            } else {
                System.out.println("Öğrenci bulunamadı: " + kullaniciAdi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenci;
    }

    public List<Ogrenci> getAllOgrenci() {
        List<Ogrenci> ogrenciler = new ArrayList<>();
        List<Bolum> bolumler = new ArrayList<>();
        List<OgrenciBolum> ogrenciBolumler = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_OGRENCI)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tckNo = rs.getString("tck_no");
                String kullaniciAdi = rs.getString("kullanici_adi");
                String sifre = rs.getString("sifre");
                String telefon = rs.getString("telefon");
                String adres = rs.getString("adres");
                Date girisTarihi = rs.getDate("giris_tarihi");
                Date cikisTarihi = rs.getDate("cikis_tarihi");
                String bolumAdi= rs.getString("bolum");
                int bolumID = rs.getInt("bolumID");
                
                
                Ogrenci eklenecekOgrenci = new Ogrenci(id, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
                Bolum eklenecekBolum = new Bolum(bolumID,bolumAdi);
                ogrenciler.add(eklenecekOgrenci);
                bolumler.add(eklenecekBolum);
                ogrenciBolumler.add(new OgrenciBolum(eklenecekOgrenci,eklenecekBolum));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenciler;
    }

    public void ekleOgrenci(Ogrenci ogrenci) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EKLE_OGRENCI_SQL)) {
            preparedStatement.setString(1, ogrenci.getTck_no());
            preparedStatement.setString(2, ogrenci.getKullaniciAdi());
            preparedStatement.setString(3, ogrenci.getSifre());
            preparedStatement.setString(4, ogrenci.getTelefon());
            preparedStatement.setString(5, ogrenci.getAdres());
            preparedStatement.setDate(6, ogrenci.getGirisTarihi() != null ? new java.sql.Date(ogrenci.getGirisTarihi().getTime()) : null);
            preparedStatement.setDate(7, ogrenci.getCikisTarihi() != null ? new java.sql.Date(ogrenci.getCikisTarihi().getTime()) : null);
            preparedStatement.executeUpdate();
        }
    }

    public void guncelleOgrenci(Ogrenci ogrenci) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GUNCELLE_OGRENCI_SQL)) {
            statement.setString(1, ogrenci.getTck_no());
            statement.setString(2, ogrenci.getKullaniciAdi());
            statement.setString(3, ogrenci.getSifre());
            statement.setString(4, ogrenci.getTelefon());
            statement.setString(5, ogrenci.getAdres());
            statement.setDate(6, ogrenci.getGirisTarihi() != null ? new java.sql.Date(ogrenci.getGirisTarihi().getTime()) : null);
            statement.setDate(7, ogrenci.getCikisTarihi() != null ? new java.sql.Date(ogrenci.getCikisTarihi().getTime()) : null);
            statement.setInt(8, ogrenci.getId());
            statement.executeUpdate();
        }
    }

    public void silOgrenci(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SIL_OGRENCI_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Ogrenci getOgrenciById(int id) {
        Ogrenci ogrenci = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_OGRENCI_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String tckNo = rs.getString("tck_no");
                String kullaniciAdi = rs.getString("kullanici_adi");
                String sifre = rs.getString("sifre");
                String telefon = rs.getString("telefon");
                String adres = rs.getString("adres");
                Date girisTarihi = rs.getDate("giris_tarihi");
                Date cikisTarihi = rs.getDate("cikis_tarihi");
                ogrenci = new Ogrenci(id, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenci;
    }
}
