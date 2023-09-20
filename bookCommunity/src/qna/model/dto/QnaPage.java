package qna.model.dto;

import java.util.List;
import qna.model.Qna;

public class QnaPage {

		private int total;			 
		private int currentPage;	
		private List<Qna> content;
		private int totalPages;	
		private int startPage;		
		private int endPage;

		public QnaPage(int total, int currentPage, 
						   int size, List<Qna> content) {
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
		public boolean hasNoQnas() {
			return total == 0;
		}
		public boolean hasQnas() {
			return total > 0;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public int getTotalPages() {
			return totalPages;
		}
		
		//게시글목록정보
		public List<Qna> getContent() {
			return content;
		}
		public int getStartPage() {
			return startPage;
		}
		public int getEndPage() {
			return endPage;
		}
		@Override
		public String toString() {
			return "QnaPage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPages="
					+ totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
		}
}
