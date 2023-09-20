package freeboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.service.DeleteService;
import mvc.command.CommandHandler;

//글 삭제컨트롤러
public class DeleteController implements CommandHandler {

	private DeleteService deleteService = new DeleteService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		
				
		
		int cnt = deleteService.deleteFreeboard(no);
				
		
		request.setAttribute("cnt", cnt);
		
				
		return "/view/freeboard/fDeleteForm.jsp";
		
	}

}
