package qna.model;

import java.util.Date;

import qna.model.dto.Writer;

public class Qna {

	private Integer q_no; 				//qna글번호
	private String q_title; 			//qna글제목
	private String q_content; 			//qna글내용
	private Writer q_writer; 			//qna작성자
	private int q_cnt; 					//qna조회수
	private Date regdate; 				//qna작성일
	
	private int    q_cmt_no;			//댓글번호
	private String q_cmt_writername;	//댓글작성자
	private String q_cmt_content;		//댓글내용
	
	public Qna(Integer q_no, String q_title, String q_content, Writer q_writer, int q_cnt, Date regdate) {
		super();
		this.q_no = q_no;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_writer = q_writer;
		this.q_cnt = q_cnt;
		this.regdate = regdate;
	}
	

	public Qna(Integer q_no, String q_title, String q_content, Writer q_writer, int q_cnt, Date regdate,
			 int q_cmt_no, String q_cmt_writername, String q_cmt_content) {
		super();
		this.q_no = q_no;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_writer = q_writer;
		this.q_cnt = q_cnt;
		this.regdate = regdate;
		this.q_cmt_no = q_cmt_no;
		this.q_cmt_writername = q_cmt_writername;
		this.q_cmt_content = q_cmt_content;
	}



	public Qna(Integer q_no, Date regdate, int q_cmt_no, String q_cmt_writername, String q_cmt_content) {
		super();
		this.q_no = q_no;
		this.regdate = regdate;
		this.q_cmt_no = q_cmt_no;
		this.q_cmt_writername = q_cmt_writername;
		this.q_cmt_content = q_cmt_content;
	}



	public Qna(int newNum, String q_title2, String q_content2, String q_writerid, String q_writername, int m_no, int i,
			Date regdate2, boolean b) {
	}



	public Qna() {
	}



	public Integer getQ_no() {
		return q_no;
	}



	public void setQ_no(Integer q_no) {
		this.q_no = q_no;
	}



	public String getQ_title() {
		return q_title;
	}



	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}



	public String getQ_content() {
		return q_content;
	}



	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}



	public Writer getQ_writer() {
		return q_writer;
	}



	public void setQ_writer(Writer q_writer) {
		this.q_writer = q_writer;
	}



	public int getQ_cnt() {
		return q_cnt;
	}



	public void setQ_cnt(int q_cnt) {
		this.q_cnt = q_cnt;
	}



	public Date getRegdate() {
		return regdate;
	}



	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}


	public int getQ_cmt_no() {
		return q_cmt_no;
	}



	public void setQ_cmt_no(int q_cmt_no) {
		this.q_cmt_no = q_cmt_no;
	}



	public String getQ_cmt_writername() {
		return q_cmt_writername;
	}



	public void setQ_cmt_writername(String q_cmt_writername) {
		this.q_cmt_writername = q_cmt_writername;
	}



	public String getQ_cmt_content() {
		return q_cmt_content;
	}



	public void setQ_cmt_content(String q_cmt_content) {
		this.q_cmt_content = q_cmt_content;
	}



	@Override
	public String toString() {
		return "Qna [q_no=" + q_no + ", q_title=" + q_title + ", q_content=" + q_content + ", q_writer=" + q_writer
				+ ", q_cnt=" + q_cnt + ", regdate=" + regdate + ",  q_cmt_no="
				+ q_cmt_no + ", q_cmt_writername=" + q_cmt_writername + ", q_cmt_content=" + q_cmt_content + "]";
	}

	
	}
	
	
	
	
	
	
	

