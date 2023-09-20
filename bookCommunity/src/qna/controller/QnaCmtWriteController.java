package qna.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.model.Qna;
import qna.service.WriteQnaService;

//댓글작성 컨트롤러
public class QnaCmtWriteController implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String strQ_no = request.getParameter("q_no");
		int q_no = Integer.parseInt(strQ_no);//원글번호
		
		String writer= request.getParameter("q_cmt_writername");//작성자
		String content= request.getParameter("q_cmt_content"); //댓글내용
		
		//2.비즈니스로직<->Service<->DAO<->DB
		WriteQnaService writeQnaService = new WriteQnaService();
		
		Qna qna= new Qna();
		qna.setQ_no(q_no);//원글번호
		qna.setQ_cmt_writername(writer);//작성자
		qna.setQ_cmt_content(content); //댓글내용
		qna.setRegdate(new Date());//작성일
		
		//return : int=>입력된 레코드수. 입력성공이면1, 실패라면 0리턴
		int cnt = writeQnaService.writeQnaCmt(qna);
		
		//3.MODEL
		request.setAttribute("q_no", q_no);//q_no=원글번호
		request.setAttribute("pageNo",pageNo);//pageNo=보고싶은페이지 //릴레이용
		
		//4.View
		return "/qRead.do?no="+q_no+"&pageNo="+pageNo;
		
	}

}
