package event.model;

import java.util.Date;
import event.model.EWriter;


public class Event {

	private Integer e_no;
	private int m_no;
	private EWriter e_writer;
	private String e_title;
	private String e_content;
	private Date regdate;
	private int e_cnt;
	
	public Event(Integer e_no, int m_no, EWriter e_writer, String e_title, String e_content, Date regdate, int e_cnt) {
		this.e_no = e_no;
		this.m_no = m_no;
		this.e_writer = e_writer;
		this.e_title = e_title;
		this.e_content = e_content;
		this.regdate = regdate;
		this.e_cnt = e_cnt;
	}
	public Integer getE_no() {
		return e_no;
	}

	public int getM_no() {
		return m_no;
	}

	public EWriter getE_writer() {
		return e_writer;
	}

	public String getE_title() {
		return e_title;
	}

	public String getE_content() {
		return e_content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public int getE_cnt() {
		return e_cnt;
	}
	
	@Override
	public String toString() {
		return "Event [e_no=" + e_no + ", m_no=" + m_no + ", e_writer=" + e_writer + ", e_title=" + e_title
				+ ", e_content=" + e_content + ", regdate=" + regdate + ", e_cnt=" + e_cnt + "]";
	}
	

}