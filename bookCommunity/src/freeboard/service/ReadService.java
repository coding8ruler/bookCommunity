package freeboard.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import freeboard.DAO.FreeboardDAO;
import freeboard.exception.FreeboardNotFoundException;
import freeboard.model.Freeboard;
import freeboard.model.FreeboardData;
import freeboard.model.FreplyDTO;

import java.sql.Connection;

import jdbc.conn.ConnectionProvider;
import jdbc.conn.JdbcUtil;

public class ReadService {
	
	Connection conn = null;
	
	private FreeboardDAO freeboardDAO = new FreeboardDAO();
	
	public FreeboardData getFreeboard(int no, boolean increaseFcnt) {
		
		try {
			conn = ConnectionProvider.getConnection();
			
			Freeboard freeboard = freeboardDAO.selectById(conn,no);
			if(freeboard==null) {
				throw new FreeboardNotFoundException();
			}
			//조회수
			if(increaseFcnt) {
				freeboardDAO.increaseFcnt(conn,no);
			}
			return new FreeboardData(freeboard);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}
	
	
	
	//댓글목록조회
	public List<FreplyDTO> selectFreplyList(int fNo) {
		Connection conn = null;
		List<FreplyDTO> freplyList = new ArrayList<FreplyDTO>();
		try {
			conn = ConnectionProvider.getConnection();
			freplyList = freeboardDAO.selectFreplyList(conn, fNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  freplyList;
	
	}
	
	//댓글쓰기
		//prameter : freplyDTO-=>게시글번호,작성자,제목,내용,작성일
		//return : int=>입력된 레코드수. 입력성공이면1, 실패라면 0리턴
		public int writeReply(FreplyDTO freplyDTO) {
			Connection conn = null;
			int result = 0;
			try {
				conn = ConnectionProvider.getConnection();
				result = freeboardDAO.writeReply(conn,freplyDTO);
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn);
			}
			return result;
		}
	

}
