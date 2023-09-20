package bookreview.model;

import java.util.Map;

public class BWriteRequest {

	
	private String B_WRITER_ID;
	private String B_WRITER_NAME;
	private String B_TITLE;
	private String B_CONTENT;
	
	private String BOOK_TITLE;
	private String AUTHOR;
	private String PUBLISHER;
	
	
	
	public BWriteRequest(String B_WRITER_ID, String B_WRITER_NAME,String BOOK_TITLE,String AUTHOR,String PUBLISHER, String B_TITLE, String B_CONTENT) {
		this.B_WRITER_ID=B_WRITER_ID;
		this.B_WRITER_NAME=B_WRITER_NAME;
		this.B_TITLE = B_TITLE;
		this.B_CONTENT = B_CONTENT;
		this.BOOK_TITLE= BOOK_TITLE;
		this.AUTHOR=AUTHOR;
		this.PUBLISHER=PUBLISHER;
	}
	
	public BWriteRequest(String B_WRITER_ID, String B_WRITER_NAME, String B_TITLE, String B_CONTENT) {
		this.B_WRITER_ID=B_WRITER_ID;
		this.B_WRITER_NAME=B_WRITER_NAME;
		this.B_TITLE = B_TITLE;
		this.B_CONTENT = B_CONTENT;
	}
	public BWriteRequest(String B_TITLE, String B_CONTENT) {
		this.B_TITLE = B_TITLE;
		this.B_CONTENT = B_CONTENT;
	}
	 public void validate(Map<String, Boolean> errors) {
		if( B_TITLE==null || B_TITLE.isEmpty() ) {
			errors.put("B_TITLE", Boolean.TRUE);
		}
		if( B_CONTENT==null || B_CONTENT.isEmpty()) {
			errors.put("B_CONTENT", Boolean.TRUE);
		}
		if( BOOK_TITLE==null || BOOK_TITLE.isEmpty()) {
			errors.put("BOOK_TITLE", Boolean.TRUE);
		}
		if( AUTHOR==null || AUTHOR.isEmpty()) {
			errors.put("AUTHOR", Boolean.TRUE);
		}
		if( PUBLISHER==null || PUBLISHER.isEmpty()) {
			errors.put("PUBLISHER", Boolean.TRUE);
		}
	}
	public String getB_WRITER_ID() {
		return B_WRITER_ID;
	}
	public void setB_WRITER_ID(String b_WRITER_ID) {
		this.B_WRITER_ID = b_WRITER_ID;
	}
	public String getB_WRITER_NAME() {
		return B_WRITER_NAME;
	}
	public void setB_WRITER_NAME(String b_WRITER_NAME) {
		this.B_WRITER_NAME = b_WRITER_NAME;
	}
	
	public String getBOOK_TITLE() {
		return BOOK_TITLE;
	}
	
	public void setBOOK_TITLE(String bOOK_TITLE) {
		BOOK_TITLE = bOOK_TITLE;
	}
	
	public String getAUTHOR() {
		return AUTHOR;
	}
	
	public void setAUTHOR(String aUTHOR) {
		AUTHOR = aUTHOR;
	}
	
	public String getPUBLISHER() {
		return PUBLISHER;
	}
	
	public void setPUBLISHER(String pUBLISHER) {
		PUBLISHER = pUBLISHER;
	}
	public String getB_TITLE() {
		return B_TITLE;
	}
	public void setB_TITLE(String b_TITLE) {
		B_TITLE = b_TITLE;
	}
	public String getB_CONTENT() {
		return B_CONTENT;
	}
	public void setB_CONTENT(String b_CONTENT) {
		B_CONTENT = b_CONTENT;
	}



}
