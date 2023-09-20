package bookreview.service;

import java.sql.SQLException;

import bookreview.dao.BookReviewDAO;
import bookreview.model.BookReview;
import bookreview.model.BookReviewData;

import java.sql.Connection;
import jdbc.conn.ConnectionProvider;
import notice.exception.NoticeNotFoundException;

public class ReadBookReviewService {
	
	Connection conn = null;
	
	private BookReviewDAO bookreviewDAO = new BookReviewDAO();
	
	public BookReviewData getBookReview(int no, boolean increaseN_cnt) {
		
		try {
			conn = ConnectionProvider.getConnection();
			BookReview bookreview = bookreviewDAO.selectById(conn,no);					// 오류부분
			if(bookreview==null) {
				throw new NoticeNotFoundException();
			}
			//조회수
			if(increaseN_cnt) {
				bookreviewDAO.increaseB_cnt(conn,no);
			}
			return new BookReviewData(bookreview);
		} catch (SQLException e) {
			System.out.println("ReadBookReviewServiceŬ������ getBookReview()�޼��� ����� ���ܹ߻�");
			throw new RuntimeException();
		}

	}

}
