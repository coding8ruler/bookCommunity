package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import qna.dao.QnaDAO;
import qna.exception.QnaContentNotFoundException;

public class DeleteQnaService {
	
	Connection conn = null;
	private QnaDAO qnaDAO = new QnaDAO();

	//글 삭제
	public int deleteQna(int no) {

		int cnt = 0;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			cnt = qnaDAO.deleteQna(conn, no);
			if(cnt==0) {
				throw new QnaContentNotFoundException();
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
	
	//댓글삭제
	public int deleteQnaCmt(int q_cmt_no) {
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			result = qnaDAO.deleteQnaCmt(conn, q_cmt_no);
			if(result==0) {
				throw new QnaContentNotFoundException();
			}			
			if(result==1) {
				conn.commit();
			}	
			return result;
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);

		}finally {
			JdbcUtil.close(conn);
		}
		return result;
		}
	}
	
	
