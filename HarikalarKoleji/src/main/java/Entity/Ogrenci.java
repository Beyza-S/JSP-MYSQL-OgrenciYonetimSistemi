package Entity;

import java.util.Date;

public class Ogrenci {
    private int id;
    private String tck_no;
    private String kullaniciAdi;
    private String sifre;
    private String telefon;
    private String adres;
    private Date girisTarihi;
    private Date cikisTarihi;


    public Ogrenci() {
    }

    public Ogrenci(int id, String tck_no, String kullaniciAdi, String sifre, String telefon, String adres, Date girisTarihi, Date cikisTarihi) {
        this.id = id;
        this.tck_no = tck_no;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.telefon = telefon;
        this.adres = adres;
        this.girisTarihi = girisTarihi;
        this.cikisTarihi = cikisTarihi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTck_no() {
        return tck_no;
    }

    public void setTck_no(String tck_no) {
        this.tck_no = tck_no;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Date getGirisTarihi() {
        return girisTarihi;
    }

    public void setGirisTarihi(Date girisTarihi) {
        this.girisTarihi = girisTarihi;
    }

    public Date getCikisTarihi() {
        return cikisTarihi;
    }

    public void setCikisTarihi(Date cikisTarihi) {
        this.cikisTarihi = cikisTarihi;
    }
}
