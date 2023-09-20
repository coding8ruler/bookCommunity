package member.model;

import java.util.Date;

public class User {
	
	private int m_no;
	private String mId;
	private String mName;
	private String mPwd;
	private String email;
	private String postcode;
	private String jibunAddress;
	private Date mJoin;
	private int grade;
	private String gender;
	
	public User(int m_no, String mId, String mName, String mPwd, String email, String postcode,String jibunAddress,
			Date mJoin, int grade, String gender) {
		super();
		this.m_no = m_no;
		this.mId = mId;
		this.mName = mName;
		this.mPwd = mPwd;
		this.email = email;
		this.postcode = postcode;
		this.jibunAddress = jibunAddress;
		this.mJoin = mJoin;
		this.grade = grade;
		this.gender = gender;
	}
	public User(int m_no, String mId, String mName, int grade, String gender) {
		this.m_no = m_no;
		this.mId = mId;
		this.mName = mName;
		this.grade = grade;
		this.gender = gender;
	}
	
	public User(int m_no, String mId) {
		this.m_no = m_no;
		this.mId = mId;
	}
	
	public int getM_no() {
		return m_no;
	}
	public String getmId() {
		return mId;
	}
	public String getmName() {
		return mName;
	}
	public String getmPwd() {
		return mPwd;
	}
	public String getEmail() {
		return email;
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
	public String getGender() {
		return gender;
	}
	
	
	@Override
	public String toString() {
		return "User [m_no=" + m_no + ", mId=" + mId + ", mName=" + mName + ", mPwd=" + mPwd + ", email=" + email
				+ ", postcode=" + postcode + ", jibunAddress=" + jibunAddress
				+ ", mJoin=" + mJoin + ", grade=" + grade + ", gender=" + gender + "]";
	}
	

}
