package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.CategoryDAO;
import Model.bean.category;
import utils.AuthUtil;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminEditCatController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response) ) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		if(request.getParameter("idcate") != null) {
			int idcate = Integer.parseInt(request.getParameter("idcate"));
			CategoryDAO catedao = new CategoryDAO();
			request.setAttribute("cate", catedao.getCateGoryByID(idcate));
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/EditCat.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("idcate") != null) {
			int idcate = Integer.parseInt(request.getParameter("idcate"));
			String name = request.getParameter("name");
			CategoryDAO catedao = new CategoryDAO();
			category cate = new category(idcate, name);
			if(catedao.UpdateCate(cate)==1) {
				response.sendRedirect(request.getContextPath() + "/Admin/cat/index?msg=1");
			}else {
				request.setAttribute("err", 1);
				RequestDispatcher rd = request.getRequestDispatcher("admin/Cat/addCat.jsp");
				rd.forward(request, response);	
			}
		}
	}

}
