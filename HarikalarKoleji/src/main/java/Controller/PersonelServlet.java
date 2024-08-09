package Controller;

import DAO.PersonelDAO;
import Entity.Personel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PersonelServlet")
public class PersonelServlet extends HttpServlet {
    private PersonelDAO personelDAO;

    @Override
    public void init() {
        personelDAO = new PersonelDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String islem = request.getParameter("islem");
        if (islem == null) {
            islem = "";
        }
        try {
            switch (islem) {
                case "guncelle":
                    guncelleForm(request, response);
                    break;
                case "sil":
                    silPersonel(request, response);
                    break;
                case "ekle":
                    ekleForm(request, response);
                    break;
                default:
                    listPersonel(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listPersonel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Personel> listPersonel = personelDAO.getAllPersonel();
        request.setAttribute("listPersonel", listPersonel);
        request.getRequestDispatcher("personelAnaSayfa.jsp").forward(request, response);
    }

    private void ekleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("personel.jsp").forward(request, response);
    }

    private void guncelleForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Personel existingPersonel = personelDAO.getPersonelById(id);
        request.setAttribute("personel", existingPersonel);
        request.getRequestDispatcher("personel.jsp").forward(request, response);
    }

    private void silPersonel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        personelDAO.silPersonel(id);
        response.sendRedirect("AdminServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String islem = request.getParameter("islem");
        if (islem == null) {
            islem = "";
        }
        try {
            switch (islem) {
                case "ekle":
                    eklePersonel(request, response);
                    break;
                case "guncelle":
                    guncellePersonel(request, response);
                    break;
                default:
                    listPersonel(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void eklePersonel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String tckNo = request.getParameter("tck_no");
        String kullaniciAdi = request.getParameter("kullaniciAdi");
        String sifre = request.getParameter("sifre");
        String telefon = request.getParameter("telefon");
        String adres = request.getParameter("adres");
        Date girisTarihi = parseDate(request.getParameter("giris_tarihi"));
        Date cikisTarihi = parseDate(request.getParameter("cikis_tarihi"));

        Personel newPersonel = new Personel(0, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
        personelDAO.eklePersonel(newPersonel);
        response.sendRedirect("AdminServlet");
    }

    private void guncellePersonel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tckNo = request.getParameter("tck_no");
        String kullaniciAdi = request.getParameter("kullaniciAdi");
        String sifre = request.getParameter("sifre");
        String telefon = request.getParameter("telefon");
        String adres = request.getParameter("adres");
        Date girisTarihi = parseDate(request.getParameter("giris_tarihi"));
        
        String cikisTarihiStr = request.getParameter("cikis_tarihi");
        Date cikisTarihi = (cikisTarihiStr == null || cikisTarihiStr.isEmpty()) ? null : parseDate(cikisTarihiStr);

        Personel guncelPersonel = new Personel(id, tckNo, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
        personelDAO.guncellePersonel(guncelPersonel);
        response.sendRedirect("AdminServlet");
    }

    private Date parseDate(String dateStr) {
        try {
            if (dateStr != null && !dateStr.isEmpty()) {
                return Date.valueOf(dateStr);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
          
        }
        return null;
    }
}
