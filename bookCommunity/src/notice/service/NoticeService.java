package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.conn.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.model.Notice;
import notice.model.dto.NoticePage;

public class NoticeService {
	
	private NoticeDAO noticeDAO = new NoticeDAO();
	
	private int size = 10;

	public NoticePage getNoticePage(int pageNo, int size) {
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			int total = noticeDAO.selectCount(conn);
			List<Notice> noticeList = noticeDAO.select(conn,(pageNo-1)*size, size);
			
			return new NoticePage(total, pageNo, size, noticeList);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
