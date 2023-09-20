package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import admin.exception.MemberNotFoundException;
import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import member.DAO.MemberDAO;
import member.model.Member;

public class MemberService {

private MemberDAO memberDAO = new MemberDAO();
	
	//댓글형 게시판 목록 조회
	public List<Member> getReboardList(){
		List<Member> reboardList = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			reboardList = memberDAO.getMemberList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberNotFoundException();
		}finally {
			JdbcUtil.close(conn);
		}
		return reboardList;
	}
}
