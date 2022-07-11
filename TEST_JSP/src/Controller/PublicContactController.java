package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.ContactDAO;
import Model.bean.Contact;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicContactController() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		Contact contact = new Contact(0, "", "", "", "");
		if(request.getParameter("name") != null) {
			contact.setName(request.getParameter("name")); 
		}
		if(request.getParameter("email") != null) {
			contact.setEmail(request.getParameter("email"));
		}
		if(request.getParameter("website") != null) {
			contact.setWebsite(request.getParameter("website"));;
		}
		if(request.getParameter("message") != null) {
			contact.setMessage(request.getParameter("message"));
		}
		ContactDAO ctdao = new ContactDAO();
		if(contact.getName().equals("") || contact.getEmail().equals("") || contact.getMessage().equals("") || contact.getWebsite().equals("")) {
			request.setAttribute("contact", contact);
			RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp");
			rd.forward(request, response);
			return;
		}
		if(ctdao.insertContact(contact)==1) {
			RequestDispatcher rd = request.getRequestDispatcher("/public/contactE.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("msg", "1");
			request.setAttribute("contact", contact);
			RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp");
			rd.forward(request, response);
		}
	}

}
