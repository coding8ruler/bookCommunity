<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="description" content="member board Web Application">
	<meta name="keywords" content="member, board, article, mvc">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>게시판 목록</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mP.css" />
	<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script> 
//스크롤 따라다니는 메뉴박스 만들기
$(document).ready(function(){
$(window).scroll(function(){  //스크롤이 움직일때마다 이벤트 발생
      var position = $(window).scrollTop()+200; // 현재 스크롤바의 위치값을 반환
      $("#Quick").stop().animate({top:position+"px"}, 400); //해당 오브젝트 위치값 재설정
   });

});
</script> 
	
	<style>
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
	.fileButton {
	  padding: 4px 25px;
	  background-color:#00a2ff;
	  border-radius: 4px;
	  color: white;
	  cursor: pointer;
	}
	#btnDelete, #btnList, #btnUpdate {
	  width: 80px;
	  height: 30px; 	
	}
	#homeDiv {
	  text-align: center;	
	} 
	</style>
	
	<script type="text/javascript">
	$(document).ready(function () {
		//게시물출력수 선택
		//<select name="rowSize" id="rowSize">에 change 이벤트
		$("#rowSize").change(function(){

		   $("#rowSizeFrm").submit();
		});
		
		
		$("#btnList").click(function() {

			location.href="<%=request.getContextPath()%>/recomboardListboard.do?pageNo=${pageNo}&rowSize=${rowSize}";
		});
		
		$("#btnDelete").click(function(no) {

			if(confirm("삭제하시겠습니까?")) {
				location.href = "<%=request.getContextPath()%>/recomboardDelete.do?no=${boardData.recomBoard.rNo}";
			}
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
<br/><br/><br/>
	<%-- UpdateBoardController에 의해 아래와 같이 Model 받는다
			request.setAttribute("boardData", boardData);
			request.setAttribute("no", no);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("rowSize", rowSize);
	
		*내용 : ${boardData} <br/><br/>
		*세션 : ${authUser} <br/>
		*글번호no : ${no} <br/>
		*보고싶은 페이지 pageNo : ${pageNo} <br/>
		*페이지당 글개수 rowSize : ${rowSize} <br/>
	--%>
	
	<h2 align="center">추천 게시판 수정</h2>
    <br/><br/>
    
    <form name="updateFrm" id="updateFrm" method="post" action="<%=request.getContextPath()%>/recomboardUpdate.do" enctype="multipart/form-data">

    	<input type="hidden" name="rNo" id="rNo" value="${boardData.recomBoard.rNo}"/><br/>
	    <input type="hidden" name="hideFileName" id="hideFileName" value="${boardData.recomFile.fileRealName}"/>
	    
	    <table border="1" style="width: 900px; margin-left: auto; margin-right: auto;">
	    	<tr>
	    		<th>작성자 아이디</th>
	    		<td>${boardData.recomBoard.member.mId}</td>
	    	</tr>
	    	<tr>
	    		<th>게시판 제목</th>
	    		<td>
	    			<input type="text" name="rtitle" id="rtitle" value="${boardData.recomBoard.rTitle}" required="required" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>책제목</th>
	    		<td>
	    			<input type="text" name="bookTitle" id="bookTitle" value="${boardData.recomBoard.bookTitle}" required="required" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>저자</th>
	    		<td>
	    			<input type="text" name="author" id="author" value="${boardData.recomBoard.author}" required="required" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>출판사</th>
	    		<td>
	    			<input type="text" name="publisher" id="publisher" value="${boardData.recomBoard.publisher}" required="required" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>책 이미지</th>
	    		<td align="left" valign="middle">
	    			<img src="../uploadImage/${boardData.recomFile.fileRealName}" style="height:300px; width:600px;"> <br/>
	    			<input type="file" name="filename" id="filename" style="display: none" />
	    			<label for="filename" class="fileButton">업로드</label>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>게시판 내용</th>
	    		<td>
	    			<textarea name="rContent" id="rContent" style="width: 600px; height: 200px" required="required">${boardData.recomBoard.rContent}</textarea>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>좋아요</th>
	    		<td>
	    			<input type="text" name="likeIt" id="likeIt" value="${boardData.recomBoard.likeIt}" readonly="readonly" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>조회수</th>
	    		<td>
	    			<input type="text" name="rCnt" id="rCnt" value="${boardData.recomBoard.rCnt}" readonly="readonly" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>작성일</th>
	    		<td>
	    			<input type="text" name="regDate" id="regDate" value="<fmt:formatDate pattern="yyyy년 MM월 dd일 HH시:mm분:ss초" value="${boardData.recomBoard.regDate}"/>" readonly="readonly" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>수정일</th>
	    		<td>
	    			<input type="text" name="modDate" id="modDate" value="<fmt:formatDate pattern="yyyy년 MM월 dd일 HH시:mm분:ss초" value="${boardData.recomBoard.modDate}"/>" readonly="readonly" />
	    		</td>
	    	</tr>
	    	<tr>          
		 		<td colspan="2" style="text-align:center;">
					<input type="submit" value="수정 진행" id="btnUpdate"/>
					
					<c:set var="pageNo" value="${ (empty param.pageNo) ? '1' : param.pageNo }"></c:set>
	  				<input type="button" value="목록 이동" id="btnList">
	  				<input type="button" value="삭제하기" id="btnDelete">
		 		</td>
		 	</tr>
	    </table>
	</form>
	
</body>
</html>