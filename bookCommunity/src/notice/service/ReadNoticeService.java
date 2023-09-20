package notice.service;

import java.sql.SQLException;
import java.sql.Connection;
import jdbc.conn.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.exception.NoticeNotFoundException;
import notice.model.Notice;
import notice.model.dto.NoticeData;

public class ReadNoticeService {
	
	Connection conn = null;
	
	private NoticeDAO noticeDAO = new NoticeDAO();
	
	public NoticeData getNotice(int no, boolean increaseN_cnt) {
		
		try {
			conn = ConnectionProvider.getConnection();
			
			Notice notice = noticeDAO.selectById(conn,no);
			if(notice==null) {
				throw new NoticeNotFoundException();
			}
			//조회수
			if(increaseN_cnt) {
				noticeDAO.increaseN_cnt(conn,no);
			}
			return new NoticeData(notice);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

}
