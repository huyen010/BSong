package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.bean.Song;
import Model.bean.User;
import Model.bean.category;
import utils.DBConnectionUtil;
import utils.FileUtil;
import utils.StringUtil;

public class UserDAO {
	private Connection cnn ;
	private DBConnectionUtil connectdb;
	private Statement stm;
	private ResultSet rs;
	private PreparedStatement pst;
	public UserDAO() {
		connectdb = new DBConnectionUtil();
	}
	public User getUser(String name, String pass){
		User user = null;
		//pass = StringUtil.md5(pass);
		try {
			cnn = connectdb.getConnection();
			String qr = "SELECT * FROM users WHERE password = BINARY ? AND username = BINARY ?";
			pst = cnn.prepareStatement(qr);
			pst.setString(1, pass);
			pst.setString(2, name);
			rs = pst.executeQuery();
			while(rs.next()) {
				user = new User(rs.getInt("Id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"),rs.getInt("Role"));
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return user;
	}
	public int insertUser(User user) {
		int result = 0;
		//user.setPassword(StringUtil.md5(user.getPassword()));
		try {
			cnn = connectdb.getConnection();
			String qr = "INSERT INTO users(username,password,fullname,Role) VALUES (?,?,?,?)";
			pst = cnn.prepareStatement(qr);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setInt(4, user.getRole());
			result = pst.executeUpdate();
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(pst, cnn);
		}
		return result;
	}
	public int DeleteUser(int id) {
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "DELETE FROM users WHERE Id = ?";
			pst = cnn.prepareStatement(qr);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(pst, cnn);
		}
		return result;
	}
	public int UpdateUser(User user) {
		// do thằng dao
		// pass db để 30 ký tự, mà md5 nó > 30 nên ko add vào đc
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "UPDATE users SET username = ?, password = ?, fullname = ?, Role = ? where Id = ?";
			pst = cnn.prepareStatement(qr);
			pst = cnn.prepareStatement(qr);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setInt(4, user.getRole());
			pst.setInt(5, user.getId());
			result = pst.executeUpdate();
	
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(pst, cnn);
		}
		return result;
	}
	
	public ArrayList<User> getListUserPage(int page, String name){
		int x1 = (page-1) * 5;
		int x2 = 5;
		name = "%" + name + "%";
		ArrayList<User> list = new ArrayList<User>();
		try {
			cnn = connectdb.getConnection();
			String qr = "SELECT * FROM users WHERE fullname LIKE ? ORDER BY Id DESC LIMIT ?,?";
			pst = cnn.prepareStatement(qr);
			pst.setString(1, name);
			pst.setInt(2, x1);
			pst.setInt(3, x2);
			rs = pst.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("Id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("Role"));
				list.add(user);
				}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return list;
	}
	public User getUserbyID(int id) {
		User user = null;
		try {
			cnn = connectdb.getConnection();
			String qr = "SELECT * FROM users WHERE Id =?";
			pst = cnn.prepareStatement(qr);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()){
				user = new User(rs.getInt("Id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("Role"));
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return user;
	}
	public int SoTrang(String name, int a) {
		name = "%" + name + "%";
		int i = 0;
		try {
			String qr = "SELECT COUNT(*) FROM users where fullname like ?";
			cnn = connectdb.getConnection();
			pst = cnn.prepareStatement(qr);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while(rs.next()) {
				i = rs.getInt(1);
			}
			if(i>0) {
				if(i%a != 0) {
					i = i/a + 1;
				}else {
					i = i/a ;
				}
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return i;
	}
	public int CountCate() {
		int i = 0;
		try {
			String qerry = "SELECT COUNT(*) FROM users";
			cnn = connectdb.getConnection();
			stm = cnn.createStatement();
			rs = stm.executeQuery(qerry);
			while(rs.next()) {
				i = rs.getInt(1);
			}
		
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, stm, cnn);
		}
		return i;
	}
}
