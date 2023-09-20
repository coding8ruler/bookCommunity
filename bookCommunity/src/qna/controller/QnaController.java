package qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.User;
import mvc.command.CommandHandler;
import qna.model.dto.QnaPage;
import qna.service.QnaService;

public class QnaController implements CommandHandler {

   private static final String FORM_VIEW = "/view/qna/listQna.jsp";
   private QnaService qnaService = new QnaService();
   
   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      String strPageNo = request.getParameter("pageNo");
      int pageNo = 1;
      if(strPageNo!=null) {
         pageNo = Integer.parseInt(strPageNo);
      }
      
      String strRowSize = request.getParameter("rowSize");
      int size = 1;
      if(strRowSize==null) {
         size = 10;
      }else {
         size = Integer.parseInt(strRowSize);
      }
   
      QnaPage qnaPage = qnaService.getQnaPage(pageNo, size);
      
      request.setAttribute("qnaPage", qnaPage);
      request.setAttribute("size", size);
      
      return FORM_VIEW;
   }
}
