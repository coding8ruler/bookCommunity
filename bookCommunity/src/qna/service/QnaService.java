package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import qna.dao.QnaDAO;
import qna.model.Qna;
import qna.model.dto.QnaPage;


public class QnaService {
		
		private QnaDAO qnaDAO = new QnaDAO();
		
		private int size = 3;

		public QnaPage getQnaPage(int pageNo, int size) {
			
			try {
				Connection conn = ConnectionProvider.getConnection();
				
				int total = qnaDAO.selectCount(conn);
				List<Qna> qnaList = qnaDAO.select(conn,(pageNo-1)*size, size);
				System.out.println("qnaservice=qnaList"+qnaList);
				
				return new QnaPage(total, pageNo, size, qnaList);
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		

	}
