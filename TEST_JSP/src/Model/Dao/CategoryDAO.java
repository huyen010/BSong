package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.bean.Song;
import Model.bean.category;
import utils.DBConnectionUtil;

public class CategoryDAO extends AbstractDAO {

	public CategoryDAO() {
		connectdb = new DBConnectionUtil();
	}
	public ArrayList<category> getListCateGory(){
		ArrayList<category> list = new ArrayList<category>();
		try {
			cnn = connectdb.getConnection();
			String qr = "select * from categories ORDER BY Id DESC";
			stm = cnn.createStatement();
			rs = stm.executeQuery(qr);
			while(rs.next()) {
				category ctgr = new category(rs.getInt("Id"),rs.getString("Name"));
				list.add(ctgr);
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return list;
	}
	public category getCateGoryByID(int id) {
		category cate = null;
		try {
			cnn = connectdb.getConnection();
			String qr = "select * from categories where Id = ?";
			pst = cnn.prepareStatement(qr);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				cate = new category(rs.getInt("Id"),rs.getString("Name"));
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);;
		}
		return cate;
	}
	public ArrayList<category> getListCatePage(int trang,String name){
		int x1 = (trang-1) * 5;
		int x2 = 5;
		name = "%" + name + "%";
		ArrayList<category> list = new ArrayList<category>();
		try {
			cnn = connectdb.getConnection();
			String qr = "";
			qr = "select * from categories where name like ? ORDER BY Id DESC limit ?,? ";
			pst = cnn.prepareStatement(qr);
			pst.setString(1, name);
			pst.setInt(2, x1);
			pst.setInt(3, x2);
			rs = pst.executeQuery();
			while(rs.next()) {
				category cate = new category(rs.getInt("Id"), rs.getString("Name"));
				list.add(cate);
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return list;
	}
	
	public int insertCate(String cate) {
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "INSERT INTO categories(Name) VALUES(?)";
			pst = cnn.prepareStatement(qr);
			pst.setString(1,cate);
			result = pst.executeUpdate();
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(pst, cnn);
		}
		return result;
	}
	public int DeleteCate(int id) {
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "DELETE FROM categories WHERE Id = ?";
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
	public int UpdateCate(category cate) {
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "UPDATE categories SET Name = ? where Id = ?";
			pst = cnn.prepareStatement(qr);
			pst.setString(1,cate.getName());
			pst.setInt(2,cate.getId());
			result = pst.executeUpdate();
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(pst, cnn);
		}
		return result;
	}
	public int SoTrang(String name, int a) {
		int i = 0;
		name = "%" + name + "%";
		try {
			String qerry = "SELECT COUNT(*) FROM categories where name like ?";
			cnn = connectdb.getConnection();
			pst = cnn.prepareStatement(qerry);
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
			String qerry = "SELECT COUNT(*) FROM categories ";
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
