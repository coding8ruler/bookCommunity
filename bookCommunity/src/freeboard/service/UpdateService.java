package freeboard.service;

import java.sql.Connection;
import java.sql.SQLException;

import freeboard.DAO.FreeboardDAO;
import freeboard.exception.FreeboardNotFoundException;
import freeboard.exception.PermissionDeniedException;
import freeboard.model.Freeboard;
import freeboard.model.FreeUpdateRequest;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;

public class UpdateService {

	private FreeboardDAO freeboardDAO = new FreeboardDAO();
	
	public void update(FreeUpdateRequest upReq) {
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Freeboard freeboard = freeboardDAO.selectById(conn,upReq.getNo());
			if(freeboard==null) {
				throw new FreeboardNotFoundException();
			}
			if(!canUpdate(upReq.getWriterId(), freeboard)) {
				throw new PermissionDeniedException();
			}
			freeboardDAO.update(conn,upReq.getfTitle(),upReq.getfContent(),upReq.getNo());
				
			conn.commit();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	private boolean canUpdate(String updatingUserId, Freeboard freeboard) {
		String id = freeboard.getfWriter().getWriterId();
		return id.equals(updatingUserId);
	} 
	
	//댓글수정하기
		public int replyModify(String rContent,int rNo){
			Connection conn = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				result = freeboardDAO.replyModify(conn,rContent,rNo);
				conn.commit();
			}catch (SQLException e) {
				e.printStackTrace();
				JdbcUtil.rollback(conn);
			}finally {
				JdbcUtil.close(conn);
			}
			return result;
		}
		
}
