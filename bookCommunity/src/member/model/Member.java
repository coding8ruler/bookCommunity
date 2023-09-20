package member.model;

import java.util.Date;

//p.591
//(DB member �뀒�씠釉�)�쉶�썝愿��젴 �뜲�씠�꽣���옣, �젣怨� �벑�쓽 湲곕뒫�쓣 媛�吏� �겢�옒�뒪
public class Member {
	//�븘�뱶
	private int m_no;
	private String mId;
	private String mPwd;
	private String mName;
	private String email;
	private String gender;
	private String postcode;
	private String jibunAddress;
	private Date mJoin;
	private int grade; 
	
	public Member(int m_no, String mId, String mPwd, String mName, 	String email, 
			String gender, String postcode,  String jibunAddress,  Date mJoin, int grade) {
		this.m_no = m_no;
		this.mId = mId;
		this.mPwd = mPwd;
		this.mName = mName;
		this.email = email;
		this.gender = gender;
		this.postcode = postcode;
		this.jibunAddress = jibunAddress;
		this.mJoin = mJoin;
		this.grade = grade;
	}

	public Member(String mId, String mPwd, String mName, String email, String gender,
			String postcode, String jibunAddress, Date mJoin,int grade) {
		this.mId = mId;
		this.mPwd = mPwd;
		this.mName = mName;
		this.email = email;
		this.gender = gender;
		this.postcode = postcode;
		this.jibunAddress = jibunAddress;
		this.mJoin = mJoin;
		this.grade = grade;
	}
	
	public Member(int m_no, String mId, String mName, String email, String postcode, String jibunAddress, String gender, int grade, Date mJoin) {
		this.m_no = m_no;
		this.mId = mId;
		this.mName = mName;
		this.email = email;
		this.gender = gender;
		this.postcode = postcode;
		this.jibunAddress = jibunAddress;
		this.mJoin = mJoin;
		this.grade = grade;
	}
	
	public Member(String mId, String mPwd, String email, String postcode, String jibunAddress) {
		this.mId = mId;
		this.mPwd = mPwd;
		this.email = email;
		this.postcode = postcode;
		this.jibunAddress = jibunAddress;
	}
	
	public Member(int m_no, String mId, String mName) {
		this.m_no = m_no;
		this.mId = mId;
		this.mName = mName;
	}
	
	public Member(String mId, String mName) {
		this.mId = mId;
		this.mName = mName;
	}

	public int getM_no() {
		return m_no;
	}
	public String getmId() {
		return mId;
	}
	public String getmPwd() {
		return mPwd;
	}
	public String getmName() {
		return mName;
	}
	public String getEmail() {
		return email;
	}
	public String getGender() {
		return gender;
	}
	public String getPostcode() {
		return postcode;
	}

	public String getJibunAddress() {
		return jibunAddress;
	}
	public Date getmJoin() {
		return mJoin;
	}
	public int getGrade() {
		return grade;
	}

	//鍮꾨�踰덊샇 留ㅼ튂(p.592 35 line)
	//留ㅺ컻蹂��닔 pwd�뿉 ���옣�맂 媛믨낵 �븘�뱶 memberpwd�쓽 媛믪씠 �씪移섑븯硫� true由ы꽩
	public boolean matchPassword(String pwd) {
		return mPwd.equals(pwd);
		}

	public void changePassword(String newPwd) {
		this.mPwd = newPwd;
	}
	public boolean matchMemberId(String mId) {
		// TODO Auto-generated method stub
		return mId.equals(mId);
	}

	public boolean matchMemberName(String mName) {
		return mName.equals(mName);
	}
	public boolean matchMemberEmail(String email) {
		return email.equals(email);
	}
	
	
	public void changeGrade(int grade) {
		this.grade = grade;
	}
	
	
	public void changeMyInfo(String mPwd, String email, String postcode, String jibunAddress) {
		this.mPwd = mPwd;
		this.email = email;
		this.postcode = postcode;
		this.jibunAddress = jibunAddress;
	}

	@Override
	public String toString() {
		return "Member [m_no=" + m_no + ", mId=" + mId + ", mPwd=" + mPwd + ", mName=" + mName + ", email=" + email
				+ ", gender=" + gender + ", postcode=" + postcode + ", jibunAddress=" + jibunAddress + ", mJoin="
				+ mJoin + ", grade=" + grade + "]";
	}
	

	
	

	
}
