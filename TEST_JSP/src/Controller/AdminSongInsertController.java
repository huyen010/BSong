package Controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

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
public class AdminSongInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSongInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response) ) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		CategoryDAO catedao = new CategoryDAO();
		request.setAttribute("listcate", catedao.getListCateGory());
		RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/addSong.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		SongDAO songdao = new SongDAO();
		CategoryDAO catedao = new CategoryDAO();
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		String fileName = "";
		int idcate = 0 ;
		try {
			idcate = Integer.parseInt(request.getParameter("category"));
		}catch(Exception e) {
			idcate = 0;
		}
		FileUtil fileut = new FileUtil();
		if(request.getPart("picture") != null ) {
			Part filePart = request.getPart("picture");
			fileName = fileut.getName(filePart);
			System.out.println(fileName);
			if (!fileName.equals("")) {
				fileName = fileut.rename(fileName);
				System.out.println(fileName);
				String dirPath = request.getServletContext().getRealPath("") + "template/public/images";
				File saveDir = new File(dirPath);
				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}
				String filePath = dirPath + File.separator + fileName;
				filePart.write(filePath);
			}
		}
		Song song = new Song(0, name, preview, detail, null, idcate, fileName,0);
		request.setAttribute("song", song);
		request.setAttribute("listcate", catedao.getListCateGory());
		if(song.getName().equals("") || song.getPreview().equals("") || song.getDetail().equals("") || song.getId_cat()==0 || song.getPicutre().equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/addSong.jsp");
			rd.forward(request, response);
			return ;
		}
		
		if(songdao.insertSong(song)==1) {
			response.sendRedirect(request.getContextPath()+"/Admin/song/index?msg=" + "1");
		}else {
			request.setAttribute("err", 1);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/Song/addSong.jsp");
			rd.forward(request, response);
		}
	}

}
