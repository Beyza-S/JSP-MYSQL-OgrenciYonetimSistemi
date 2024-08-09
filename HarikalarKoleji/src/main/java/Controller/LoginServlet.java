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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private PersonelDAO personelDAO;
    private OgrenciDAO ogrenciDAO;

    @Override
    public void init() {
        personelDAO = new PersonelDAO();
        ogrenciDAO = new OgrenciDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kullaniciAdi = request.getParameter("kullaniciAdi");
        String sifre = request.getParameter("sifreforLogin");

        Personel personel = personelDAO.dogrulaPersonel(kullaniciAdi, sifre);
        Ogrenci ogrenci = ogrenciDAO.dogrulaOgrenci(kullaniciAdi, sifre);

        if (personel != null) {
            if (personel.getKullaniciAdi().equals("Beyza")) {
                request.setAttribute("personel", personel);
                response.sendRedirect("AdminServlet");
            } else {
                request.setAttribute("personel", personel);
                request.getRequestDispatcher("personelAnaSayfa.jsp").forward(request, response);
            }
        } else if (ogrenci != null) {
            request.setAttribute("ogrenci", ogrenci);
            request.getRequestDispatcher("ogrenciAnaSayfa.jsp").forward(request, response);
        } else {
            System.out.println("Giriş başarısız!");
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
