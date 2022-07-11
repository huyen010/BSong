package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.CategoryDAO;
import utils.AuthUtil;

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDeleteCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response) ) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		CategoryDAO catedao = new CategoryDAO();
		if(request.getParameter("idcate") != null) {
			try {
				int idcate = Integer.parseInt(request.getParameter("idcate"));
				if(catedao.getCateGoryByID(idcate)==null) {
					response.sendRedirect(request.getContextPath() + "/Admin/cat/index?error=2");
					return;
				}

				if(catedao.DeleteCate(idcate)==1) {
					response.sendRedirect(request.getContextPath() + "/Admin/cat/index?msg=" + "1");
				}else {
					response.sendRedirect(request.getContextPath() + "/Admin/cat/index?error=" + "1");
				}
			}
			catch(Exception e) {
				response.sendRedirect(request.getContextPath() + "/Admin/cat/index?error=" + "2");
			}
		}
	}

}
