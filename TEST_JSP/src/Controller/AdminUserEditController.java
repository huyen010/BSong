package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Dao.UserDAO;
import Model.bean.User;
import utils.AuthUtil;
import utils.StringUtil;

public class AdminUserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminUserEditController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		UserDAO userdao = new UserDAO();
		HttpSession session = request.getSession();
		User userlogin = (User) session.getAttribute("userlogin");
		if (request.getParameter("iduser") != null) {
			try {
				int iduser = Integer.parseInt(request.getParameter("iduser"));
				if (userlogin.getRole() == 2 && userlogin.getId() != iduser) {
					response.sendRedirect(request.getContextPath() + "/Admin/user/index?error=" + "4");
					return;
				}
				User user = userdao.getUserbyID(iduser);
				if (user == null) {
					response.sendRedirect("Admin/user/index?error=2");
				}
				request.setAttribute("user", user);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/User/EditUser.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/Admin/user/index?error=2");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		UserDAO userdao = new UserDAO();
		int role = 0;
		try {
			role = Integer.parseInt(request.getParameter("role"));
		} catch (Exception e) {
			role = 0;
		}
		int iduser = Integer.parseInt(request.getParameter("iduser"));
		User user = userdao.getUserbyID(iduser);
		user.setFullname(request.getParameter("fullname"));
		if (!request.getParameter("password").equals("")) {
			String pass = StringUtil.md5(request.getParameter("password"));
			user.setPassword(pass);
			System.out.println(pass); // pass nó mã hoá thành công rồi
		}
		user.setRole(role);
		if (user.getFullname().equals("") || user.getRole() == 0) {
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/User/EditUser.jsp");
			rd.forward(request, response);
			return;
		}

		if (userdao.UpdateUser(user) > 0) { 
			response.sendRedirect(request.getContextPath() + "/Admin/user/index?msg=2");
		} else {
			request.setAttribute("user", user);
			request.setAttribute("err", 1);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/User/EditUser.jsp");
			rd.forward(request, response);
		}
	}

}
