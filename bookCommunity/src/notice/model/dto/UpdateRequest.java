package notice.model.dto;

import java.util.Map;

public class UpdateRequest {

	private String n_writerid;
	private int no;	
	private String n_writername;
	private String n_title;	
	private String n_content;
	
	public UpdateRequest(String n_writerid, int no, String n_writername, String n_title, String n_content) {
		
		this.n_writerid = n_writerid;
		this.no = no;
		this.n_writername = n_writername;
		this.n_title = n_title;
		this.n_content = n_content;
	}
	
	public String getN_writerid() {
		return n_writerid;
	}
	public int getNo() {
		return no;
	}
	public String getN_writername() {
		return n_writername;
	}
	public String getN_title() {
		return n_title;
	}
	public String getN_content() {
		return n_content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(n_title==null || n_title.trim().isEmpty()) {
			errors.put("n_title", Boolean.TRUE);
		}
	}
	@Override
	public String toString() {
		return "UpdateRequest [n_writerid=" + n_writerid + ", no=" + no + ", n_writername=" + n_writername
				+ ", n_title=" + n_title + ", n_content=" + n_content + "]";
	}
	
}
