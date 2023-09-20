package event.model;

import java.util.Map;

public class WriteRequest {

	private EWriter E_WRITER;
	private String E_TITLE;
	private String E_CONTENT;
	
	public WriteRequest(EWriter E_WRITER, String E_TITLE, String E_CONTENT) {
		super();
		this.E_WRITER = E_WRITER;
		this.E_TITLE = E_TITLE;
		this.E_CONTENT = E_CONTENT;
	}
	public WriteRequest(String E_TITLE, String E_CONTENT) {
		super();
		this.E_TITLE = E_TITLE;
		this.E_CONTENT = E_CONTENT;
	}
	 public void validate(Map<String, Boolean> errors) {
		if( E_TITLE==null || E_TITLE.isEmpty() ) {
			errors.put("E_TITLE", Boolean.TRUE);
		}
		if( E_CONTENT==null || E_CONTENT.isEmpty()) {
			errors.put("E_CONTENT", Boolean.TRUE);
		}
	}
	public EWriter getE_WRITER() {
		return E_WRITER;
	}
	public void setE_WRITER(EWriter e_WRITER) {
		E_WRITER = e_WRITER;
	}
	public String getE_TITLE() {
		return E_TITLE;
	}
	public void setE_TITLE(String e_TITLE) {
		E_TITLE = e_TITLE;
	}
	public String getE_CONTENT() {
		return E_CONTENT;
	}
	public void setE_CONTENT(String e_CONTENT) {
		E_CONTENT = e_CONTENT;
	}
	@Override
	public String toString() {
		return "WriteRequest [E_WRITER=" + E_WRITER + ", E_TITLE=" + E_TITLE + ", E_CONTENT=" + E_CONTENT + "]";
	}
	
}
