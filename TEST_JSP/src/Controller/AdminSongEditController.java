package Controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.Dao.CategoryDAO;
import Model.Dao.SongDAO;
import Model.bean.Song;
import utils.AuthUtil;
import utils.FileUtil;

@MultipartConfig
public class AdminSongEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminSongEditController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response) ) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		CategoryDAO catedao = new CategoryDAO();
		SongDAO songdao = new SongDAO();
		if (request.getParameter("idsong") != null) {
			try {
				int idsong = Integer.parseInt(request.getParameter("idsong"));
				Song song = songdao.getSongById(idsong);
				if (song == null) {
					response.sendRedirect(request.getContextPath() + "/Admin/song/index?error=3");
				} else {
					request.setAttribute("song", song);
					request.setAttribute("listcate", catedao.getListCateGory());
					RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/editSong.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/Admin/song/index?error=3");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		CategoryDAO catedao = new CategoryDAO();
		SongDAO songdao = new SongDAO();
		if (request.getParameter("idsong") != null) {
			int idsong = Integer.parseInt(request.getParameter("idsong"));
			Song song = songdao.getSongById(idsong);

			song.setName(request.getParameter("name"));
			song.setPreview(request.getParameter("preview"));
			song.setDetail(request.getParameter("detail"));
			int idcat = 0;
			try {
				idcat = Integer.parseInt(request.getParameter("category"));
			} catch (Exception e) {
				idcat = 0;
			}
			song.setId_cat(idcat);
			FileUtil fileut = new FileUtil();
			if (request.getPart("picture") != null) {
				Part filePart = request.getPart("picture");
				String picture = fileut.getName(filePart);
				if (!picture.equals("")) {
					System.out.println("hi");
					picture = fileut.rename(picture);
					String dirPath = request.getServletContext().getRealPath("") + "template/public/images";
					File saveDir = new File(dirPath);
					if (!saveDir.exists()) {
						saveDir.mkdirs();
					}
					String filePath = dirPath + File.separator + picture;
					filePart.write(filePath);
					song.setPicutre(picture);
				}
			}
			if (request.getParameter("name").equals("") || request.getParameter("preview").equals("")
					|| request.getParameter("detail").equals("") || request.getParameter("category").equals("")) {
				request.setAttribute("song", song);
				request.setAttribute("listcate", catedao.getListCateGory());
				RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/editSong.jsp");
				rd.forward(request, response);
				return;
			} else {
				if (songdao.UpdateSong(song) == 1) {
					response.sendRedirect(request.getContextPath() + "/Admin/song/index?msg=2");
				} else {
					request.setAttribute("listcate", catedao.getListCateGory());
					request.setAttribute("song", song);
					request.setAttribute("error", "1");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/editSong.jsp");
					rd.forward(request, response);
				}
			}
		}

	}

}
