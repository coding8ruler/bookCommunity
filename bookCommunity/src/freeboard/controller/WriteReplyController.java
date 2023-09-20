package freeboard.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.FreplyDTO;
import freeboard.service.ReadService;
import mvc.command.CommandHandler;

//댓글 작성 관련 컨트롤러
public class WriteReplyController implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		//String strFno = request.getParameter("fNo");
		int fNo = Integer.parseInt(request.getParameter("fNo"));//원글번호
		String writer= request.getParameter("writer");//작성자
		String rContent= request.getParameter("rContent");
		
		//2.비즈니스로직<->Service<->DAO<->DB
		ReadService readService = new ReadService();
		
		FreplyDTO freplyDTO= new FreplyDTO();
		freplyDTO.setfNo(fNo);//원글번호
		freplyDTO.setWriterId(writer);;//작성자
		freplyDTO.setrContent(rContent);
		freplyDTO.setRegeDate(new Date());//작성일
		
		//return : int=>입력된 레코드수. 입력성공이면1, 실패라면 0리턴
		int cnt = readService.writeReply(freplyDTO);
		
		//3.MODEL
		request.setAttribute("fNo", fNo);//oriNo=원글번호
		request.setAttribute("pageNo",pageNo);//pageNo=보고싶은페이지 //릴레이용
		
		//4.View
		return "/fRead.do";
		
	}

}





