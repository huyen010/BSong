package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import Model.Dao.UserDAO;
import Model.bean.User;
import utils.AuthUtil;

public class AdminUserInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminUserInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response) ) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userlogin");
		if(user.getRole()==1) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/User/addUser.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath()+"/Admin/user/index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		int role = 0 ;
		try {
			role = Integer.parseInt(request.getParameter("role"));
		}
		catch(Exception e) {
			role = 0;
		}
		User user = new User(0, name, password, fullname, role);
		UserDAO userdao = new UserDAO();
		if(user.getFullname().equals("") || user.getPassword().equals("") || user.getUsername().equals("") || user.getRole()==0) {
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/User/addUser.jsp");
			rd.forward(request, response);
			return;
		}
		if(userdao.insertUser(user)==1) {
			response.sendRedirect(request.getContextPath()+"/Admin/user/index?msg=" + "1");
		}else {
			request.setAttribute("user", user);
			request.setAttribute("error", 1);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/User/addUser.jsp");
			rd.forward(request, response);
		}
	}

}
