package freeboard.model;

//작성자 관련 정보
public class Fwriter {
	
	private String writerId;
	private String writerName;
	
	public Fwriter(String writerId, String writerName) {
		this.writerId = writerId;
		this.writerName = writerName;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	@Override
	public String toString() {
		return "Writer [writerId=" + writerId + ", writerName=" + writerName + "]";
	}
	
	
	
}