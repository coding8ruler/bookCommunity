package event.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.conn.ConnectionProvider;
import event.dao.EventDAO;
import event.model.Event;
import event.model.EventPage;

public class EventService {
	
	private EventDAO eventDAO = new EventDAO();
	
	private int size = 3;

	public EventPage getEventPage(int pageNo, int size) {
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			int total = eventDAO.selectCount(conn);
			List<Event> eventList = eventDAO.select(conn,(pageNo-1)*size, size);
			
			return new EventPage(total, pageNo, size, eventList);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
