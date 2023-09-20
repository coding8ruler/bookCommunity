package bookreview.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import bookreview.dao.BookReviewDAO;
import bookreview.model.BWriteRequest;
import bookreview.model.BookReview;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;



public class WriteBookReviewService {

	private BookReviewDAO bookreviewDAO = new BookReviewDAO();
	
	public Integer write(BWriteRequest bwriteReq) {
			Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);  
			BookReview bookereivew = toBookReivew(bwriteReq);
			BookReview savedBookReview = bookreviewDAO.insert(conn, bookereivew);
			if(savedBookReview == null) {
				throw new RuntimeException();
			}
			conn.commit();
			return savedBookReview.getbNo();
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
	private BookReview toBookReivew(BWriteRequest bwriteReq) {
		Date now = new Date();
		return new BookReview(null,715,bwriteReq.getBOOK_TITLE(),bwriteReq.getAUTHOR(),bwriteReq.getPUBLISHER(),bwriteReq.getB_WRITER_ID(),bwriteReq.getB_WRITER_NAME(),bwriteReq.getB_TITLE(), bwriteReq.getB_CONTENT(),now, 0);
	} 
}
