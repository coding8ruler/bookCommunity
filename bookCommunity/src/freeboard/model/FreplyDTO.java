package freeboard.model;

import java.util.Date;

//댓글 관련 정보
public class FreplyDTO {
	private int rNo;
	private String writerId; 
	private String writerName; 
	private String rTitle;
	private String rContent;
	private Date regeDate;
	
	private int fNo;//fNO 프리보드번호
	
	private int rCnt;//댓글수
	

	public FreplyDTO() {}


	public FreplyDTO(int rNo, String writerId, String writerName, String rTitle, String rContent, Date regeDate,
			int fNo) {
		this.rNo = rNo;
		this.writerId = writerId;
		this.writerName = writerName;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.regeDate = regeDate;
		this.fNo = fNo;
	}
	
	public FreplyDTO(int rNo, String writerId, String rTitle, String rContent, Date regeDate,
			int fNo) {
		this.rNo = rNo;
		this.writerId = writerId;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.regeDate = regeDate;
		this.fNo = fNo;
	}


	//cnt 추가
	public FreplyDTO(int rNo, String writerId, String writerName, String rTitle, String rContent, Date regeDate,
			int fNo, int rCnt) {
		this.rNo = rNo;
		this.writerId = writerId;
		this.writerName = writerName;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.regeDate = regeDate;
		this.fNo = fNo;
		this.rCnt = rCnt;
	}


	public int getrNo() {
		return rNo;
	}


	public void setrNo(int rNo) {
		this.rNo = rNo;
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


	public String getrTitle() {
		return rTitle;
	}


	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}


	public String getrContent() {
		return rContent;
	}


	public void setrContent(String rContent) {
		this.rContent = rContent;
	}


	public Date getRegeDate() {
		return regeDate;
	}


	public void setRegeDate(Date regeDate) {
		this.regeDate = regeDate;
	}


	public int getfNo() {
		return fNo;
	}


	public void setfNo(int fNo) {
		this.fNo = fNo;
	}


	public int getrCnt() {
		return rCnt;
	}


	public void setrCnt(int rCnt) {
		this.rCnt = rCnt;
	}


	@Override
	public String toString() {
		return "FreplyDTO [rNo=" + rNo + ", writerId=" + writerId + ", writerName=" + writerName + ", rTitle=" + rTitle
				+ ", rContent=" + rContent + ", regeDate=" + regeDate + ", fNo=" + fNo + ", rCnt=" + rCnt + "]";
	}


	



	
	
	
}
