package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.CategoryDAO;
import utils.AuthUtil;

public class AdminInsertCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminInsertCatController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/addCat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		CategoryDAO catedao = new CategoryDAO();
		if (request.getParameter("name") != null) {
			String name = request.getParameter("name");
			if (name.equals("")) {
				request.setAttribute("name", name);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/addCat.jsp");
				rd.forward(request, response);
				return;
			}
			if (catedao.insertCate(name) == 1) {
				response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=" + "2");
			} else {
				request.setAttribute("name", name);
				request.setAttribute("error", 2);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/addCat.jsp");
				rd.forward(request, response);
			}

		}
	}
	// lạ lùng nhỉ, có post đây, mà nó bảo ko có

}
