package bookreview.service;

import java.sql.Connection;
import java.sql.SQLException;

import bookreview.dao.BookReviewDAO;
import bookreview.exception.BookReviewNotFoundException;
import bookreview.exception.PermissionDeniedException;
import bookreview.model.BUpdateRequest;
import bookreview.model.BookReview;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;


public class UpdateBookReviewService {

	private BookReviewDAO bookreviewDAO = new BookReviewDAO();
	
	public void update(BUpdateRequest bupReq) {
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			BookReview bookreview = bookreviewDAO.selectById(conn,bupReq.getNo());
			if(bookreview==null) {
				throw new BookReviewNotFoundException();
			}

			bookreviewDAO.update(conn,	
										bupReq.getbookTitle(),
										bupReq.getauthor(),
										bupReq.getpublisher(),
										bupReq.getbTitle(),
										bupReq.getbContent(),
										bupReq.getNo());
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
	private boolean canUpdate(String updatingUserId, BookReview bookreview) {
		String id = bookreview.getbWriterId();
		return id.equals(updatingUserId);
		
		
	} 
}
