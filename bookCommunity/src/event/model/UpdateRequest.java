package event.model;

import java.util.Map;

public class UpdateRequest {

	private String e_writerid;
	private int no;	
	private String e_writername;
	private String e_title;	
	private String e_content;
	
	public UpdateRequest(String e_writerid, int no, String e_writername, String e_title, String e_content) {
		
		this.e_writerid = e_writerid;
		this.no = no;
		this.e_writername = e_writername;
		this.e_title = e_title;
		this.e_content = e_content;
	}
	
	public String getE_writerid() {
		return e_writerid;
	}
	public int getNo() {
		return no;
	}
	public String getE_writername() {
		return e_writername;
	}
	public String getE_title() {
		return e_title;
	}
	public String getE_content() {
		return e_content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(e_title==null || e_title.trim().isEmpty()) {
			errors.put("e_title", Boolean.TRUE);
		}
	}
	@Override
	public String toString() {
		return "UpdateRequest [e_writerid=" + e_writerid + ", no=" + no + ", e_writername=" + e_writername
				+ ", e_title=" + e_title + ", e_content=" + e_content + "]";
	}
	
}
