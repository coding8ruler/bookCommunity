package qna.model.dto;

import java.util.Map;

public class UpdateRequest {

	private String q_writerid; //원글작성자id
	private int no;	
	private String q_writername;
	private String q_title;	
	private String q_content;
	
	public UpdateRequest(String q_writerid, int no, String q_writername, String q_title, String q_content) {
		
		this.q_writerid = q_writerid;
		this.no = no;
		this.q_writername = q_writername;
		this.q_title = q_title;
		this.q_content = q_content;
	}
	
	public String getQ_writerid() {
		return q_writerid;
	}
	public int getNo() {
		return no;
	}
	public String getQ_writername() {
		return q_writername;
	}
	public String getQ_title() {
		return q_title;
	}
	public String getQ_content() {
		return q_content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(q_title==null || q_title.trim().isEmpty()) {
			errors.put("q_title", Boolean.TRUE);
		}
	}
	@Override
	public String toString() {
		return "UpdateRequest [q_writerid=" + q_writerid + ", no=" + no + ", q_writername=" + q_writername
				+ ", q_title=" + q_title + ", q_content=" + q_content + "]";
	}
	
}
