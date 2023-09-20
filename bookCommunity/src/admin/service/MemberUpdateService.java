package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.exception.MemberNotFoundException;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import member.DAO.MemberDAO;
import member.model.Member;

public class MemberUpdateService {

	private MemberDAO memberDAO = new MemberDAO();
	
	public void memberUpdate(String mId, int grade) throws SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDAO.selectById(mId, conn);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			member.changeGrade(grade);
			memberDAO.MemberUpdate(conn, member);
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}

}
