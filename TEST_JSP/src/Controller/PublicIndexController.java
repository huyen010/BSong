package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.SongDAO;
import Model.bean.Song;
import utils.DefineUtil;


public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicIndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SongDAO songdao = new SongDAO();
		int trang = 1;
		if(request.getParameter("trang") != null) {
			trang = Integer.parseInt(request.getParameter("trang"));
		}
		String name = "";
		if(request.getParameter("name") != null ) {
			name = request.getParameter("name");
			request.setAttribute("name",name);
		}
		ArrayList<Song> list = songdao.Search(0, name, trang);
		
		if(list.size()==0) {
			request.setAttribute("msg", 1);
			RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
			rd.forward(request, response);
			return;
		}
		int sotrang = songdao.SoTrang(name,0, DefineUtil.NUMBER_PER_PAGE2);
		request.setAttribute("idcate", 0);
		request.setAttribute("trang", trang);
		request.setAttribute("sotrang",sotrang);
		request.setAttribute("listsong1",list);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);
//			try {
//				
//			}catch(Exception e) {
//				response.sendRedirect(request.getContextPath()+"/404");
//			}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
