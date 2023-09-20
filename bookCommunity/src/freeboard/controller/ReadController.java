package freeboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.FreeboardData;
import freeboard.model.FreplyDTO;
import freeboard.service.ReadService;
import mvc.command.CommandHandler;

//글 상세보기 & 글에 대한 댓글 보기 컨트롤러
public class ReadController implements CommandHandler {

	private ReadService readService = new ReadService();
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		String strNo =  req.getParameter("no");
		if(strNo==null) {
			throw new RuntimeException(); 
		}
		int no = Integer.parseInt(strNo);
	
		String strPageNo = req.getParameter("pageNo");
		
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		String strRowSize = req.getParameter("rowSize");
		
		int size = 3;
		if(strRowSize!=null) {
			size = Integer.parseInt(strRowSize);
		}
		
		
		
		//댓글목록조회
		List<FreplyDTO> freplyList = readService.selectFreplyList(no);
		
		FreeboardData freeboardData = readService.getFreeboard(no, true);
		req.setAttribute("freplyList",freplyList);//댓글목록
		req.setAttribute("freeboardData", freeboardData);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("rowSize", size);
		
		return "/view/freeboard/fReadForm.jsp";
	}

}
