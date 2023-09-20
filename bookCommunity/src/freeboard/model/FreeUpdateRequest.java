package freeboard.model;

import java.util.Map;

//글 수정 관련 정보
public class FreeUpdateRequest {

	private String writerId;
	private int no;	
	private String writerName;
	private String fTitle;	
	private String fContent;
	
	public FreeUpdateRequest(String writerId, int no, String writerName, String fTitle, String fContent) {
		this.writerId = writerId;
		this.no = no;
		this.writerName = writerName;
		this.fTitle = fTitle;
		this.fContent = fContent;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
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
	
	public void validate(Map<String, Boolean> errors) {
		if(fTitle==null || fTitle.trim().isEmpty()) {
			errors.put("fTitle", Boolean.TRUE);
		}
	}

	@Override
	public String toString() {
		return "UpdateRequest [writerId=" + writerId + ", no=" + no + ", writerName=" + writerName + ", fTitle="
				+ fTitle + ", fContent=" + fContent + "]";
	}
	
	
	
	
}
