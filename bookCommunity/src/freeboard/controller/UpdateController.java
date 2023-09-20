package freeboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.exception.FreeboardNotFoundException;
import freeboard.exception.PermissionDeniedException;
import freeboard.model.FreeUpdateRequest;
import freeboard.model.FreeboardData;
import freeboard.service.ReadService;
import freeboard.service.UpdateService;
import member.model.User;
import mvc.command.CommandHandler;

//글 수정 관련 컨트롤러
public class UpdateController implements CommandHandler {

	private static final String FORM_VIEW = "/view/freeboard/fUpdateForm.jsp";
	
		private ReadService readService = new ReadService();		
		private UpdateService updateService = new UpdateService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
			
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("fUpdateController processForm");
		
			String strNo =  request.getParameter("no");
		try {
			if(strNo==null) {
				throw new RuntimeException();  
			}
			int no = Integer.parseInt(strNo);
			
			String strPageNo = request.getParameter("pageNo");
			int pageNo = 1;
			if(strPageNo!=null) {
				pageNo = Integer.parseInt(strPageNo);
			}
			
			String strRowSize = request.getParameter("rowSize");
			int size = 3;
			if(strRowSize!=null) {
				size = Integer.parseInt(strRowSize);;
			}
			
			String fTitle = request.getParameter("fTitle");
			String fContent = request.getParameter("fContent");
			
			FreeboardData freeboardData = readService.getFreeboard(no, false);
			
			User authUser = (User) request.getSession().getAttribute("authUser");
			
			if(!canUpdate(authUser, freeboardData)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}	
			
			FreeUpdateRequest upReq = new FreeUpdateRequest(authUser.getmId(), 
													 no, 
													 freeboardData.getFreeboard().getfWriter().getWriterName(),
													 freeboardData.getFreeboard().getfTitle(), 
													 freeboardData.getFreeboard().getfContent());
			request.setAttribute("upReq", upReq);			
			
			return FORM_VIEW; 
		} catch (FreeboardNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("fUpdateController processSubmit");
	
		String strNo =  request.getParameter("no");
		if(strNo==null) {
			throw new RuntimeException();
		}
		int no = Integer.parseInt(strNo);

		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		String strRowSize = request.getParameter("rowSize");
		int size = 3;   
		if(strRowSize!=null) {
			size = Integer.parseInt(strRowSize);;
		}
	
		String fTitle = request.getParameter("fTitle");
		String fContent = request.getParameter("fContent");
		String writerName = request.getParameter("writerName");
		User authUser = (User) request.getSession().getAttribute("authUser");
		
		FreeUpdateRequest upReq = new FreeUpdateRequest(authUser.getmId(), 
												 no,
												 writerName,
												 fTitle,
												 fContent);
		request.setAttribute("upReq", upReq);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		upReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			updateService.update(upReq);
			return "/view/freeboard/fUpdateSuccess.jsp";
		}catch (FreeboardNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch (PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}	
	private boolean canUpdate(User authUser, FreeboardData freeboardData) {
		String userId = authUser.getmId();
		String writerId = freeboardData.getFreeboard().getfWriter().getWriterId();
		return userId.equals(writerId);
	}
}