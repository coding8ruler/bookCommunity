package recommand.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import recommand.service.DeleteBoardService;

public class DeleteBoardController implements CommandHandler {

	private DeleteBoardService deleteBoardService = new DeleteBoardService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터받기
		// 삭제하려는 글 번호
		String strNo = request.getParameter("no"); 
		int no = Integer.parseInt(strNo);
		
		// 2. 비지니스로직 <-> Service <-> DAO <-> DB
		int cnt = deleteBoardService.deleteBoard(no);
		
		// 3. Model
		request.setAttribute("cnt", cnt);
		
		// 4. View
		response.sendRedirect(request.getContextPath()+"/recomboardListboard.do");
		
		return null;
	}

}
