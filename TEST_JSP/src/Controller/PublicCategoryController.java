package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.CategoryDAO;
import Model.Dao.SongDAO;
import Model.bean.Song;
import Model.bean.category;
import utils.AuthUtil;
import utils.DefineUtil;

public class PublicCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicCategoryController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SongDAO songdao = new SongDAO();
		if (request.getParameter("idcate") != null) {
			try {
				int trang = 1;
				int iddm = Integer.parseInt(request.getParameter("idcate"));
				if (request.getParameter("trang") != null) {
					trang = Integer.parseInt(request.getParameter("trang"));
				}
				String name = "";
				if (request.getParameter("name") != null) {
					name = request.getParameter("name");
					request.setAttribute("name", name);
				}
				ArrayList<Song> list = songdao.Search(iddm, name, trang);
				if (list.size() == 0) {
					request.setAttribute("msg", 1);
					RequestDispatcher rd = request.getRequestDispatcher("/public/cat.jsp");
					rd.forward(request, response);
					return;
				}
				CategoryDAO catedao = new CategoryDAO();
				int sotrang = songdao.SoTrang(name,iddm, DefineUtil.NUMBER_PER_PAGE2);
				
				request.setAttribute("trang", trang);
				request.setAttribute("sotrang", sotrang);
				request.setAttribute("danhmuc", catedao.getCateGoryByID(iddm));
				request.setAttribute("listsong1", list);
				request.setAttribute("idcate", iddm);
				RequestDispatcher rd = request.getRequestDispatcher("/public/cat.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath()+"/404");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
