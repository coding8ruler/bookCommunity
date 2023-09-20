package notice.model;

import java.util.Date;
import notice.model.dto.Writer;

public class Notice {

	private Integer n_no;
	private int m_no;
	private Writer n_writer;
	private String n_title;
	private String n_content;
	private Date regdate;
	private int n_cnt;
	
	public Notice(Integer n_no, int m_no, Writer n_writer, String n_title, String n_content, Date regdate, int n_cnt) {
		super();
		this.n_no = n_no;
		this.m_no = m_no;
		this.n_writer = n_writer;
		this.n_title = n_title;
		this.n_content = n_content;
		this.regdate = regdate;
		this.n_cnt = n_cnt;
	}
	public Integer getN_no() {
		return n_no;
	}

	public int getM_no() {
		return m_no;
	}

	public Writer getN_writer() {
		return n_writer;
	}

	public String getN_title() {
		return n_title;
	}

	public String getN_content() {
		return n_content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public int getN_cnt() {
		return n_cnt;
	}
	
	@Override
	public String toString() {
		return "Notice [n_no=" + n_no + ", m_no=" + m_no + ", n_writer=" + n_writer + ", n_title=" + n_title
				+ ", n_content=" + n_content + ", regdate=" + regdate + ", n_cnt=" + n_cnt + "]";
	}
	

}