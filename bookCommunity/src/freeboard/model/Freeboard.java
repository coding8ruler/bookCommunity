package freeboard.model;

import java.util.Date;

//게시판 관련 정보
public class Freeboard {

	private Integer fNo;
	private int mNo;
	private Fwriter fWriter;
	private String fTitle;
	private String fContent;
	private Date regDate;
	private Date modDate;
	private int fCnt;
	
	public Freeboard(Integer fNo, int mNo, Fwriter fWriter, String fTitle, String fContent, Date regDate, Date modDate,
			int fCnt) {
		this.fNo = fNo;
		this.mNo = mNo;
		this.fWriter = fWriter;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.regDate = regDate;
		this.modDate = modDate;
		this.fCnt = fCnt;
	}

	public Integer getfNo() {
		return fNo;
	}

	public void setfNo(Integer fNo) {
		this.fNo = fNo;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public Fwriter getfWriter() {
		return fWriter;
	}

	public void setfWriter(Fwriter fWriter) {
		this.fWriter = fWriter;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public int getfCnt() {
		return fCnt;
	}

	public void setfCnt(int fCnt) {
		this.fCnt = fCnt;
	}

	@Override
	public String toString() {
		return "Freeboard [fNo=" + fNo + ", mNo=" + mNo + ", fWriter=" + fWriter + ", fTitle=" + fTitle + ", fContent="
				+ fContent + ", regDate=" + regDate + ", modDate=" + modDate + ", fCnt=" + fCnt + "]";
	}
	
	
	
	

}