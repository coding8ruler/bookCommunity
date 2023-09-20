package freeboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import freeboard.model.Freeboard;
import freeboard.DAO.FreeboardDAO;
import freeboard.model.FreeWriteRequest;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;


public class WriteService {

	private FreeboardDAO freeboardDAO = new FreeboardDAO();
	
	public Integer write(FreeWriteRequest writeReq) {
			Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);  
			Freeboard freeboard = toFreeboard(writeReq);
			Freeboard savedfreeboard = freeboardDAO.insert(conn, freeboard);
			if(savedfreeboard == null) {
				throw new RuntimeException();
			}
			conn.commit();
			return savedfreeboard.getfNo();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	
	
	private Freeboard toFreeboard(FreeWriteRequest writeReq) {
		Date now = new Date();
		return new Freeboard(null,1,writeReq.getWriter(),writeReq.getfTitle(), writeReq.getfContent(),now,now,0);
	}
}
