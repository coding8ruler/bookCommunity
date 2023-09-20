package qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.DeleteQnaService;

public class QnaDeleteController implements CommandHandler {

	private DeleteQnaService deleteQnaService = new DeleteQnaService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
				
		int cnt = deleteQnaService.deleteQna(no);
				
		req.setAttribute("cnt", cnt);
				
		return "/view/qna/deleteForm.jsp";
		
	}

}
