package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Dao.ContactDAO;
import Model.Dao.UserDAO;
import Model.bean.User;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminUserIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AdminUserIndexController() {
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
		UserDAO userdao = new UserDAO();
		int trang = 1;
		try {
			if (request.getParameter("trang") != null) {
				trang = Integer.parseInt(request.getParameter("trang"));
			}
			ArrayList<User> list = userdao.getListUserPage(trang, "");
			if (list.size() == 0) {
				request.setAttribute("err", 1);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/User/indexUser.jsp");
				rd.forward(request, response);
				return;
			}
			String qr = "SELECT COUNT(*) FROM users";
			int sotrang = userdao.SoTrang("", DefineUtil.NUMBER_PER_PAGE1);
			request.setAttribute("trang", trang);
			request.setAttribute("sotrang", sotrang);
			request.setAttribute("listuser", list);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/User/indexUser.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("err", 1);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/User/indexUser.jsp");
			rd.forward(request, response);
		} 
	
	}

}
