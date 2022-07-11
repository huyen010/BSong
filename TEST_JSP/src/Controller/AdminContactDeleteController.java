package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.ContactDAO;
import utils.AuthUtil;

public class AdminContactDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminContactDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response) ) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		ContactDAO ctdao = new ContactDAO();
		if(request.getParameter("idcontact") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("idcontact"));
				if(ctdao.getContactbyID(id) == null) {
					response.sendRedirect(request.getContextPath() + "/Admin/conctact/index?error=" +"2");
				}
				if(ctdao.DeleteContact(id) == 1) {
					response.sendRedirect(request.getContextPath() + "/Admin/contact/index?msg=" + "1");
				}else {
					response.sendRedirect(request.getContextPath() + "/Admin/conctact/index?error=" + "1" );
				}
			}catch(Exception e) {
				response.sendRedirect(request.getContextPath() + "/Admin/contact/index?error=" +"2");
			}
		}
	}

}
