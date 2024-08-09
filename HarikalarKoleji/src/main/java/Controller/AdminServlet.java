package Controller;

import DAO.PersonelDAO;
import DAO.OgrenciDAO;
import Entity.Personel;
import Entity.Ogrenci;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private PersonelDAO personelDAO;
    private OgrenciDAO ogrenciDAO;

    @Override
    public void init() {
        personelDAO = new PersonelDAO();
        ogrenciDAO = new OgrenciDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String islem = request.getParameter("islem");
        if (islem == null) {
            islem = "";
        }

        try {
            switch (islem) {
                case "silPersonel":
                    silPersonel(request, response);
                    break;
                case "silOgrenci":
                    silOgrenci(request, response);
                    break;
                default:
                    listAll(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String islem = request.getParameter("islem");
        if (islem == null) {
            islem = "";
        }

        try {
            if ("eklePersonel".equals(islem)) {
                eklePersonel(request, response);
            } else if ("ekleOgrenci".equals(islem)) {
                ekleOgrenci(request, response);
            } else if ("guncellePersonel".equals(islem)) {
                guncellePersonel(request, response);
            } else if ("guncelleOgrenci".equals(islem)) {
                guncelleOgrenci(request, response);
            } else {
                doGet(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Personel> personeller = personelDAO.getAllPersonel();
        List<Ogrenci> ogrenciler = ogrenciDAO.getAllOgrenci();
        request.setAttribute("personeller", personeller);
        request.setAttribute("ogrenciler", ogrenciler);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void eklePersonel(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String tckNo = request.getParameter("tck_no");
        String kullaniciAdi = request.getParameter("kullanici_adi");
        String sifre = request.getParameter("sifre");
        String telefon = request.getParameter("telefon");
        String adres = request.getParameter("adres");
        Date girisTarihi = Date.valueOf(request.getParameter("giris_tarihi"));
        Date cikisTarihi = request.getParameter("cikis_tarihi") == null ? null : Date.valueOf(request.getParameter("cikis_tarihi"));

        Personel personel = new Personel(0, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
        personelDAO.eklePersonel(personel);
        response.sendRedirect("AdminServlet");
    }

    private void ekleOgrenci(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String tckNo = request.getParameter("tck_no");
        String kullaniciAdi = request.getParameter("kullanici_adi");
        String sifre = request.getParameter("sifre");
        String telefon = request.getParameter("telefon");
        String adres = request.getParameter("adres");
        Date girisTarihi = Date.valueOf(request.getParameter("giris_tarihi"));
        Date cikisTarihi = request.getParameter("cikis_tarihi") == null ? null : Date.valueOf(request.getParameter("cikis_tarihi"));

        Ogrenci ogrenci = new Ogrenci(0, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
        ogrenciDAO.ekleOgrenci(ogrenci);
        response.sendRedirect("AdminServlet");
    }

    private void guncellePersonel(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tckNo = request.getParameter("tck_no");
        String kullaniciAdi = request.getParameter("kullanici_adi");
        String sifre = request.getParameter("sifre");
        String telefon = request.getParameter("telefon");
        String adres = request.getParameter("adres");
        Date girisTarihi = Date.valueOf(request.getParameter("giris_tarihi"));
        Date cikisTarihi = request.getParameter("cikis_tarihi") == null ? null : Date.valueOf(request.getParameter("cikis_tarihi"));

        Personel updatedPersonel = new Personel(id, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
        personelDAO.guncellePersonel(updatedPersonel);
        response.sendRedirect("AdminServlet");
    }

    private void guncelleOgrenci(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tckNo = request.getParameter("tck_no");
        String kullaniciAdi = request.getParameter("kullanici_adi");
        String sifre = request.getParameter("sifre");
        String telefon = request.getParameter("telefon");
        String adres = request.getParameter("adres");
        Date girisTarihi = Date.valueOf(request.getParameter("giris_tarihi"));
        Date cikisTarihi = request.getParameter("cikis_tarihi") == null ? null : Date.valueOf(request.getParameter("cikis_tarihi"));

        Ogrenci guncelOgrenci = new Ogrenci(id, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
        ogrenciDAO.guncelleOgrenci(guncelOgrenci);
        response.sendRedirect("AdminServlet");
    }

    private void silPersonel(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        personelDAO.silPersonel(id);
        response.sendRedirect("AdminServlet");
    }

    private void silOgrenci(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ogrenciDAO.silOgrenci(id);
        response.sendRedirect("AdminServlet");
    }
}
