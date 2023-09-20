package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.exception.MemberNotFoundException;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import member.DAO.MemberDAO;
import member.exception.InvalidEmailException;
import member.exception.InvalidNameException;
import member.model.Member;

public class FindIdService {

	private MemberDAO memberDAO = new MemberDAO();
	
	public Member Findid(String mName, String email) throws SQLException {

		Connection conn=null;
		Member member = null;
		try {
			conn = ConnectionProvider.getConnection();
			member = memberDAO.findId(mName, email, conn);
			System.out.println("memberDAO.member"+member);

			if(member == null) {
				System.out.println("member=null");
				throw new MemberNotFoundException();
			}

			if(!member.matchMemberName(mName)) {
				throw new InvalidNameException();
			}
			if(!member.matchMemberEmail(email)) {
				throw new InvalidEmailException();
			}

			return memberDAO.findId(mName, email, conn);	
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
