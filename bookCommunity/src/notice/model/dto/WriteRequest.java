package notice.model.dto;

import java.util.Map;

public class WriteRequest {

	private Writer N_WRITER;
	private String N_TITLE;
	private String N_CONTENT;
	
	public WriteRequest(Writer N_WRITER, String N_TITLE, String N_CONTENT) {
		super();
		this.N_WRITER = N_WRITER;
		this.N_TITLE = N_TITLE;
		this.N_CONTENT = N_CONTENT;
	}
	public WriteRequest(String N_TITLE, String N_CONTENT) {
		super();
		this.N_TITLE = N_TITLE;
		this.N_CONTENT = N_CONTENT;
	}
	 public void validate(Map<String, Boolean> errors) {
		if( N_TITLE==null || N_TITLE.isEmpty() ) {
			errors.put("N_TITLE", Boolean.TRUE);
		}
		if( N_CONTENT==null || N_CONTENT.isEmpty()) {
			errors.put("N_CONTENT", Boolean.TRUE);
		}
	}
	public Writer getN_WRITER() {
		return N_WRITER;
	}
	public void setN_WRITER(Writer n_WRITER) {
		N_WRITER = n_WRITER;
	}
	public String getN_TITLE() {
		return N_TITLE;
	}
	public void setN_TITLE(String n_TITLE) {
		N_TITLE = n_TITLE;
	}
	public String getN_CONTENT() {
		return N_CONTENT;
	}
	public void setN_CONTENT(String n_CONTENT) {
		N_CONTENT = n_CONTENT;
	}
	@Override
	public String toString() {
		return "WriteRequest [N_WRITER=" + N_WRITER + ", N_TITLE=" + N_TITLE + ", N_CONTENT=" + N_CONTENT + "]";
	}
	
}
