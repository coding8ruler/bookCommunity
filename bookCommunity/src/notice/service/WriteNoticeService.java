package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import notice.dao.NoticeDAO;
import notice.model.Notice;
import notice.model.dto.WriteRequest;


public class WriteNoticeService {

	private NoticeDAO noticeDAO = new NoticeDAO();
	
	public Integer write(WriteRequest writeReq) {
			Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);  
			Notice notice = toNotice(writeReq);
			Notice savedNotice = noticeDAO.insert(conn, notice);
			if(savedNotice == null) {
				throw new RuntimeException();
			}
			conn.commit();
			return savedNotice.getN_no();
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
	private Notice toNotice(WriteRequest writeReq) {
		Date now = new Date();
		return new Notice(null,9999,writeReq.getN_WRITER(),writeReq.getN_TITLE(), writeReq.getN_CONTENT(),now, 0);
	}
}
