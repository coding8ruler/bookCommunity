package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.conn.ConnectionProvider;
import member.DAO.MemberDAO;

public class MemberDeleteService {
	
	//필드
		private MemberDAO memberDAO = new MemberDAO();
		
		//생성자
		
		//메서드
		
		//삭제(delete)
		public int memberDelete(int m_no) {
			int cnt = 0;
			try {
				Connection conn = ConnectionProvider.getConnection();
				cnt = memberDAO.memberDelete(conn, m_no);
				
				return cnt;
			}catch(SQLException e) {
				
			}
			return m_no;
		}
	}
	
