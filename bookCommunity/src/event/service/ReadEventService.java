package event.service;

import java.sql.SQLException;
import java.sql.Connection;
import jdbc.conn.ConnectionProvider;
import event.dao.EventDAO;
import event.exception.EventNotFoundException;
import event.model.Event;
import event.model.EventData;

public class ReadEventService {
	
	Connection conn = null;
	
	private EventDAO eventDAO = new EventDAO();
	
	public EventData getEvent(int no, boolean increaseE_cnt) {
		
		try {
			conn = ConnectionProvider.getConnection();
			
			Event event = eventDAO.selectById(conn,no);
			if(event==null) {
				throw new EventNotFoundException();
			}
			//조회?��
			if(increaseE_cnt) {
				eventDAO.increaseE_cnt(conn,no);
			}
			return new EventData(event);
		} catch (SQLException e) {
			System.out.println("ReadEventServiceŬ������ getEvent()�޼��� �����? ���ܹ߻�");
			throw new RuntimeException();
		}

	}

}
