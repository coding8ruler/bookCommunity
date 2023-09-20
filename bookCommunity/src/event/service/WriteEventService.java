package event.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import notice.model.Notice;
import event.dao.EventDAO;
import event.model.Event;
import event.model.WriteRequest;


public class WriteEventService {

	private EventDAO eventDAO = new EventDAO();
	
	public Integer write(WriteRequest writeReq) {
			Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);  
			Event event = toEvent(writeReq);
			Event savedEvent = eventDAO.insert(conn, event);
			if(savedEvent == null) {
				throw new RuntimeException();
			}
			conn.commit();
			return savedEvent.getE_no();
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
	private Event toEvent(WriteRequest writeReq) {
		Date now = new Date();
		return new Event(null,9999,writeReq.getE_WRITER(),writeReq.getE_TITLE(), writeReq.getE_CONTENT(),now, 0);
	}
}
