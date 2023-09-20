package qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.DeleteQnaService;

//댓글삭제 컨트롤러
public class QnaCmtDeleteController implements CommandHandler {

	DeleteQnaService deleteQnaService = new DeleteQnaService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
				
			String str_q_cmt_no = request.getParameter("q_cmt_no");
			int q_cmt_no = Integer.parseInt(str_q_cmt_no);
			
			int rcnt = deleteQnaService.deleteQnaCmt(q_cmt_no);
			request.setAttribute("rcnt", rcnt);
				
				return "/view/qna/qnaCmtDeleteSuccess.jsp";
			}

		}
