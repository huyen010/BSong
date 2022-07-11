package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Dao.SongDAO;
import utils.AuthUtil;

public class AdminSongDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSongDeleteController() {
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
		if(request.getParameter("idsong") != null) {
			try {
				int idsong = Integer.parseInt(request.getParameter("idsong"));
				if(songdao.getSongById(idsong)==null) {
					response.sendRedirect(request.getContextPath() + "/Admin/song/index?error=" + "3");
				}
				if(songdao.DeleteSong(idsong)==1) {
					response.sendRedirect(request.getContextPath() + "/Admin/song/index?msg=" + "3");
				}else {
					response.sendRedirect(request.getContextPath() + "/Admin/song/index?error=" + "2");
				}
			}catch(Exception e) {
				response.sendRedirect(request.getContextPath() + "/Admin/song/index?error=" + "3");
			}
		}
	}

}
