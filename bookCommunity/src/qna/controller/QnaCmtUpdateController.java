package qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.UpdateQnaService;

//댓글수정 컨트롤러
public class QnaCmtUpdateController implements CommandHandler {
	
	private UpdateQnaService updateQnaService = new UpdateQnaService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {


		int q_cmt_no = Integer.parseInt(request.getParameter("q_cmt_no"));
		String q_cmt_content = request.getParameter("q_cmt_content");
		
		int result = updateQnaService.updateQnaCmt(q_cmt_content, q_cmt_no);
		
		request.setAttribute("q_cmt_no",q_cmt_no);
		request.setAttribute("q_cmt_content", q_cmt_content);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(result);
		
		return null;
	}

 }
	
	

