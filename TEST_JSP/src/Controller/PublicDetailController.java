package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Dao.SongDAO;
import Model.bean.Song;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("name") != null) {
			String name = request.getParameter("name");
			response.sendRedirect(request.getContextPath() + "/Trang-chu?name="+name);
		}else {
			if(request.getParameter("idsong") != null) {
				try {
					int idsong = Integer.parseInt(request.getParameter("idsong"));
					SongDAO songdao = new SongDAO();
					Song song = songdao.getSongById(idsong);
					if(song==null) {
						response.sendRedirect(request.getContextPath()+"/404");
						return;
					}
					HttpSession session = request.getSession();
					String hasvisited =(String)session.getAttribute("hasvisited" + idsong);
					if(hasvisited == null) {
						session.setAttribute("hasvisited" + idsong, "yes");
						session.setMaxInactiveInterval(3600);
						songdao.UpdateCounter(song);
					}
					request.setAttribute("song",song);
					request.setAttribute("songrd",songdao.getSongRandom(song,2));
					RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
				    rd.forward(request, response);
				}catch(Exception e) {
					response.sendRedirect(request.getContextPath()+"/404");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
