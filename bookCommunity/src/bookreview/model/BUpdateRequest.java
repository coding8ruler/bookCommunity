package bookreview.model;

import java.util.Map;

import member.model.User;

public class BUpdateRequest {
	
	private int no;
	
	private String bookTitle;
	private String author;
	private String publisher;
	private User user;
	private String bWriterId;
	private String bWriterName;
	private String bTitle;	
	private String bContent;
	
	public BUpdateRequest(int no,String bookTitle, String author, String publisher, String bWriterName, String bTitle, String bContent,User user) {
		
		this.no = no;
		
		this.bookTitle=bookTitle;
		this.author=author;
		this.publisher=publisher;
		this.user=user;
		
		this.bWriterName = bWriterName;
		this.bTitle = bTitle;
		this.bContent = bContent;
	}
	public BUpdateRequest(int no,String bookTitle, String author, String publisher, String bWriterName, String bTitle, String bContent) {
		
		this.no = no;
		this.bookTitle=bookTitle;
		this.author=author;
		this.publisher=publisher;
		
		this.bWriterName = bWriterName;
		this.bTitle = bTitle;
		this.bContent = bContent;
	}
	public int getNo() {
		return no;
	}
	
	public String getbookTitle() {
		return bookTitle;
	}
	public String getauthor() {
		return author;
	}
	public String getpublisher() {
		return publisher;
	}

	public User getUser() {
		return user;
	}
	
	public String getbWriterId() {
		return bWriterId;
	}
	public String getbWriterName() {
		return bWriterName;
	}
	public String getbTitle() {
		return bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(bookTitle==null || bookTitle.trim().isEmpty()) {
			errors.put("bookTitle", Boolean.TRUE);
		}
		if(author==null || author.trim().isEmpty()) {
			errors.put("author", Boolean.TRUE);
		}
		if(publisher==null || publisher.trim().isEmpty()) {
			errors.put("publisher", Boolean.TRUE);
		}
		if(bTitle==null || bTitle.trim().isEmpty()) {
			errors.put("bTitle", Boolean.TRUE);
		}
		if(bContent==null || bContent.trim().isEmpty()) {
			errors.put("bContent", Boolean.TRUE);
		}

	}
	@Override
	public String toString() {
		return "BUpdateRequest [no=" + no + ", bookTitle=" + bookTitle + ", author=" + author + ", publisher="
				+ publisher + ", user=" + user + ", bWriterId=" + bWriterId + ", bWriterName=" + bWriterName
				+ ", bTitle=" + bTitle + ", bContent=" + bContent + "]";
	}

}
