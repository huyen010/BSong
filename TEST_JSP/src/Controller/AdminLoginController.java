package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Dao.UserDAO;
import Model.bean.User;
import utils.AuthUtil;
import utils.StringUtil;

public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(AuthUtil.checkLogin(request, response) ) {
			response.sendRedirect(request.getContextPath()+"/Admin/index");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/auth/Login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User userlog =(User) session.getAttribute("userlogin");
		if(userlog != null) {
			response.sendRedirect(request.getContextPath()+"/Admin/index");
			return;
		}
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		pass = StringUtil.md5(pass);
		if(name.equals("") || pass.equals("")) {
			request.setAttribute("name", name);
			request.setAttribute("pass", pass);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/auth/Login.jsp");
			rd.forward(request, response);
			return;
		}
		User userlogin = new UserDAO().getUser(name, pass);
		if(userlogin == null) {
			request.setAttribute("name", name);
			request.setAttribute("pass", pass);
			request.setAttribute("err", "1");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/auth/Login.jsp");
			rd.forward(request, response);
		}else {
			session.setAttribute("userlogin", userlogin);
			response.sendRedirect(request.getContextPath()+"/Admin/index");
		}
	}

}
