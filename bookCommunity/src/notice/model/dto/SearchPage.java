package notice.model.dto;

import java.util.List;

import notice.model.Notice;

public class SearchPage {
	
	private int total;			 
	private int currentPage;	
	private List<Notice> content;
	private int totalPages;	
	private int startPage;		
	private int endPage;
	private String choice;
	private String searchText;
	
	public SearchPage(String choice,String searchText,int total, int currentPage, int size, List<Notice> content
			) {
		
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total == 0) { 
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else { 
			totalPages = total/size;
			
			if (total%size>0) {      
				totalPages++;    
			}
			int modVal=currentPage%5;  
			
			startPage =currentPage/5*5 + 1;
			if (modVal==0) startPage-=5;
			endPage = startPage + 4;
			if(endPage>totalPages) endPage=totalPages;
		}
	}

	public int getTotal() {
		return total;
	}
	public boolean hasNoSearches() {
		return total == 0;
	}
	public boolean hasSearches() {
		return total > 0;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public List<Notice> getContent() {
		return content;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	public String getChoice() {
		return choice;
	}
	
	public String getSearchText() {
		return searchText;
	}
	
	@Override
	public String toString() {
		return "SerchPage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPages="
				+ totalPages + ", startPage=" + startPage + ", endPage=" + endPage + ", choice=" + choice
				+ ", searchText=" + searchText + "]";
	}
	
}