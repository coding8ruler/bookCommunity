package member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.exception.LoginFailException;
import member.model.User;
import member.service.LoginService;
import mvc.command.CommandHandler;

//p.606
//�씠 而⑦듃濡ㅻ윭 �겢�옒�뒪�뒗 濡쒓렇�씤 �슂泥��뿉 �뵲�씪 �샇異쒕릺�뒗 �겢�옒�뒪�씠�떎.
public class LoginController implements CommandHandler {

	//�븘�뱶
	private static final String FORM_VIEW="view/member/loginForm.jsp";
	private LoginService loginService = new LoginService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginHandler�쓽 process()�샇異�");
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		}
		else if(request.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(request, response);
		}
		else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
		
	private String processForm(HttpServletRequest request, HttpServletResponse response){
		System.out.println("processForm()吏꾩엯");
		return FORM_VIEW;
			
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String mId = trim(request.getParameter("mId"));
		System.out.println(mId);
		String mPwd = trim(request.getParameter("mPwd"));
		System.out.println(mPwd);
		
		//�뿉�윭 泥섎━
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		
		if(mId == null || mId.isEmpty())
			errors.put("mId", Boolean.TRUE);
		if(mPwd == null || mPwd.isEmpty())
			errors.put("mPwd", Boolean.TRUE);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		//2.鍮꾩쫰�땲�뒪 濡쒖쭅�닔�뻾<->serivce<->DAO<->DB
		//3.Model & view
		try {
			User user = loginService.login(mId, mPwd);
			request.getSession().setAttribute("authUser", user); //String name, Object value
			response.sendRedirect(request.getContextPath()+"/index.do");
			return null;
			
		}catch(LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
		
	private String trim(String str) {
		return (str==null)? null:str.trim();
	}
	
}
