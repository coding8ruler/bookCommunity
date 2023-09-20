package event.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import event.dao.EventDAO;
import event.exception.EventNotFoundException;
import event.exception.PermissionDeniedException;
import event.model.Event;
import event.model.UpdateRequest;

public class UpdateEventService {

	private EventDAO eventDAO = new EventDAO();
	
	public void update(UpdateRequest upReq) {
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Event event = eventDAO.selectById(conn,upReq.getNo());
			if(event==null) {
				throw new EventNotFoundException();
			}
			if(!canUpdate(upReq.getE_writerid(), event)) {
				throw new PermissionDeniedException();
			}
			eventDAO.update(conn,upReq.getE_title(),upReq.getE_content(),upReq.getNo());
				
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
	private boolean canUpdate(String updatingUserId, Event event) {
		String id = event.getE_writer().getE_writerid();
		return id.equals(updatingUserId);
	} 
}
