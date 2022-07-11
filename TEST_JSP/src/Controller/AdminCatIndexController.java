package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.CategoryDAO;
import Model.bean.category;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminCatIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminCatIndexController() {
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
		CategoryDAO catedao = new CategoryDAO();
		int trang = 1;
		try {
			if (request.getParameter("trang") != null) {
				trang = Integer.parseInt(request.getParameter("trang"));
			}
			ArrayList<category> listcate = catedao.getListCatePage(trang, "");
			if (listcate.size() == 0) {
				request.setAttribute("err", 1);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/indexCat.jsp");
				rd.forward(request, response);
				return;
			}
			int sotrang = catedao.SoTrang("", DefineUtil.NUMBER_PER_PAGE1);
			request.setAttribute("trang", trang);
			request.setAttribute("sotrang", sotrang);
			request.setAttribute("listcate", listcate);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/indexCat.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("err", 2);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/indexCat.jsp");
			rd.forward(request, response);
		}
	}

}
