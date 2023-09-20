package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import notice.dao.NoticeDAO;
import notice.exception.NoticeNotFoundException;
import notice.exception.PermissionDeniedException;
import notice.model.Notice;
import notice.model.dto.UpdateRequest;

public class UpdateNoticeService {

	private NoticeDAO noticeDAO = new NoticeDAO();
	
	public void update(UpdateRequest upReq) {
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Notice notice = noticeDAO.selectById(conn,upReq.getNo());
			if(notice==null) {
				throw new NoticeNotFoundException();
			}
			if(!canUpdate(upReq.getN_writerid(), notice)) {
				throw new PermissionDeniedException();
			}
			noticeDAO.update(conn,upReq.getN_title(),upReq.getN_content(),upReq.getNo());
				
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
	private boolean canUpdate(String updatingUserId, Notice notice) {
		String id = notice.getN_writer().getN_writerid();
		return id.equals(updatingUserId);
	} 
}
