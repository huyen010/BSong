package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.ContactDAO;
import Model.Dao.SongDAO;
import Model.bean.Contact;
import utils.AuthUtil;
import utils.DefineUtil;


public class AdminContactSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminContactSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response) ) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		ContactDAO contactdao = new ContactDAO();
		int trang = 1;
		if(!request.getParameter("contact").equals("")) {
			String name = request.getParameter("contact");
			System.out.println(name);
			if(request.getParameter("trang") != null) {
				try {
					trang = Integer.parseInt(request.getParameter("trang"));
				}catch(Exception e) {
					request.setAttribute("err", "1");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/Contact/Searchcontact.jsp");
					rd.forward(request, response);
				}
			}
			ArrayList<Contact> list =  contactdao.getListContactPage(trang, name);
			if(list.size()==0) {
				request.setAttribute("err", "1");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/Contact/Searchcontact.jsp");
				rd.forward(request, response);
				return ;
			}
			int sotrang = contactdao.SoTrang(name, DefineUtil.NUMBER_PER_PAGE1);
			request.setAttribute("trang", trang);
			request.setAttribute("sotrang",sotrang);
			request.setAttribute("listcontact",list);
			request.setAttribute("contact", name);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Contact/Searchcontact.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
