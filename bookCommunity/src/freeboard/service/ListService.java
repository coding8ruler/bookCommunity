package freeboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import freeboard.DAO.FreeboardDAO;
import freeboard.model.Freeboard;
import freeboard.model.FreeboardPage;
import jdbc.conn.ConnectionProvider;

public class ListService {
	
	private FreeboardDAO freeboardDAO = new FreeboardDAO();
	private int size = 3;
	//목록보기 서비스
	public FreeboardPage getFreeboardPage(int pageNo, int size) {
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			int total = freeboardDAO.selectCount(conn);
			
			List<Freeboard> freeboardList = freeboardDAO.select(conn,(pageNo-1)*size, size);
			
			return new FreeboardPage(total, pageNo, size, freeboardList);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
