package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import notice.dao.NoticeDAO;
import notice.exception.NoticeContentNotFoundException;

public class DeleteNoticeService {
	
	private NoticeDAO noticeDAO = new NoticeDAO();

	public int deleteNotice(int no) {

		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			cnt = noticeDAO.deleteNotice(conn, no);
			if(cnt==0) {
				throw new NoticeContentNotFoundException();
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
	
