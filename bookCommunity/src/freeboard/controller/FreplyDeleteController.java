package freeboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.service.DeleteService;
import mvc.command.CommandHandler;


//댓글 삭제 컨트롤러
public class FreplyDeleteController implements CommandHandler {
	DeleteService deleteService = new DeleteService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strRno = request.getParameter("rNo");
		int rNo = Integer.parseInt(strRno);
		
		int rcnt = deleteService.deleteFreply(rNo);
		request.setAttribute("rcnt", rcnt);
		
		return "/view/freeboard/fReplyDeleteForm.jsp";
	}

}
