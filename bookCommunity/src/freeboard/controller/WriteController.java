package freeboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.FreeWriteRequest;
import freeboard.model.Fwriter;
import freeboard.service.WriteService;
import member.model.User;
import mvc.command.CommandHandler;

//글 쓰기 관련 컨트롤러
public class WriteController implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/freeboard/fWriteForm.jsp";
	private WriteService writeService = new WriteService();
	
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
		System.out.println("fWriteController processForm() ");
		
		String rowSize = request.getParameter("rowSize");
		
		User authUser = loginedUser(request);
		request.setAttribute("authUser", authUser);
		request.setAttribute("rowSize", rowSize);
		
		return FORM_VIEW;
	}
	
	public User loginedUser(HttpServletRequest request) {
		
		User authUser = (User) request.getSession().getAttribute("authUser");
		return authUser;
	}
	
	private FreeWriteRequest createWriteRequest(User authUser, HttpServletRequest request) {
		
		return new FreeWriteRequest( new Fwriter(authUser.getmId(),
				authUser.getmName()), 
				request.getParameter("fTitle"),
				request.getParameter("fContent")
				);
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("fWriteController processSubmit()");
		
		String rowSize = request.getParameter("rowSize");
		User authUser = loginedUser(request);

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		FreeWriteRequest writeReq = createWriteRequest(authUser, request);
		writeReq.validate(errors);
		request.setAttribute("errors", errors);
		
		if(!errors.isEmpty()) {
			request.setAttribute("rowSize", rowSize);
			return FORM_VIEW;
		}
		
		int newFreeboardNo = writeService.write(writeReq);
		
		request.setAttribute("newFreeboardNo", newFreeboardNo);
		request.setAttribute("rowSize", rowSize);
		
		return "/view/freeboard/fWriteSuccess02.jsp";
		}
	
	}
