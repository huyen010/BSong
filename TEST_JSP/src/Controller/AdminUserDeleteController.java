package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Dao.UserDAO;
import Model.bean.User;
import utils.AuthUtil;

public class AdminUserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminUserDeleteController() {
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
		UserDAO userdao = new UserDAO();
		HttpSession session = request.getSession();
		User userlogin = (User)session.getAttribute("userlogin");
		if(request.getParameter("iduser") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("iduser"));
				if(userlogin.getRole()==2 && userlogin.getId() != id) {
					response.sendRedirect(request.getContextPath() + "/Admin/user/index?error=" + "3");
					return;
				}
				if(userdao.getUserbyID(id)==null) {
					response.sendRedirect(request.getContextPath() + "/Admin/user/index?error=2");
				}
				if(userdao.DeleteUser(id)==1) {
					response.sendRedirect(request.getContextPath() + "/Admin/user/index?msg=" + "3");
				}else {
					response.sendRedirect(request.getContextPath() + "/Admin/user/index?error=" + "1");
				}
			}catch(Exception e) {
				response.sendRedirect(request.getContextPath() + "/Admin/user/index?error=" + "2");
			}
		}
	}

}
