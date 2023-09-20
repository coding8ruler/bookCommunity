package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.exception.MemberNotFoundException;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import member.DAO.MemberDAO;
import member.exception.InvalidEmailException;
import member.exception.InvalidIdException;
import member.exception.InvalidNameException;
import member.model.Member;

public class FindPwdService {

private MemberDAO memberDAO = new MemberDAO();
	
	public Member FindPwd(String mId, String mName, String email) throws SQLException {

		Connection conn=null;
		Member member = null;
		try {
			conn = ConnectionProvider.getConnection();
			member = memberDAO.findPwd(mId, mName, email, conn);
			System.out.println("memberDAO.member"+member);

			if(member == null) {
				throw new MemberNotFoundException();
			}

			if(!member.matchMemberId(mId)) {
				throw new InvalidIdException();
			}
			if(!member.matchMemberName(mName)) {
				throw new InvalidNameException();
			}
			if(!member.matchMemberEmail(email)) {
				throw new InvalidEmailException();
			}

			return memberDAO.findPwd(mId, mName, email, conn);	
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
