package notice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.User;
import mvc.command.CommandHandler;
import notice.exception.NoticeNotFoundException;
import notice.exception.PermissionDeniedException;
import notice.model.dto.NoticeData;
import notice.model.dto.UpdateRequest;
import notice.service.ReadNoticeService;
import notice.service.UpdateNoticeService;

public class NoticeUpdateController implements CommandHandler {

	private static final String FORM_VIEW = "/view/notice/updateForm.jsp";
	
		private ReadNoticeService readService = new ReadNoticeService();		
		private UpdateNoticeService updateService = new UpdateNoticeService();
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
		System.out.println("NoticeUpdateHandler processForm");
		
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
			int size = 10;
			if(strRowSize!=null) {
				size = Integer.parseInt(strRowSize);
			}
			
			String n_title = request.getParameter("title");
			String n_content = request.getParameter("content");
			
			NoticeData noticeData = readService.getNotice(no, false);
			
			User authUser = (User) request.getSession().getAttribute("authUser");
			
			if(!canUpdate(authUser, noticeData)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}	
			
			UpdateRequest upReq = new UpdateRequest(authUser.getmId(), 
													 no, 
													 noticeData.getNotice().getN_writer().getN_writername(),
													 noticeData.getNotice().getN_title(), 
													 noticeData.getNotice().getN_content());
			request.setAttribute("upReq", upReq);			
			
			return FORM_VIEW; 
		} catch (NoticeNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeUpdateHandler processSubmit");
	
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
		int size = 10;   
		if(strRowSize!=null) {
			size = Integer.parseInt(strRowSize);
		}
	
		String n_title = request.getParameter("title");
		String n_content = request.getParameter("content");
		String n_writername = request.getParameter("writername");
		User authUser = (User) request.getSession().getAttribute("authUser");
		
		UpdateRequest upReq = new UpdateRequest(authUser.getmId(), 
												 no,
												 n_writername,
												 n_title,
												 n_content);
		request.setAttribute("upReq", upReq);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		upReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			updateService.update(upReq);
			return "/view/notice/updateSuccess.jsp";
		}catch (NoticeNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch (PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}	
	private boolean canUpdate(User authUser, NoticeData noticeData) {
		String userId = authUser.getmId();
		String writerId = noticeData.getNotice().getN_writer().getN_writerid();
		return userId.equals(writerId);
	}
}