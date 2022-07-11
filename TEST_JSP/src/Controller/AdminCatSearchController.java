 package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.CategoryDAO;
import Model.bean.category;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminCatSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminCatSearchController() {
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
		if(!request.getParameter("cate").equals("")) {
			String name = request.getParameter("cate");
			CategoryDAO catedao = new CategoryDAO();
			int trang = 1;
			request.setAttribute("cate", name);
			if(request.getParameter("trang") != null) {
				try {
					trang = Integer.parseInt(request.getParameter("trang"));
				}catch(Exception e) {
					request.setAttribute("err", "1");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/SearchCat.jsp");
					rd.forward(request, response);
					return;
				}
			}
			ArrayList<category> list = catedao.getListCatePage(trang,name);
			if(list.size()==0) {
				request.setAttribute("err", "1");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/SearchCat.jsp");
				rd.forward(request, response);
				return ;
			}
			int sotrang = catedao.SoTrang(name, DefineUtil.NUMBER_PER_PAGE1);
			request.setAttribute("trang", trang);
			request.setAttribute("sotrang",sotrang);
			request.setAttribute("listcate", list);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Cat/SearchCat.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
