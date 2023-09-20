package qna.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.model.Qna;
import qna.model.dto.QnaData;
import qna.service.ReadQnaService;

public class QnaReadController implements CommandHandler {

	private ReadQnaService readQnaService = new ReadQnaService();
	
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
		
		//원글상세조회
		QnaData qnaData = readQnaService.getQna(no, true);
		
		//댓글목록조회
		List<Qna> qnaCmtList = readQnaService.selectQnaCmtList(no);
		
		req.setAttribute("qnaCmtList",qnaCmtList); //댓글목록
		req.setAttribute("qnaData", qnaData);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("rowSize", size);
		
		return "/view/qna/readQna.jsp";
	}

}