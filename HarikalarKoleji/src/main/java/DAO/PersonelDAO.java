package DAO;

import Entity.Personel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.Veritabani.getConnection;
import Entity.Departman;
import Entity.PersonelDepartman;

public class PersonelDAO {

    private static final String DOGRU_PERSONEL = "SELECT p.id, p.tck_no, p.kullanici_adi, p.sifre, p.telefon, p.adres, p.giris_tarihi, p.cikis_tarihi, d.ad AS departman " +
            "FROM personel p " +
            "LEFT JOIN personel_departman pd ON p.id = pd.personel_id " +
            "LEFT JOIN departman d ON pd.departman_id = d.id " +
            "WHERE p.kullanici_adi = ? AND p.sifre = ?";

    private static final String SELECT_ALL_PERSONEL = "SELECT p.id, p.tck_no, p.kullanici_adi, p.sifre, p.telefon, p.adres, p.giris_tarihi, p.cikis_tarihi, d.ad AS departman, d.id AS departmanId " +
            "FROM personel p " +
            "LEFT JOIN personel_departman pd ON p.id = pd.personel_id " +
            "LEFT JOIN departman d ON pd.departman_id = d.id";

    private static final String SELECT_PERSONEL_BY_ID = "SELECT p.id, p.tck_no, p.kullanici_adi, p.sifre, p.telefon, p.adres, p.giris_tarihi, p.cikis_tarihi, d.ad AS departman " +
            "FROM personel p " +
            "LEFT JOIN personel_departman pd ON p.id = pd.personel_id " +
            "LEFT JOIN departman d ON pd.departman_id = d.id WHERE p.id = ?";

    private static final String EKLE_PERSONEL_SQL = "INSERT INTO personel (tck_no, kullanici_adi, sifre, telefon, adres, giris_tarihi, cikis_tarihi) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GUNCELLE_PERSONEL_SQL = "UPDATE personel SET tck_no = ?, kullanici_adi = ?, sifre = ?, telefon = ?, adres = ?, giris_tarihi = ?, cikis_tarihi = ? WHERE id = ?";

    private static final String SIL_PERSONEL_SQL = "DELETE FROM personel WHERE id = ?";

    public Personel dogrulaPersonel(String kullaniciAdi, String sifre) {
        Personel personel = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DOGRU_PERSONEL)) {
            preparedStatement.setString(1, kullaniciAdi);
            preparedStatement.setString(2, sifre);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String tckNo = rs.getString("tck_no");
                String telefon = rs.getString("telefon");
                String adres = rs.getString("adres");
                Date girisTarihi = rs.getDate("giris_tarihi");
                Date cikisTarihi = rs.getDate("cikis_tarihi");
                personel = new Personel(id, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
                System.out.println("Personel bulundu: " + kullaniciAdi);
            } else {
                System.out.println("Personel bulunamadı: " + kullaniciAdi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personel;
    }

    public List<Personel> getAllPersonel() {
        List<Personel> personeller = new ArrayList<>();
        List<Departman> departmanlar = new ArrayList<>();
        List<PersonelDepartman> personelDepartmanlar = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERSONEL)) {
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
                String departmanAdi= rs.getString("departman");
                int departmanID = rs.getInt("departmanID");
                
                Personel eklenecekPersonel = new Personel(id, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
                Departman eklenecekDepartman = new Departman(departmanID,departmanAdi);
                personeller.add(eklenecekPersonel);
                departmanlar.add(eklenecekDepartman);
                personelDepartmanlar.add(new PersonelDepartman(eklenecekPersonel,eklenecekDepartman));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personeller;
    }

    public void eklePersonel(Personel personel) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EKLE_PERSONEL_SQL)) {
            preparedStatement.setString(1, personel.getTck_no());
            preparedStatement.setString(2, personel.getKullaniciAdi());
            preparedStatement.setString(3, personel.getSifre());
            preparedStatement.setString(4, personel.getTelefon());
            preparedStatement.setString(5, personel.getAdres());
            preparedStatement.setDate(6, personel.getGirisTarihi() != null ? new java.sql.Date(personel.getGirisTarihi().getTime()) : null);
            preparedStatement.setDate(7, personel.getCikisTarihi() != null ? new java.sql.Date(personel.getCikisTarihi().getTime()) : null);
            preparedStatement.executeUpdate();
        }
    }

    public void guncellePersonel(Personel personel) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GUNCELLE_PERSONEL_SQL)) {
            statement.setString(1, personel.getTck_no());
            statement.setString(2, personel.getKullaniciAdi());
            statement.setString(3, personel.getSifre());
            statement.setString(4, personel.getTelefon());
            statement.setString(5, personel.getAdres());
            statement.setDate(6, personel.getGirisTarihi() != null ? new java.sql.Date(personel.getGirisTarihi().getTime()) : null);
            statement.setDate(7, personel.getCikisTarihi() != null ? new java.sql.Date(personel.getCikisTarihi().getTime()) : null);
            statement.setInt(8, personel.getId());
            statement.executeUpdate();
        }
    }

    public void silPersonel(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SIL_PERSONEL_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Personel getPersonelById(int id) {
        Personel personel = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSONEL_BY_ID)) {
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
                personel = new Personel(id, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personel;
    }
}
