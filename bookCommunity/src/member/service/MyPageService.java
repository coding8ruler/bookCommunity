package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.exception.MemberNotFoundException;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import member.DAO.MemberDAO;
import member.model.Member;

public class MyPageService {
	
private MemberDAO memberDAO = new MemberDAO();
	
	public void myPageUpdate(String mId, String mPwd, String email, String postcode, String jibunAddress) throws SQLException {
		Connection conn = null;
		Member member = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			member = memberDAO.selectById(mId, conn);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			member.changeMyInfo(mPwd, email, postcode, jibunAddress);
			System.out.println("member!="+member);
			memberDAO.myInfoUpdate(conn, member);
			System.out.println("여기까지 되나?");
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}	
}
