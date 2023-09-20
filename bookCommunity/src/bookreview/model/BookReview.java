package bookreview.model;

import java.util.Date;

public class BookReview {

	private Integer bNo;		// 게시판 글번호				
	private int brNo;			// 댓글번호 		
	private int mNo;			// 회원번호
	private String bWriterId;
	private String bWriterName;
	
	private String bookTitle;	// 책제목
	private String author;		// 저자
	private String publisher;	// 출판사
	private String bTitle;		// 글제목
	private String bContent;	// 글내용
	private Date regDate;		// 작성일
	private int bCnt;			// 조회수 

	
	
	public BookReview() {}
		
	
	public BookReview(Integer bNo, int mNo, String bookTitle , String author, String publisher, String bWriterId,String bWriterName, String bTitle, String bContent, Date regDate, int bCnt) {
		this.bNo = bNo;
		this.mNo = mNo;
		this.bookTitle= bookTitle;
		this.author=author;
		this.publisher=publisher;
		this.bWriterId=bWriterId;
		this.bWriterName=bWriterName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.regDate = regDate;
		this.bCnt = bCnt;
		
	}
// ----------------------------------------------------------------------------------

	public BookReview(Integer bNo,int brNo, int mNo, String bookTitle , String author, String publisher, String bWriterId,String bWriterName, String bTitle, String bContent, Date regDate, int bCnt) {
		this.bNo = bNo;
		this.brNo = brNo;
		this.mNo = mNo;
		this.bookTitle= bookTitle;
		this.author=author;
		this.publisher=publisher;
		this.bWriterId=bWriterId;
		this.bWriterName=bWriterName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.regDate = regDate;
		this.bCnt = bCnt;
		
	}

	public Integer getbNo() {
		return bNo;
	}
	
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	
	public int getmNo() {
		return mNo;
	}
	public void setMNo(int mNo) {
		this.mNo = mNo;
	}

	public int getbrNo() {
		return brNo;
	}
	public void setBrNo(int brNo) {
		this.brNo = brNo;
	}
	
	public String getbookTitle() {
		return bookTitle;
	}
	public void setbookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getauthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getpublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getbWriterId() {
		return bWriterId;
	}
	public void setBwriterId(String bWriterId) {
		this.bWriterId = bWriterId;
	}
	public String getbWriterName() {
		return bWriterName;
	}
	public void setBwriterName(String bWriterName) {
		this.bWriterName = bWriterName;
	}

	
	public String getbTitle() {
		return bTitle;
	}
	public void setBtitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setBcontent(String bContent) {
		this.bContent = bContent;
	}
	public Date getregDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getbCnt() {
		return bCnt;
	}
	public void setBcnt(int bCnt) {
		this.bCnt = bCnt;
	}




}