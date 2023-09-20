package freeboard.model;

import java.util.Map;

//글 쓰기 관련 정보
public class FreeWriteRequest {

	private Fwriter writer;
	private String fTitle;
	private String fContent;
	
	public FreeWriteRequest(Fwriter writer, String fTitle, String fContent) {
		this.writer = writer;
		this.fTitle = fTitle;
		this.fContent = fContent;
	}

	public Fwriter getWriter() {
		return writer;
	}

	 public void validate(Map<String, Boolean> errors) {
			if( fTitle==null || fTitle.isEmpty() ) {
				errors.put("fTitle", Boolean.TRUE);
			}
			if( fContent==null || fContent.isEmpty()) {
				errors.put("fContent", Boolean.TRUE);
			}
		}
	public void setWriter(Fwriter writer) {
		this.writer = writer;
	}

	public String getfTitle() {
		return fTitle;
	}

	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	@Override
	public String toString() {
		return "WriteRequest [writer=" + writer + ", fTitle=" + fTitle + ", fContent=" + fContent + "]";
	}

		
	
	
	
}
