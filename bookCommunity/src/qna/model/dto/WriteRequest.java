package qna.model.dto;

import java.util.Map;

public class WriteRequest {

	private Writer Q_WRITER;
	private String Q_TITLE;
	private String Q_CONTENT;
	
	public WriteRequest(Writer Q_WRITER, String Q_TITLE, String Q_CONTENT) {
		super();
		this.Q_WRITER = Q_WRITER;
		this.Q_TITLE = Q_TITLE;
		this.Q_CONTENT = Q_CONTENT;
	}
	public WriteRequest(String Q_TITLE, String Q_CONTENT) {
		super();
		this.Q_TITLE = Q_TITLE;
		this.Q_CONTENT = Q_CONTENT;
	}
	 public void validate(Map<String, Boolean> errors) {
		if( Q_TITLE==null || Q_TITLE.isEmpty() ) {
			errors.put("Q_TITLE", Boolean.TRUE);
		}
		if( Q_CONTENT==null || Q_CONTENT.isEmpty()) {
			errors.put("Q_CONTENT", Boolean.TRUE);
		}
	}
	public Writer getQ_WRITER() {
		return Q_WRITER;
	}
	public void setQ_WRITER(Writer q_WRITER) {
		Q_WRITER = q_WRITER;
	}
	public String getQ_TITLE() {
		return Q_TITLE;
	}
	public void setQ_TITLE(String q_TITLE) {
		Q_TITLE = q_TITLE;
	}
	public String getQ_CONTENT() {
		return Q_CONTENT;
	}
	public void setQ_CONTENT(String q_CONTENT) {
		Q_CONTENT = q_CONTENT;
	}
	@Override
	public String toString() {
		return "WriteRequest [Q_WRITER=" + Q_WRITER + ", Q_TITLE=" + Q_TITLE + ", Q_CONTENT=" + Q_CONTENT + "]";
	}
	
}
