package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import qna.dao.QnaDAO;
import qna.model.Qna;
import qna.model.dto.WriteRequest;


public class WriteQnaService {

	Connection conn = null;
	private QnaDAO qnaDAO = new QnaDAO();
	//글 작성
	public Integer write(WriteRequest writeReq) {
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);  
			Qna qna = toQna(writeReq);
			
			Qna savedQna = qnaDAO.insert(conn, qna);
			if(savedQna == null) {
				throw new RuntimeException();
			}
			conn.commit();
			return savedQna.getQ_no();
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
	private Qna toQna(WriteRequest writeReq) {
		Date now = new Date();
		return new Qna(null,writeReq.getQ_TITLE(),writeReq.getQ_CONTENT(),writeReq.getQ_WRITER(),0,now);
	}

	//댓글쓰기
	public int writeQnaCmt(Qna qna) {
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			result = qnaDAO.writeQnaCmt(conn,qna);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return result;
	}

}
