package freeboard.model;

import java.util.List;

//페이지 관련 정보
public class FreeboardPage {

		private int total;			 
		private int currentPage;	
		private List<Freeboard> content;
		private int totalPages;	
		private int startPage;		
		private int endPage;

		public FreeboardPage(int total, int currentPage, 
						   int size, List<Freeboard> content) {
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
		
		public boolean hasNoFreeboards() {
			return total == 0;
		}
		public boolean hasFreeboards() {
			return total > 0;
		}


		public int getCurrentPage() {
			return currentPage;
		}



		public List<Freeboard> getContent() {
			return content;
		}



		public int getTotalPages() {
			return totalPages;
		}



		public int getStartPage() {
			return startPage;
		}



		public int getEndPage() {
			return endPage;
		}



		@Override
		public String toString() {
			return "FreeboardPage [total=" + total + ", currentPage=" + currentPage + ", content=" + content
					+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
		}
		
}
