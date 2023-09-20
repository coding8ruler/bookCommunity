package freeboard.service;

import java.sql.Connection;
import java.sql.SQLException;

import freeboard.DAO.FreeboardDAO;
import freeboard.exception.FreeboardContentNotFoundException;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;

public class DeleteService {
	
	private FreeboardDAO freeboardDAO = new FreeboardDAO();
	
	//게시글 삭제 
	public int deleteFreeboard(int no) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			cnt = freeboardDAO.deleteFreeboard(conn, no);
			if(cnt==0) {
				throw new FreeboardContentNotFoundException();
			}			
			if(cnt==1) {
				conn.commit();
			}
			return cnt;
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);

		}finally {
			JdbcUtil.close(conn);
		}
		return cnt;
		}
	
	//댓글 삭제
	public int deleteFreply(int rNo) {

		Connection conn = null;
		int rcnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			rcnt = freeboardDAO.deleteFreply(conn, rNo);
			if(rcnt==0) {
				throw new FreeboardContentNotFoundException();
			}			
			if(rcnt==1) {
				conn.commit();
			}	
			return rcnt;
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);

		}finally {
			JdbcUtil.close(conn);
		}
		return rcnt;
		}
	}
	
