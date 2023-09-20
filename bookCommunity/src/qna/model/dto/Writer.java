package qna.model.dto;

public class Writer {
	
	private String q_writerid; //qna작성자id
	private String q_writername; //qna작성자명
	private int m_no; //회원번호
	
	public Writer(String q_writerid, String q_writername, int m_no) {
		this.q_writerid = q_writerid;
		this.q_writername = q_writername;
		this.m_no = m_no;
	}


	public String getQ_writerid() {
		return q_writerid;
	}

	public String getQ_writername() {
		return q_writername;
	}

	public int getM_no() {
		return m_no;
	}

	@Override
	public String toString() {
		return "Writer [Q_writerid=" + q_writerid + ", Q_writername=" + q_writername + ", M_no=" + m_no + "]";
	}
	
	
	
}