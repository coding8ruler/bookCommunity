package freeboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.service.UpdateService;
import mvc.command.CommandHandler;

//댓글 수정 컨트롤러
public class FreplyModifyController implements CommandHandler {
	
	private UpdateService updateService = new UpdateService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int rNo = Integer.parseInt(request.getParameter("rNo"));//
		String rContent = request.getParameter("rContent");//내용
		int result = updateService.replyModify( rContent, rNo);
		request.setAttribute("rNo","rNo");
		request.setAttribute("rContent","rContent");
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(result);
		return null;
	}

}
