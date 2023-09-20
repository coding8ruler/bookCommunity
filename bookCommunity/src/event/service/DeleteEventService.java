package event.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import event.dao.EventDAO;
import event.exception.EventContentNotFoundException;

public class DeleteEventService {
	
	private EventDAO eventDAO = new EventDAO();

	public int deleteEvent(int no) {

		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			cnt = eventDAO.deleteEvent(conn, no);
			if(cnt==0) {
				throw new EventContentNotFoundException();
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
	}
	
