package member.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;
import member.model.Member;
import notice.model.Notice;
import notice.model.dto.Writer;

public class MemberDAO {
	
	public Member selectById(String mId, Connection conn) {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "SELECT m_no, mId, mPwd, mName, email, gender, postcode, jibunAddress, grade, mJoin "+
				 "from member where mId= ?";
	
	Member member = null;
	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mId);
		rs = pstmt.executeQuery();
		System.out.println("rs="+rs);
		
		
		
		if(rs.next()) { 
			int m_no = rs.getInt("m_no");
			String mId1 = rs.getString("mId");
			String mPwd = rs.getString("mPwd");
			String mName = rs.getString("mName");
			String email = rs.getString("email");
			String gender = rs.getString("gender");
			String postcode = rs.getString("postcode");
			String jibunAddress = rs.getString("jibunAddress");
			Date mJoin = toDate(rs.getTimestamp("mJoin"));
			int grade = rs.getInt("grade");
			member = new Member(m_no, mId1, mPwd, mName, email, gender, postcode, jibunAddress, mJoin, grade);
			System.out.println("member= " + member);
			
			return member;
		}
	}catch(SQLException e){
		e.printStackTrace();
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
	return member;
		
	}

	private Date toDate(Timestamp ts) {
		Date date = null;
		if( ts != null) {
			date = new Date(ts.getTime());
		}
		return date;
	}

	public void insert(Member member, Connection conn) throws SQLException {
		String sql = "insert into member(mId, mPwd, mName, email, gender, postcode, jibunAddress, mJoin, grade) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getmId());
		stmt.setString(2, member.getmPwd());
		stmt.setString(3, member.getmName());
		stmt.setString(4, member.getEmail());
		stmt.setString(5, member.getGender());
		stmt.setString(6, member.getPostcode());
		stmt.setString(7, member.getJibunAddress());

		stmt.setTimestamp(8,new Timestamp(member.getmJoin().getTime()));//p593 48
		stmt.setInt(9,member.getGrade());
		int result = stmt.executeUpdate();
	}

	public void update(Connection conn, Member member) throws SQLException {
		String sql = "update member set mName=?, mPwd=? where mId=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getmName());
			pstmt.setString(2, member.getmPwd());
			pstmt.setString(3, member.getmId());
			pstmt.executeUpdate();
	}
	
	public Member findId(String mName, String email, Connection conn) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT m_no, mId, mPwd, mName, email, gender, postcode, jibunAddress, grade, mJoin "+
				 "from member where mName= ? and email=?";
		
		Member member = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mName);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			System.out.println("rs="+rs);
			
			if(rs.next()) { 
				int m_no = rs.getInt("m_no");
				String mId = rs.getString("mId");
				String mPwd = rs.getString("mPwd");
				String memberName = rs.getString("mName");
				String memberEmail = rs.getString("email");
				String gender = rs.getString("gender");
				String postcode = rs.getString("postcode");
				String jibunAddress = rs.getString("jibunAddress");
				Date mJoin = toDate(rs.getTimestamp("mJoin"));
				int grade = rs.getInt("grade");
				
				member = new Member(m_no, mId, mPwd, memberName, memberEmail, gender, postcode, jibunAddress, mJoin, grade);
				System.out.println("member= " + member);
				
				return member;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return member;
			
		}
	
	public Member findPwd(String mId, String mName, String email, Connection conn) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT m_no, mId, mPwd, mName, email, gender, postcode, jibunAddress, grade, mJoin "+
				 "from member where mId=? and mName= ? and email=?";
		
		Member member = null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mName);
			pstmt.setString(3, email);
			rs=pstmt.executeQuery();
			if(rs.next()) { 
				int m_no = rs.getInt("m_no");
				String memberId = rs.getString("mId");
				String mPwd = rs.getString("mPwd");
				String memberName = rs.getString("mName");
				String memberEmail = rs.getString("email");
				String gender = rs.getString("gender");
				String postcode = rs.getString("postcode");
				String jibunAddress = rs.getString("jibunAddress");
				Date mJoin = toDate(rs.getTimestamp("mJoin"));
				int grade = rs.getInt("grade");
				
				
				member = new Member(m_no, memberId, mPwd, memberName, memberEmail, gender, postcode, jibunAddress, mJoin, grade);
				System.out.println("member= " + member);
				
				return member;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return member;
		
	}
	
	public List<Member> getMemberList(Connection conn) throws SQLException {
		PreparedStatement stmt = null;	
		List<Member> memberList = new ArrayList<Member>();
		Member member = null;
		ResultSet rs = null;
		String sql = "select m_no, mId, mName, email, postcode, jibunAddress, gender, grade, mjoin from member";
		stmt = conn.prepareStatement(sql);
		rs=stmt.executeQuery();
		//List에 dto를 추가할 예정
		while(rs.next()) {
			member = new Member(rs.getInt("m_no"),
					rs.getString("mId"),
					rs.getString("mName"),
					rs.getString("email"),
					rs.getString("postcode"),
					rs.getString("jibunAddress"),
					rs.getString("gender"),
					rs.getInt("grade"),
					toDate1(rs.getTimestamp("mJoin")));
			memberList.add(member);
		}
		return memberList;
	}
	
	public void MemberUpdate(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update member set grade=? where mId=?";
		try {

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, member.getGrade());
		pstmt.setString(2, member.getmId());
		pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}
	
	//파라미터 int no는 삭제할 글 번호
		public int memberDelete(Connection conn, int m_no) {
			PreparedStatement pstmt = null;
			int cnt = 0;
			try {
				pstmt = conn.prepareStatement("delete from member where m_no = ?");
				pstmt.setInt(1, m_no);
				cnt = pstmt.executeUpdate();
				
				return cnt;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
			}
			return cnt;
		}
		
		public void myInfoUpdate(Connection conn, Member member) throws SQLException {
			PreparedStatement pstmt = null;
			String sql = "update member set mPwd=?, email=?, postcode=?, jibunAddress=? where mId=?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getmPwd());
				pstmt.setString(2, member.getEmail());
				pstmt.setString(3, member.getPostcode());
				pstmt.setString(4, member.getJibunAddress());
				pstmt.setString(5, member.getmId());
				pstmt.executeUpdate();
		}

	private Date toDate1(Timestamp timestamp) {

		return new Date(timestamp.getTime());
	}

}

