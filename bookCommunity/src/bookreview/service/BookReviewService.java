package bookreview.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookreview.dao.BookReviewDAO;
import bookreview.model.BookReview;
import bookreview.model.BookReviewPage;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;


public class BookReviewService {
	Connection conn = null;
	private BookReviewDAO bookreviewDAO = new BookReviewDAO();
	
	private int size = 3;

	public BookReviewPage getBookReviewPage(int pageNo, int size) {
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = bookreviewDAO.selectCount(conn);
			List<BookReview> bookReviewList = bookreviewDAO.select(conn,(pageNo-1)*size, size);
			return new BookReviewPage(total, pageNo, size, bookReviewList);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	
}

