package bookreview.service;

import java.sql.Connection;
import java.sql.SQLException;

import bookreview.dao.BookReviewDAO;
import bookreview.exception.BookReviewContentNotFoundException;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;


public class DeleteBookReviewService {
	
	private BookReviewDAO bookreviewDAO = new BookReviewDAO();

	public int deleteBookReview(int no) {

		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			cnt = bookreviewDAO.deleteBookReview(conn, no);
			if(cnt==0) {
				throw new BookReviewContentNotFoundException();
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
	
