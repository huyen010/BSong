package Model.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.bean.Contact;
import utils.DBConnectionUtil;

public class ContactDAO extends AbstractDAO {

	public ContactDAO() {
		connectdb = new DBConnectionUtil();
	}

	public int insertContact(Contact contact) {
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "INSERT INTO contact(Name,Email,website,message) VALUES (?,?,?,?)";
			pst = cnn.prepareStatement(qr);
			pst.setString(1,contact.getName()); 
			pst.setString(2,contact.getEmail()); 
			pst.setString(3,contact.getWebsite()); 
			pst.setString(4,contact.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			connectdb.close(pst, cnn); 
		}
		return result;
	}

	public int DeleteContact(int id) {
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "DELETE FROM contact WHERE Id = ?";
			pst = cnn.prepareStatement(qr);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {

		} finally {
			connectdb.close(pst, cnn);
		}
		return result;
	}

	public ArrayList<Contact> getListContactPage(int trang, String name) {
		int x1 = (trang - 1) * 5;
		int x2 = 5;
		name = "%" + name + "%";
		ArrayList<Contact> list = new ArrayList<Contact>();
		try {
			cnn = connectdb.getConnection();
			String qr = "select * from contact where Name like ? ORDER BY Id DESC limit ?,?";
			pst = cnn.prepareStatement(qr);
			pst.setString(1, name);
			pst.setInt(2, x1);
			pst.setInt(3, x2);
			rs = pst.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"),
						rs.getString("website"), rs.getString("message"));
				list.add(contact);
			}
		} catch (SQLException e) {

		} finally {
			connectdb.close(rs, pst, cnn);
		}
		return list;
	}

	public Contact getContactbyID(int id) {
		Contact contact = null;
		try {
			cnn = connectdb.getConnection();
			String qr = "SELECT * FROM contact WHERE Id =?";
			pst = cnn.prepareStatement(qr);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				contact = new Contact(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"),
						rs.getString("website"), rs.getString("message"));
			}
		} catch (SQLException e) {

		} finally {
			connectdb.close(rs, pst, cnn);
		}
		return contact;
	}
	public int SoTrang(String name, int a) {
		name = "%" + name + "%";
		int i = 0;
		try {
			String qr = "SELECT COUNT(*) FROM contact where Name like ?";
			cnn = connectdb.getConnection();
			pst = cnn.prepareStatement(qr);
			pst.setString(1, name);
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
			String qerry = "SELECT COUNT(*) FROM contact";
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
