package Controller;

import DAO.OgrenciDAO;
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

@WebServlet("/OgrenciServlet")
public class OgrenciServlet extends HttpServlet {
    private OgrenciDAO ogrenciDAO;

    @Override
    public void init() {
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
                case "guncelle":
                    guncelleForm(request, response);
                    break;
                case "sil":
                    silOgrenci(request, response);
                    break;
                case "ekle":
                    ekleForm(request, response);
                    break;
                default:
                    listOgrenci(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listOgrenci(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Ogrenci> listOgrenci = ogrenciDAO.getAllOgrenci();
        request.setAttribute("listOgrenci", listOgrenci);
        request.getRequestDispatcher("ogrenciAnaSayfa.jsp").forward(request, response);
    }

    private void ekleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ogrenci.jsp").forward(request, response);
    }

    private void guncelleForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Ogrenci existingOgrenci = ogrenciDAO.getOgrenciById(id);
        request.setAttribute("ogrenci", existingOgrenci);
        request.getRequestDispatcher("ogrenci.jsp").forward(request, response);
    }

    private void silOgrenci(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ogrenciDAO.silOgrenci(id);
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
                    ekleOgrenci(request, response);
                    break;
                case "guncelle":
                    guncelleOgrenci(request, response);
                    break;
                default:
                    listOgrenci(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void ekleOgrenci(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String tck_no = request.getParameter("tck_no");
        String kullaniciAdi = request.getParameter("kullaniciAdi");
        String sifre = request.getParameter("sifre");
        String telefon = request.getParameter("telefon");
        String adres = request.getParameter("adres");
        Date girisTarihi = parseDate(request.getParameter("girisTarihi"));
        String cikisTarihiStr = request.getParameter("cikisTarihi");
        Date cikisTarihi = (cikisTarihiStr == null || cikisTarihiStr.isEmpty()) ? null : parseDate(cikisTarihiStr);

        Ogrenci newOgrenci = new Ogrenci(0, tck_no, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
        ogrenciDAO.ekleOgrenci(newOgrenci);
        response.sendRedirect("AdminServlet");
    }

    private void guncelleOgrenci(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tck_no = request.getParameter("tck_no");
        String kullaniciAdi = request.getParameter("kullaniciAdi");
        String sifre = request.getParameter("sifre");
        String telefon = request.getParameter("telefon");
        String adres = request.getParameter("adres");
        Date girisTarihi = parseDate(request.getParameter("girisTarihi"));
        String cikisTarihiStr = request.getParameter("cikisTarihi");
        Date cikisTarihi = (cikisTarihiStr == null || cikisTarihiStr.isEmpty()) ? null : parseDate(cikisTarihiStr);

        Ogrenci guncelOgrenci = new Ogrenci(id, tck_no, kullaniciAdi, sifre, telefon, adres, girisTarihi, cikisTarihi);
        ogrenciDAO.guncelleOgrenci(guncelOgrenci);
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
