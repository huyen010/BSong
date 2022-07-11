package Model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.jdt.internal.compiler.IDebugRequestor;

import Model.bean.Song;
import Model.bean.category;
import utils.DBConnectionUtil;

public class SongDAO extends AbstractDAO {
	public SongDAO() {
		connectdb = new DBConnectionUtil();
	}
	public ArrayList<Song> getListSongPage(int trang, int id_cate){
		int x1 = (trang-1) * 3;
		int x2 = 3;
		ArrayList<Song> list = new ArrayList<Song>();
		try {
			cnn = connectdb.getConnection();
			String qr = "";
			if(id_cate==0) {
				qr = "select * from songs ORDER BY Id DESC limit ?,?";
				pst = cnn.prepareStatement(qr);
				pst.setInt(1, x1);
				pst.setInt(2, x2);
			}else {
				qr = "select * from songs where Cat_id = ? ORDER BY Id DESC limit ?,?";
				pst = cnn.prepareStatement(qr);
				pst.setInt(1, id_cate);
				pst.setInt(2, x1);
				pst.setInt(3, x2);
			}
			rs = pst.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("Id"), rs.getString("Name"), rs.getString("Preview_text"), rs.getString("Detail_text"),rs.getDate("Date_create"), rs.getInt("Cat_id"), rs.getString("Picture"), rs.getInt("Counter"));
				list.add(song);
			}
		}catch(SQLException e) {
			
		}
		finally {
			 connectdb.close(rs, pst, cnn);
		}
		return list;
	}

	public ArrayList<Song> getListSongNew(){
		ArrayList<Song> list = new ArrayList<Song>();
		try {
			cnn = connectdb.getConnection();
			String qr = "SELECT * FROM songs ORDER BY Id DESC LIMIT 5";
			stm = cnn.createStatement();
			rs = stm.executeQuery(qr);
			while(rs.next()) {
				Song song = new Song(rs.getInt("Id"), rs.getString("Name"), rs.getString("Preview_text"), rs.getString("Detail_text"),rs.getDate("Date_create"), rs.getInt("Cat_id"), rs.getString("Picture"), rs.getInt("Counter"));
				list.add(song);
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, stm, cnn);
		}
		return list;
	}
	public ArrayList<Song> getListSongById_Cat(int id_cat){
		ArrayList<Song> list = new ArrayList<Song>();
		try {
			cnn = connectdb.getConnection();
			String qr = "select * from songs where Cat_id = ? ORDER BY Id DESC";
			pst = cnn.prepareStatement(qr);
			pst.setInt(1, id_cat);
			rs = pst.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("Id"), rs.getString("Name"), rs.getString("Preview_text"), rs.getString("Detail_text"),rs.getDate("Date_create"), rs.getInt("Cat_id"), rs.getString("Picture"), rs.getInt("Counter"));
				list.add(song);
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return list;
	}
	public Song getSongById(int id_song) {
		Song song = null;
		try {
			cnn = connectdb.getConnection();
			String qr = "select * from songs where Id = ?";
			pst = cnn.prepareStatement(qr);
			pst.setInt(1, id_song);
			rs = pst.executeQuery();
			while(rs.next()) {
				song = new Song(rs.getInt("Id"), rs.getString("Name"), rs.getString("Preview_text"), rs.getString("Detail_text"),rs.getDate("Date_create"), rs.getInt("Cat_id"), rs.getString("Picture"), rs.getInt("Counter"));
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return song;
	}
	public ArrayList<Song> Search(int id_cat,String name,int page){
		int x1 = (page-1) * 3;
		int x2 = 3;
		name = "%" + name + "%";
		ArrayList<Song> list = new ArrayList<Song>();
		String qr = "";
		try {
			cnn = connectdb.getConnection();
			if(id_cat == 0) {
				qr = "select * from songs where Name like ? ORDER BY Id DESC limit ?,?";
				pst = cnn.prepareStatement(qr);
				pst.setString(1, name);
				pst.setInt(2, x1);
				pst.setInt(3, x2);
			}else {
				qr = "select * from songs where Cat_id = ? and Name like ? ORDER BY Id DESC limit ?,?";
				pst = cnn.prepareStatement(qr);
				pst.setInt(1, id_cat);
				pst.setString(2, name);
				pst.setInt(3, x1);
				pst.setInt(4, x2);
			}
			rs = pst.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("Id"), rs.getString("Name"), rs.getString("Preview_text"), rs.getString("Detail_text"),rs.getDate("Date_create"), rs.getInt("Cat_id"), rs.getString("Picture"), rs.getInt("Counter"));
				list.add(song);
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return list;
	}

	public ArrayList<Song> getSongRandom(Song song, int a) {
		ArrayList<Song> listsong = new ArrayList<Song>();
		try {
			cnn = connectdb.getConnection();
			String qr = "SELECT * FROM songs WHERE Cat_id = ? AND Id != ? ORDER BY Id DESC LIMIT ?";
			pst = cnn.prepareStatement(qr);
			pst.setInt(1, song.getId_cat());
			pst.setInt(2, song.getId());
			pst.setInt(3, a);
			rs = pst.executeQuery();
			while(rs.next()) {
				Song songrd = new Song(rs.getInt("Id"), rs.getString("Name"), rs.getString("Preview_text"), rs.getString("Detail_text"),rs.getDate("Date_create"), rs.getInt("Cat_id"), rs.getString("Picture"), rs.getInt("Counter"));
				listsong.add(songrd);
			}
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return listsong;
	}
	public int insertSong(Song song) {
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "INSERT INTO songs(Name,Preview_text,Detail_text,Cat_id,Picture) VALUES(?,?,?,?,?)";
			pst = cnn.prepareStatement(qr);
			pst.setString(1,song.getName());
			pst.setString(2,song.getPreview());
			pst.setString(3,song.getDetail());
			pst.setInt(4,song.getId_cat());
			pst.setString(5,song.getPicutre());
			result = pst.executeUpdate();
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(pst, cnn);
		}
		return result;
	}
	public int DeleteSong(int id) {
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "DELETE FROM songs WHERE Id = ?";
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
	public int UpdateSong(Song song) {
		int result = 0;
		try {
			cnn = connectdb.getConnection();
			String qr = "UPDATE songs SET Name = ?, Preview_text = ?, Detail_text = ?, Cat_id = ?, Picture = ? where Id = ?";
			pst = cnn.prepareStatement(qr);
			pst.setString(1,song.getName());
			pst.setString(2,song.getPreview());
			pst.setString(3,song.getDetail());
			pst.setInt(4,song.getId_cat());
			pst.setString(5,song.getPicutre());
			pst.setInt(6,song.getId());
			result = pst.executeUpdate();
	
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(pst, cnn);
		}
		return result;
	}
	public int SoTrang(String name, int iddm, int a) {
		int i = 0;
		name = "%" + name + "%";
		try {
			String qr = "SELECT COUNT(*) FROM songs where Name like ?" ;
			if(iddm != 0) {
				qr = qr + " and Cat_id = ?";
			}
			cnn = connectdb.getConnection();
			pst = cnn.prepareStatement(qr);
			pst.setString(1, name);
			if(iddm != 0) {
				pst.setInt(2, iddm);
			}
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
			String qerry = "SELECT COUNT(*) FROM songs";
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
	public int UpdateCounter(Song song) {
		int result = 0;
		song.setCounter(song.getCounter()+1);
		try {
			String qr = "UPDATE songs SET Counter = ? WHERE Id = ? " ;
			cnn = connectdb.getConnection();
			pst = cnn.prepareStatement(qr);
			pst.setInt(1, song.getCounter());;
			pst.setInt(2, song.getId());
			result = pst.executeUpdate();
		}catch(SQLException e) {
			
		}
		finally {
			connectdb.close(rs, pst, cnn);
		}
		return result;
	}
}
