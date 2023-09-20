package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import qna.dao.QnaDAO;
import qna.exception.PermissionDeniedException;
import qna.exception.QnaNotFoundException;
import qna.model.Qna;
import qna.model.dto.UpdateRequest;

public class UpdateQnaService {

	Connection conn = null;
	private QnaDAO qnaDAO = new QnaDAO();
	//글 수정
	public void update(UpdateRequest upReq) {
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Qna qna = qnaDAO.selectById(conn,upReq.getNo());
			if(qna==null) {
				throw new QnaNotFoundException();
			}
			if(!canUpdate(upReq.getQ_writerid(), qna)) {
				throw new PermissionDeniedException();
			}
			qnaDAO.update(conn,upReq.getQ_title(),upReq.getQ_content(),upReq.getNo());
				
			conn.commit();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}catch (PermissionDeniedException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	private boolean canUpdate(String updatingUserId, Qna qna) {
		String id = qna.getQ_writer().getQ_writerid();
		return id.equals(updatingUserId);
	} 
	
	
	//댓글수정
	public int updateQnaCmt(String q_cmt_content,int q_cmt_no){
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			result = qnaDAO.updateQnaCmt(conn, q_cmt_content, q_cmt_no);
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
		return result;
		
	}
	
	
}
