package bookreview.model;



public class BookReviewData {
	
	private BookReview bookreview;
	
	public BookReviewData(BookReview bookreview) {
		this.bookreview = bookreview;
	}

	public BookReview getBookReview() {
		return bookreview;
	}

	public void setBookReview(BookReview bookreview) {
		this.bookreview = bookreview;
	}

	@Override
	public String toString() {
		return "BookReviewData [bookreview=" + bookreview + "]";
	}
}
