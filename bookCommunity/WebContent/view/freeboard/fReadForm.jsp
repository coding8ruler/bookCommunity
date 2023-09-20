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
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
 	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/readPage.css" /> 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
 <title></title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mP.css" />
 
 <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<style>
	.center{text-align: center;}
	</style>
 <script>
	let sessionInfo = "<c:out value='${sessionScope.authUser}'/>";
	let titleVal,contentVal;
	
	//로그인진행여부 확인
	function loginChk(){
		if(sessionInfo==""){
			let r=confirm("로그인을 진행하시겠습니까?");
			if(r){
				location.href="<%=request.getContextPath()%>/login.do";
			}
		}
	}

  
  //댓글쓰기 필수입력체크
  function writeValid(){
	  contentVal = $("#rContent").val();
	  if(contentVal==""){
		  alert("내용을 입력하세요");
		  $("#rContent").focus();
		  return false;
	  }
	  $("#frmInsReply").submit()
  }
 	
  $(document).ready(function(){
 		
 		//<button type="button" id="btnInsReply">댓글쓰기</button>
		  $("#btnInsReply").click(function(){
			  if(sessionInfo!=""){
				  writeValid();//필수입력체크
			  }
		  });
		  
		  let noVal =${freeboardData.freeboard.fNo}; 
			let pageNoVal=${pageNo}; 		
		
			//<button type="button" id="btnReplyUpdate${reply.no}" class="btnReplyU">댓글수정</button>
	 		//숨겨졌던  댓글수정폼을 보여주기
	 		$(".btnReplyU").click(function(){
	 			let rNoVal=$(this).attr("param");//댓글번호
	 			$("#frmModiReply"+rNoVal).show();
	 		});
	 		
	 		//댓글 수정폼 다시 숨기기
	 		$(".btnReplyNU").click(function(){
	 			let rNoVal=$(this).attr("param");//댓글번호
	 			$("#frmModiReply"+rNoVal).hide();
	 		});
	 		//댓글 삭제
	 		$(".btnReplyD").click(function(){
	 				let rNoVal = $(this).attr("param");
	 				let queryS = {rNo:rNoVal,no:noVal,pageNo:pageNoVal};	
	 				console.log(queryS);
	 				$.ajax({
	 					type:"POST",//요청방식.
	 					url:"<%=request.getContextPath()%>/fReplyDelete.do", //요청url.
	 					data:queryS,//서버로 전송할 데이터.
	 					success:function(data){
	 						location.href="<%=request.getContextPath()%>/fRead.do?no=${freeboardData.freeboard.fNo}&pageNo=${pageNo}";
	 					}//정상응답후 호출되는 함수
	 				});//aJax끝
	 			 }); //댓글삭제버튼 클릭시 호출함수끝
	 		
	 		//<button type="button" id="btnModiReply${reply.no}" class="btnModiR">댓글수정하기</button>
	 		 //댓글수정처리
	 			 $(".btnModiR").click(function(){
	 				let rNoVal = $(this).attr("param");
	 				let queryS = {rNo:rNoVal, rContent:$("#rContent"+rNoVal).val(),
	 											no:noVal,pageNo:pageNoVal};
	 				if($("#rContent"+rNoVal).val()==""){
	 					alert("수정할 내용을 입력하세요");
	 					$("#rContent"+rNoVal).focus();
	 					return false
	 				}
	 				console.log(queryS);
	 				$.ajax({
	 					type:"POST",//요청방식.
	 					url:"<%=request.getContextPath()%>/fReplyModify.do", //요청url.
	 					data:queryS,//서버로 전송할 데이터.
	 					success:function(data){
	 						location.href="<%=request.getContextPath()%>/fRead.do?no=${freeboardData.freeboard.fNo}&pageNo=${pageNo}";
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
 <%-- BoardDetailHandler에 의해서 아래와 같이 model받음
  	  request.setAttribute("pageNo",pageNo);//보고싶은페이지
  	  request.setAttribute("reboard",reboard);//원글상세
		  request.setAttribute("replyList",replyList);//댓글목록--%>
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
<br/><br/><br/><br/><br/>
 <div class="container">
 <h3>글상세보기</h3>
  	<form name="FreeViewForm" id="FreeViewForm">
 	<div>
 	<br>
 	<h3 class="bbsTitle">${freeboardData.freeboard.fTitle}</h3>
    <table class="bbsView" >
     	<colgroup>
			<col style="width: 15%;" data-view="th">
			<col style="width: 35%;" data-view="td">
			<col style="width: 15%;" data-view="th">
			<col style="width: 35%;" data-view="td">
		</colgroup>
  <tbody>
    	<tr>
    		<th scope="row">번호</th>
    		<td>${freeboardData.freeboard.fNo}</td>
    	</tr>
    	<tr>
    		<th scope="row">작성자명</th>
    		<td>${freeboardData.freeboard.fWriter.writerName}</td>
    	</tr>
    	<tr>
    		<th scope="row" data-view="date">작성일</th>
    		<td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${freeboardData.freeboard.regDate}"/></td>
    	</tr>
    	<tr>
    		<td class="conts" colspan="4">
    		<div class="conts"> 
    		${freeboardData.freeboard.fContent}
    		</div>
    		</td>
    	</tr>
  </tbody>
 </table>
 </div>
 <div class="btn">
		<div class="btn_area">
 <c:set var="pgNo" value="${ (empty param.pageNo) ? '1' : param.pageNo }"></c:set>
 		<input type="button" value="목록" class="readpgbtn" onclick="location.href='<%=request.getContextPath()%>/fList.do?rowSize=${rowSize}'"/>
    	<input type="button" value="수정" class="wud_btn" onclick="location.href='<%=request.getContextPath()%>/fUpdate.do?no=${freeboardData.freeboard.fNo}&pageNo=${pgNo}&rowSize=${rowSize}'" />
    	<input type="button" value="삭제" class="wud_btn" onclick="location.href='<%=request.getContextPath()%>/fDelete.do?no=${freeboardData.freeboard.fNo}'"/>
	    </div> 
	    </div>
	</form>
	</div>	    
 <br/><br/><br/>
 <%-- 댓글목록출력 -------------------------- --%>
 <%-- c:if이용 댓글이 없는 경우 --%>
 <c:if test="${empty freplyList}">
 <table border="1">
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
 <c:if test="${not empty freplyList}">
  <c:forEach var="freply" items="${freplyList}">
  <table class="table table table-hover" style="border: 1px solid;">
  <thead>
    <tr>
      <th scope="col">댓글번호</th>
      <th scope="col">내용</th>
      <th scope="col">작성자ID</th>
      <th scope="col">작성일</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">${freply.rNo}</th>
      <td>${freply.rContent}</td>
      <td>${freply.writerId}</td>
      <td><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${freply.regeDate}"/></td>
    </tr>
      </tbody>
</table>
	   <tr>
	    <td colspan="4" class="center">
	    <c:if test="${ authUser != null && authUser.mId == freply.writerId }">
	      <button type="button" id="btnReplyUpdate${freply.rNo}" class="btn btn-primary btn-sm btnReplyU" param="${freply.rNo}">댓글수정</button></div> 
	      <button type="button" id="btnReplyNotUpdate${freply.rNo}" class="btn btn-secondary btn-sm btnReplyNU" param="${freply.rNo}">댓글수정취소</button> 
	      <button type="button" id="btnReplyDelete${freply.rNo}" class="btn btn-warning btn-sm btnReplyD" param="${freply.rNo}">댓글삭제</button> 
      </c:if>
	    </td>
	   </tr>
	  </tbody>
	 
	 <%-- 댓글 수정 폼 ---------------------------- --%>
	 <form name="frmModiReply" id="frmModiReply${freply.rNo}" 
	       action="<%=request.getContextPath()%>/fReplyModify.do" 
	       method="post" style="display:none;">
	  <input type="hidden" name="pageNo" id="pageNo${freply.rNo}" value="${pageNo}"/>
	  <input type="hidden" name="fNo"  id="fNo${freply.rNo}"  value="${freeboardData.freeboard.fNo}"/>
	  <input type="hidden" name="rNo" id="rNo${freply.rNo}" value="${freply.rNo}"/> 
	 <table border="1">
	  <tbody>
	   <tr>
	    <th>댓글내용</th>
	    <td colspan="3"><textarea name="rContent" id="rContent${freply.rNo}" cols="50" rows="3"  onclick="loginChk()"  placeholder="자유롭게 의견을 표현해주세요.&#10;내용에 따라 이용약관 및 관련 법률에 의해 임의 조치를 수행할 수 있습니다.">${reply.content}</textarea></td>
	   </tr>
	    <td colspan="4" class="center">
	      <button type="button" id="btnModiReply${freply.rNo}" class="btn btn-primary btn-sm btnModiR" param="${freply.rNo}">댓글수정완료</button>
	    </td>
	  </tbody>
	 </table> <%-- 댓글수정table끝 --%>
	 </form>
	 
	 
  </c:forEach>
 </c:if>
 <br/><br/><br/>
 
 <%-- 댓글등록 ----------------------------- --%>
  <form name="frmInsReply" id="frmInsReply" 
       action="<%=request.getContextPath()%>/fReply.do" method="post">
  <input type="hidden" name="pageNo" id="pageNo" value="${pageNo}" />
  <input type="hidden" name="fNo"  id="fNo"  value="${freeboardData.freeboard.fNo}"/>
  <input type="hidden" name="no"  id="no"  value="${freeboardData.freeboard.fNo}"/>
  <input type="hidden" name="writer" id="writer" value="${sessionScope.authUser.mId}"/> 
  <div class="container mt-3">
  <h5>댓글</h5>
    <div class="form-floating mb-3 mt-3">
      <textarea class="form-control" id="rContent" name="rContent" placeholder="자유롭게 의견을 표현해주세요.&#10;단,내용에 따라 이용약관 및 관련 법률에 의해 임의 조치를 수행할 수 있습니다."></textarea>
      <label for="comment">자유롭게 의견을 표현해주세요.</label>
    </div>
    <button type="button" class="btn btn-primary" id="btnInsReply">댓글쓰기</button>
</div>
 </form>
 <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> 
</body>
</html>



