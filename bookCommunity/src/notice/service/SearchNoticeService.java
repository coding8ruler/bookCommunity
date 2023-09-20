package notice.service;

import java.sql.SQLException;
import java.util.List;

import java.sql.Connection;

import jdbc.conn.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.model.Notice;
import notice.model.dto.SearchPage;


public class SearchNoticeService{

	private NoticeDAO noticeDAO = new NoticeDAO();
	
	private int size = 10;
	String choice = null;
	String searchText = null;
	
	public SearchPage getSearchPage(String choice,String searchText,int pageNo,int size) {
		
	try {	
			Connection conn = ConnectionProvider.getConnection();
		
			int total = noticeDAO.searchCount(conn, choice,searchText);
			
			List<Notice> searchList = noticeDAO.search(conn,choice,searchText,(pageNo-1)*size, size);
			
		  	return new SearchPage(choice,searchText,total,pageNo,size,searchList);
		 
			} catch(SQLException e) {
				
				throw new RuntimeException();
			}
		}

	}