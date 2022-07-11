package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Model.Dao.ContactDAO;
import Model.Dao.UserDAO;
import Model.bean.User;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminUserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminUserSearchController() {
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
		UserDAO userdao = new UserDAO();
		int trang = 1;
		if(!request.getParameter("user").equals("")) {
			String name = request.getParameter("user");
			if(request.getParameter("trang") != null) {
				try {
					trang = Integer.parseInt(request.getParameter("trang"));
				}catch(Exception e) {
					request.setAttribute("err", 1);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/User/SearchUser.jsp");
					rd.forward(request, response);
					return;
				}
			}
			ArrayList<User> list = userdao.getListUserPage(trang, name);
			if(list.size()==0) {
				request.setAttribute("err", 1);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/User/SearchUser.jsp");
				rd.forward(request, response);
				return;
			}
			int sotrang = userdao.SoTrang(name, DefineUtil.NUMBER_PER_PAGE1);
			request.setAttribute("trang", trang);
			request.setAttribute("sotrang",sotrang);
			request.setAttribute("listuser",list );
			request.setAttribute("user", name);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/User/SearchUser.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
