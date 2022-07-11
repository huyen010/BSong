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
import Model.Dao.SongDAO;
import Model.bean.Song;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminSongSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSongSearchController() {
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
		if(!request.getParameter("song").equals("")) {
			String name = request.getParameter("song");
			SongDAO songdao = new SongDAO();
			int trang = 1;
			request.setAttribute("song", name);
			if(request.getParameter("trang") != null) {
				try {
					trang = Integer.parseInt(request.getParameter("trang"));
				}catch(Exception e) {
					request.setAttribute("err","1");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/SearchSong.jsp");
					rd.forward(request, response);
				}
			}
			ArrayList<Song> list = songdao.Search(0, name, trang);
			if(list.size()==0) {
				request.setAttribute("err","1");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/SearchSong.jsp");
				rd.forward(request, response);
				return ;
			}
			int sotrang = songdao.SoTrang(name, 0, DefineUtil.NUMBER_PER_PAGE1);
			request.setAttribute("trang", trang);
			request.setAttribute("sotrang",sotrang);
			request.setAttribute("listsong",list );
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/SearchSong.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
