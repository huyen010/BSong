package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.SongDAO;
import Model.Dao.UserDAO;
import Model.bean.Song;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminSongIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSongIndexController() {
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
		SongDAO songdao = new SongDAO();
		int trang = 1;
		try {
			if(request.getParameter("trang") != null ) {
				trang = Integer.parseInt(request.getParameter("trang"));
			}
			ArrayList<Song> list = songdao.getListSongPage(trang, 0);
			if (list.size() == 0) {
				request.setAttribute("err", 1);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/indexSong.jsp");
				rd.forward(request, response);
				return;
			}
			int sotrang = songdao.SoTrang("",0, DefineUtil.NUMBER_PER_PAGE2);
			request.setAttribute("trang", trang);
			request.setAttribute("sotrang", sotrang);
			request.setAttribute("listsong", list);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/indexSong.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("err", 1);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/indexSong.jsp");
			rd.forward(request, response);
		}

	}

}
