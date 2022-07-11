package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import utils.DBConnectionUtil;

public abstract class AbstractDAO {
	protected Connection cnn;
	protected DBConnectionUtil connectdb;
	protected Statement stm;
	protected ResultSet rs;
	protected PreparedStatement pst;
	
}
