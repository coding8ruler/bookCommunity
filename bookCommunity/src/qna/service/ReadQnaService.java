package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import qna.model.Qna;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import qna.dao.QnaDAO;
import qna.exception.QnaNotFoundException;
import qna.model.Qna;
import qna.model.dto.QnaData;

public class ReadQnaService {
	
	
	Connection conn = null;
	
	private QnaDAO qnaDAO = new QnaDAO();
	//원글상세조회
	public QnaData getQna(int no, boolean increaseQ_cnt) {
		
		try {
			conn = ConnectionProvider.getConnection();
			
			Qna qna = qnaDAO.selectById(conn,no);
			if(qna==null) {
				throw new QnaNotFoundException();
			}
			
			//조회수 증가관련
			if(increaseQ_cnt) {
				qnaDAO.increaseQ_cnt(conn,no);
			}
			return new QnaData(qna);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	//댓글목록조회
		public List<Qna> selectQnaCmtList(int q_no) {
			
			Connection conn = null;
			List<Qna> qnaCmtList = new ArrayList<Qna>();
			try {
				conn = ConnectionProvider.getConnection();
				qnaCmtList = qnaDAO.selectQnaCmtList(conn, q_no);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return  qnaCmtList;
		
		}
		
	
	
		
			
		
}
