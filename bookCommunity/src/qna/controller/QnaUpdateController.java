package qna.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.User;
import mvc.command.CommandHandler;
import qna.exception.PermissionDeniedException;
import qna.exception.QnaNotFoundException;
import qna.model.dto.QnaData;
import qna.model.dto.UpdateRequest;
import qna.service.ReadQnaService;
import qna.service.UpdateQnaService;

public class QnaUpdateController implements CommandHandler {

	private static final String FORM_VIEW = "/view/qna/updateForm.jsp";
	
		private ReadQnaService readService = new ReadQnaService();		
		private UpdateQnaService updateService = new UpdateQnaService();
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
			
			String q_title = request.getParameter("title");
			String q_content = request.getParameter("content");
			
			QnaData qnaData = readService.getQna(no, false);
			
			User authUser = (User) request.getSession().getAttribute("authUser");
			
			if(!canUpdate(authUser, qnaData)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}	
			
			UpdateRequest upReq = new UpdateRequest(authUser.getmId(), 
													 no, 
													 qnaData.getQna().getQ_writer().getQ_writername(),
													 qnaData.getQna().getQ_title(), 
													 qnaData.getQna().getQ_content());
			request.setAttribute("upReq", upReq);			
			
			return FORM_VIEW; 
		} catch (QnaNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
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
	
		String q_title = request.getParameter("title");
		String q_content = request.getParameter("content");
		String q_writername = request.getParameter("writername");
		User authUser = (User) request.getSession().getAttribute("authUser");
		
		UpdateRequest upReq = new UpdateRequest(authUser.getmId(), 
												 no,
												 q_writername,
												 q_title,
												 q_content);
		request.setAttribute("upReq", upReq);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		upReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			updateService.update(upReq);
			return "/view/qna/updateSuccess.jsp";
		}catch (QnaNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch (PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}	
	private boolean canUpdate(User authUser, QnaData qnaData) {
		String userId = authUser.getmId();
		String writerId = qnaData.getQna().getQ_writer().getQ_writerid();
		return userId.equals(writerId);
	}
}