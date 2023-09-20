<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="description" content="member board Web Application">
 <meta name="keywords" content="member, board, article, mvc">
 <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>문의게시판 글내용</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mP.css" />
	
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
 	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/readPage.css" /> 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
 <title></title>
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<style>
	.center {
		text-align: center;
	}
	body {
	  padding:1.5em;
	  background: #f5f5f5
	}	
	table {
	  border: 1px #a39485 solid;
	  font-size: .9em;
	  box-shadow: 0 2px 5px rgba(0,0,0,.25);
	  width: 100%;
	  border-collapse: collapse;
	  border-radius: 5px;
	  overflow: hidden;
	  margin: auto;
	}	
	th {
	  text-align: center;
	}	  
	thead {
	  font-weight: bold;
	  color: #fff;
	  background: #73685d;
	}  
	td, th {
	  padding: 1em .5em;
	  vertical-align: middle;
	}  
	td {
	  border-bottom: 1px solid rgba(0,0,0,.1);
	  background: #fff;
	}
	a {
	  color: #73685d;
	  text-align: center;
	} 
	.fileButton {
	  padding: 6px 25px;
	  background-color:#00a2ff;
	  border-radius: 4px;
	  color: white;
	  cursor: pointer;
	}
	input {
	  width: 400px;
	  height: 30px; 	
	}
	#btnDelete, #btnList, #btnUpdate {
	  width: 80px;
	  height: 30px; 	
	} 
	#homeDiv, #btnDiv {
	  text-align: center;	
	}
	h2 { text-align: center; }
	</style>
 <script>
	
	let sessionInfo = "<c:out value='${sessionScope.authUser}'/>";
	let contentVal;
	
	//로그인진행여부 확인
	function loginChk(){
		if(sessionInfo==""){
			let r=confirm("로그인을 진행하시겠습니까?");
			if(r){
				location.href="<%=request.getContextPath()%>/login.do";
			}
		}
	}
	
  //필수입력체크
  function valid(){
	  contentVal = $("#q_cmt_content").val();
	  if(contentVal==""){
		  alert("내용을 입력하세요");
		  $("#q_cmt_content").focus();
		  return false;
	  }
  }
  
 	$(document).ready(function(){

 		//댓글쓰기
 		  $("#btnInsCmt").on("click",function(){
 			 let contentVal=document.getElementById("qqq").value;
 			  loginChk();//로그인체크->권한체크
 			  if(sessionInfo!=""){
 				   if(contentVal==""){
 					  	alert("내용을 입력하세요");
 					  document.getElementById("qqq").focus();
 					  return false;
 				  } 
 			  	$("#frmInsCmt").submit();
 			  }
 		  });
 		  
 		  //숨겨진 수정폼 보여주기
	 		$(".btnCmtU").click(function(){
	 			let rNoVal=$(this).attr("param");//댓글번호
	 			$("#frmUpdateCmt"+rNoVal).show();
	 		});
	 		
	 		  let noVal =${qnaData.qna.q_no}; 
				let pageNoVal=${pageNo}; 		
		 		
	 		//댓글 삭제
	 		$(".btnCmtD").click(function(){
	 				let rNoVal = $(this).attr("param");
	 				
	 				let queryS = {q_cmt_no:rNoVal,no:noVal,pageNo:pageNoVal};	
	 				console.log(queryS);
	 				
	 				$.ajax({
	 					type:"POST",//요청방식.
	 					url:"<%=request.getContextPath()%>/qCmtDelete.do", //요청url.
	 					data:queryS,//서버로 전송할 데이터.
	 					success:function(data){
	 						location.href="<%=request.getContextPath()%>/qRead.do?no=${qnaData.qna.q_no}&pageNo=${pageNo}";
	 					}//정상응답후 호출되는 함수
	 				});//aJax끝
	 			 }); //댓글삭제버튼 클릭시 호출함수끝
	 			 
	 		
	 		 //댓글수정처리  
	 			 $(".btnUpdateR").click(function(){
	 				let rNoVal = $(this).attr("param");
	 				let queryS = {q_cmt_no:rNoVal,
	 											 q_cmt_content:$("#eee"+rNoVal).val(),
	 											no:noVal,pageNo:pageNoVal};	
	 				
	 				if($("#eee"+rNoVal).val()==""){
	 					alert("수정할 내용을 입력하세요");
	 					$("#eee").focus();
	 					return false
	 				}
	 				console.log(queryS);
	 				$.ajax({
	 					type:"POST",//요청방식.
	 					url:"<%=request.getContextPath()%>/qCmtUpdate.do", //요청url.
	 					data:queryS,//서버로 전송할 데이터.
	 					success:function(data){
	 						location.href="<%=request.getContextPath()%>/qRead.do?no=${qnaData.qna.q_no}&pageNo=${pageNo}";
	 					}//정상응답후 호출되는 함수
	 				});//aJax끝
	 			 }); //댓글수정버튼 클릭시 호출함수끝
	});//jQuery끝


 
	//스크롤 따라다니는 메뉴박스 만들기
	$(document).ready(function(){
	$(window).scroll(function(){  //스크롤이 움직일때마다 이벤트 발생
	      var position = $(window).scrollTop()+200; // 현재 스크롤바의 위치값을 반환
	      $("#Quick").stop().animate({top:position+"px"}, 400); //해당 오브젝트 위치값 재설정
	   });

	});

 </script>
</head>
<body>
<header id="header" role="banner">
        <div id="site-title" name="site-title">
        <c:if test="${! empty authUser && (authUser.grade==999)}">	 
            <a href=""><h1></h1></a>      <!--사이트 제목-->
            <nav id="gnb" class="gnb">          <!-- 네비게이션 바-->
                <ul id="nav-ul">
                  <!--각자 맞게 자기 게시판에 태그 걸면 됩니다.-->
                  
                  <li><a href="notice.do">공지사항</a></li>
                  <li><a href="event.do">이벤트</a></li>
                  <li><a href="recomboardListboard.do">도서추천</a></li>
                  <li><a href="blist.do">독후감</a></li>
                  <li><a href="fList.do">자유게시판</a></li>
                  <li><a href="qList.do">1:1문의</a></li>
                  <li><a href="memberList.do">회원관리</a></li>
                  <li><a href="logout.do">로그아웃</a></li>
                  <li><a href="myPage.do">내 정보</a></li>
                </ul>
            </nav>
          	</c:if>
       	 <c:if test="${! empty authUser && (authUser.grade!=999)}">	  
            <a href=""><h1></h1></a>      <!--사이트 제목-->
            <nav id="gnb" class="gnb">          <!-- 네비게이션 바-->
                <ul id="nav-ul">
                  <!--각자 맞게 자기 게시판에 태그 걸면 됩니다.-->
                  
                  <li><a href="notice.do">공지사항</a></li>
                  <li><a href="event.do">이벤트</a></li>
                  <li><a href="recomboardListboard.do">도서추천</a></li>
                  <li><a href="blist.do">독후감</a></li>
                  <li><a href="fList.do">자유게시판</a></li>
                  <li><a href="qList.do">1:1문의</a></li>
                  <li><a href="logout.do">로그아웃</a></li>
                  <li><a href="myPage.do">내 정보</a></li>
                </ul>
            </nav>
          	</c:if>
          	<c:if test="${empty authUser}">
            <a href=""><h1></h1></a>      <!--사이트 제목-->
            <nav id="gnb" class="gnb">          <!-- 네비게이션 바-->
                <ul id="nav-ul">
                  <!--각자 맞게 자기 게시판에 태그 걸면 됩니다.-->
                  
                  <li><a href="notice.do">공지사항</a></li>
                  <li><a href="event.do">이벤트</a></li>
                  <li><a href="recomboardListboard.do">도서추천</a></li>
                  <li><a href="blist.do">독후감</a></li>
                  <li><a href="fList.do">자유게시판</a></li>
                  <li><a href="qList.do">1:1문의</a></li>
                  <li><a href="login.do">로그인/회원가입</a></li>
                </ul>
            </nav>
          	</c:if>
        </div>
      </header>
<div id="Quick" class="" style="position: absolute; right: 10px; top: 400px;">
    <table class="quickMenuBar" style=" border: 1px solid red; border-radius: 20px;">
        <tr>
            <td colspan="2" style="cursor:pointer; background-color:white;" onclick="location.href='<%=request.getContextPath()%>/index.do'">HOME</td>
        </tr>
        <tr>
            <td colspan="2" style="cursor:pointer; background-color:#E2E2E2;" onclick="window.scrollTo(0,0);">TOP</td>
        </tr>
    </table>
</div>
<br/><br/><br/><br/>
	<h2>Q&A</h2>
    <hr/>
    <%-- 원글상세출력 -------------------------- --%>
    <table border="1" style="height:200px; width: 400px;">
    	<tr>
    		<th>번호</th>
  			<td>${qnaData.qna.q_no}</td>
    	</tr>
    	<tr>
    		<th>작성자</th>
    		<td>${qnaData.qna.q_writer.q_writername}</td>
    	</tr>
    	<tr>
    		<th>작성일</th>
    		<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${qnaData.qna.regdate}"/></td>
    	</tr>
    	<tr>
    		<th>조회수</th>
    		<td>${qnaData.qna.q_cnt}</td>
    	</tr>
    	<tr>
    		<th>제목</th>
    		<td>${qnaData.qna.q_title}</td>
    	</tr>
    	<tr>
    		<th>내용</th>
    		<td><u:pre value="${qnaData.qna.q_content}" /></td>
    	</tr>
    </table>
    
    
    <div id="btnDiv">
    	<c:set var="pgNo" value="${ (empty param.pageNo) ? '1' : param.pageNo}"></c:set>
    	<a href="<%=request.getContextPath()%>/qList.do?rowSize=${rowSize}">목록보기</a>
			<!-- 관리자 글수정 -->
    	<c:if test="${authUser.grade == 999 && (authUser.mId == qnaData.qna.q_writer.q_writerid)}">
	    <a href="<%=request.getContextPath()%>/qUpdate.do?no=${qnaData.qna.q_no}&pageNo=${pgNo}&rowSize=${rowSize}">글 수정</a>
			</c:if>
			<!-- 관리자 회원글 글삭제 -->
    	<c:if test="${authUser.grade == 999}">
	    <a href="<%=request.getContextPath()%>/qDelete.do?no=${qnaData.qna.q_no}">글 삭제</a>
	    </c:if>
	    
	    <!-- 글작성자일 경우 메뉴 -->
      <c:if test="${authUser.grade!=999 && (authUser.mId == qnaData.qna.q_writer.q_writerid)}">
	    <a href="<%=request.getContextPath()%>/qUpdate.do?no=${qnaData.qna.q_no}&pageNo=${pgNo}&rowSize=${rowSize}">글 수정</a>
	    <a href="<%=request.getContextPath()%>/qDelete.do?no=${qnaData.qna.q_no}">글 삭제</a>
	    </c:if>


	</div>
<br/><br/><br/>
<div class="container">
<%-- 댓글목록출력 -------------------------- --%>
 <%-- c:if이용 댓글이 없는 경우 --%>
 <c:if test="${empty qnaCmtList}">
 <table border="1" >
  <tbody>
   <tr>
    <td colspan="4" class="center">
        등록된 댓글이 없습니다. 첫 댓글의 주인공이 되세요~.
    </td>
   </tr>
  </tbody>
 </table> 
 </c:if>
 
  <%-- c:if이용 댓글이 있는 경우 c:forEach이용 반복출력 --%>
 <c:if test="${not empty qnaCmtList}">
  <c:forEach var="qnaCmt" items="${qnaCmtList}">
	 <table border="1">
	  <tbody>
	   <tr>
	    <th>댓번호</th><td>${qnaCmt.q_cmt_no}</td>
	    <th>원글번호</th><td>${qnaCmt.q_no}</td>
	   </tr>
	   <tr>
	    <th>작성자</th><td>${qnaCmt.q_cmt_writername}</td>
	    <th>댓글작성일</th><td>${qnaCmt.regdate}</td>
	   </tr>
	   <tr>
	    <th>내용</th>
	    <td colspan="3"><u:pre value="${qnaCmt.q_cmt_content}"/></td>
	   </tr>
	   <tr>
	    <td colspan="4" class="center">
	    <c:if test="${authUser.grade==999}">
	       <button type="button" id="btnCmtUpdate${qnaCmt.q_cmt_no}" class="btnCmtU" param="${qnaCmt.q_cmt_no}">댓글수정</button> 
	      <button type="button" id="btnCmtDelete${qnaCmt.q_cmt_no}" class="btnCmtD" param="${qnaCmt.q_cmt_no}">댓글삭제</button> 
	   </c:if> 
	    
	    <c:if test="${authUser.grade!=999 && (authUser.mName == qnaCmt.q_cmt_writername)}">
	      <button type="button" id="btnCmtUpdate${qnaCmt.q_cmt_no}" class="btnCmtU" param="${qnaCmt.q_cmt_no}">댓글수정</button> 
	      <button type="button" id="btnCmtDelete${qnaCmt.q_cmt_no}" class="btnCmtD" param="${qnaCmt.q_cmt_no}">댓글삭제</button> 
	   </c:if> 
	    </td>
	   </tr>
	 	</tbody>
	   <br>
	 </table> <%-- 댓글상세table끝 --%>
	 
	 	<%-- 댓글 수정 폼 -------------------------- --%>
	 <form name="frmUpdateCmt" id="frmUpdateCmt${qnaCmt.q_cmt_no}" 
	       action="<%=request.getContextPath()%>/qCmtUpdate.do" 
	       method="post" style="display:none;">
	  pageNo:<input type="text" name="pageNo" id="pageNo${qnaCmt.q_cmt_no}" value="${pageNo}"/>
	  q_no:<input type="text" name="q_no"  id="q_no${qnaCmt.q_cmt_no}"  value="${qnaData.qna.q_no}"/>
	  rNo:<input type="text" name="rNo" id="rNo${qnaCmt.q_cmt_no}" value="${qnaCmt.q_cmt_no}"/> 
	 <table border="1">
	  <tbody>
	   <tr>
	    <th>댓글내용</th>
	    <td colspan="3"><textarea name="eee" id="eee${qnaCmt.q_cmt_no}" cols="50" rows="3"  onclick="loginChk()" placeholder="타인을 배려 하는 마음을 담아 댓글을 달아주세요.&#10;내용에 따라 이용약관 및 관련 법률에 의해 임의 조치를 수행할 수 있습니다.">${qnaCmt.q_cmt_content}</textarea></td>
	   </tr>
	   <tr>
	    <td colspan="4" class="center">
	      <button type="button" id="btnUpdateCmt${qnaCmt.q_cmt_no}" class="btnUpdateR" param="${qnaCmt.q_cmt_no}">댓글수정하기</button>
	    </td>
	   </tr>
	  </tbody>
	 </table> <%-- 댓글수정table끝 --%>
	 </form>
 </c:forEach>
 </c:if>
 <br/><br/><br/>
<%-- 댓글등록 ----------------------------- --%>

 <form name="frmInsCmt" id="frmInsCmt" 
       action="<%=request.getContextPath()%>/qCmtWrite.do" method="post">
  <input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
  <input type="hidden" name="q_no"  id="q_no"  value="${qnaData.qna.q_no}"/>
  <input type="hidden" name="writer" id="writer" value="${sessionScope.authUser.mId}"/> 
  <input type="hidden" name="q_cmt_writername" value="${authUser.mName}"/>
 <table border="1">
  <tbody>
   <tr>
    <th>댓글내용</th>
 		 <td colspan="3"><textarea name="q_cmt_content" id="qqq" cols="100" rows="3"  onclick="loginChk()" placeholder="타인을 배려 하는 마음을 담아 댓글을 달아주세요.&#10;내용에 따라 이용약관 및 관련 법률에 의해 임의 조치를 수행할 수 있습니다."></textarea></td>
   </tr>
   <tr>
    <td colspan="4" class="center">
      <button type="button" id="btnInsCmt">댓글쓰기</button>
    </td>
   </tr>
  </tbody>
 </table>
 </form>	 
 </div>
 <br/><br/><br/>
 <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> 
</body>
</html>