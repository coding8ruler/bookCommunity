<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mainPage.css" />
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
	  font: 14px/24px "Open Sans", "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", Sans-Serif;
	}
	table {
	  border-collapse: separate;
	  border-spacing: 0;
	  width: 100%;
	  margin: auto;
	}
	th,	td {
	  padding: 6px 15px;
	}
	th {
	  background: #42444e;
	  color: #fff;
	  text-align: center;
	}
	tr:first-child th:first-child {
	  border-top-left-radius: 6px;
	}
	tr:first-child th:last-child {
	  border-top-right-radius: 6px;
	}
	td {
	  border-right: 1px solid #c6c9cc;
	  border-bottom: 1px solid #c6c9cc;
	}
	td:first-child {
	  border-left: 1px solid #c6c9cc;
	}
	tr:nth-child(even) td {
	  background: #eaeaed;
	}
	tr:last-child td:first-child {
	  border-bottom-left-radius: 6px;
	}
	tr:last-child td:last-child {
	  border-bottom-right-radius: 6px;
	}
	#formDiv, #tableDiv, #homeDiv {
		text-align: center;
	}
	</style>
</head>
 <body>
    <div id="wrapper">
      <!-- 헤드시작 -->
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
      <!--헤드 끝-->
<div id="Quick" class="" style="position: absolute; right: 10px; top: 400px;">
    <table class="quickMenuBar" style="">
        <tr>
            <td colspan="2" style="cursor:pointer; background-color:white;" onclick="location.href='<%=request.getContextPath()%>/index.do'">HOME</td>
        </tr>
        <tr>
            <td colspan="2" style="cursor:pointer;" onclick="window.scrollTo(0,0);">TOP</td>
        </tr>
    </table>
</div>
       <!-- 바디 시작 -->
      <div id="main">
        <div id="mainbanner01" class="mainbanner01"> <!--상단 배너-->
          <div id="mm">
              <div class="container" id="container">
                  <div class="inner" id="slide-1">
                      <div>
                          <img src="/images/m01.jpg">
                          <img src="/images/book-shadow.png" class="shoadow">
                            <div class="title">
                                <a href="#">당신의 세계는 아직도 바다와빗소리와<br>작약을 취급하는지</a>
                                
                            </div>
                            <div class="info">
                                <span class="role">글</span>
                                <a href="http://minumsa.com/blog/36706/">김경미 </a>
                                <span class="sep">|</span>
                                <strong>출판사</strong>
                                <a href="http://minumsa.minumsa.com">민음사</a>
                            </div>
                            <div class="copy">
                                삶의 오욕들을 슬픈 웃음과 유쾌한 외로움으로 전복하는<br> 반어와  역설의 장인자서(自序) 한겨울밤 갑작스런 폭우에 <br>온통 젖은 채 물과 어둠을뚝뚝 떨어뜨리면서…
                            </div>
                      </div>
                  </div>
                  <div class="inner">
                      <div>
                          <img src="/images/m02.jpg">
                          <img src="/images/book-shadow.png" class="shoadow">
                            <div class="title"><a href="http://minumsa.minumsa.com/book/22969/">명상록</a></div>
                            <div class="info">
                              <span class="role">글</span> 
                              <a href="http://minumsa.com/blog/36704/">아우렐리우스</a> 
                              <span class="sep">|</span> <span class="role">옮김</span>
                              <a href="http://minumsa.com/blog/27180/">김동훈</a> 
                              <span class="sep">|</span> <strong>출판사</strong> 
                              <a href="http://minumsa.minumsa.com">민음사</a></div>
                            <div class="copy">고통, 질병, 분노, 상실에서 회복하는 힘에 대하여</div>
                      </div>
                  </div>
                  <div class="inner">
                      <div><img src="/images/m03.jpg">
                        <img src="/images/book-shadow.png" class="shoadow">
                        <div class="title"><a href="http://minumsa.minumsa.com/book/22962/">아이스</a></div>
                        <div class="info">
                          <span class="role">글</span>
                          <a href="http://minumsa.com/blog/36635/">애나 캐번</a> <span class="sep">|</span>
                          <span class="role">옮김</span> <a href="http://minumsa.com/blog/27045/">박소현</a>
                          <span class="sep">|</span> <strong>출판사</strong> <a href="http://minumsa.minumsa.com">민음사</a></div>
                        <div class="copy">무라카미 하루키, 폴 오스터가 선보인 슬립스트림 문학의 절정을<br> 성취한 작가 애나 캐번의 최고 걸작 어슐러 르 귄, 커트 보니것,<br> J. G. 밸러드,…</div>
                      </div>
                  </div>
                  <div class="inner">
                      <div><img src="/images/m04.jpg">
                        <img src="/images/book-shadow.png" class="shoadow">
                        <div class="title"><a href="http://minumsa.minumsa.com/book/22958/">영원의 기획</a></div>
                        <div class="info">
                          <span class="role">글</span> <a href="http://minumsa.com/blog/31714/">정은경</a>
                          <span class="sep">|</span> <strong>출판사</strong> <a href="http://minumsa.minumsa.com">민음사</a></div>
                      </div>
                      <div class="copy">한 사람의 내면에서 무수한 타자를, 현재의 시공간으로부터<br> 거대한 바깥을, 필멸에서 영원을 길어 올리는 ‘문학’이라는 현장</div>
                  </div>
                  <div class="inner">
                      <div><img src="/images/m05.jpg">
                        <img src="/images/book-shadow.png" class="shoadow">
                        <div class="title"><a href="http://minumsa.minumsa.com/book/22931/">한편 10호 대학</a></div>
                        <div class="info"><span class="role">글</span> <a href="http://minumsa.com/blog/36663/">난다</a>, <a href="http://minumsa.com/blog/36665/">김종은</a>, <a href="http://minumsa.com/blog/36667/">신하영</a>, <a href="http://minumsa.com/blog/36669/">우재형</a>, <a href="http://minumsa.com/blog/36671/">신현아</a>, <a href="http://minumsa.com/blog/36673/">유상운</a>, <a href="http://minumsa.com/blog/36675/">소진형</a>,<br> <a href="http://minumsa.com/blog/36677/">황민호</a>, <a href="http://minumsa.com/blog/36679/">현수진</a>, <a href="http://minumsa.com/blog/36681/">유리관</a> <span class="sep">|</span> <strong>출판사</strong> <a href="http://minumsa.minumsa.com">민음사</a></div>
                        <div class="copy">창간 3주년 특집 《한편》 10호 ‘대학’ 출간 대학 안 가도 잘<br> 살 수있다는 구호, 고등학교 열에 일곱이 대학을 가는 현실. 지금…</div>
                      </div>
                  </div>
                  <div class="inner">
                      <div><img src="/images/m06.jpg">
                        <img src="/images/book-shadow.png" class="shoadow">
                        <div class="title"><a href="http://minumsa.minumsa.com/book/22929/">가만한 지옥에서 산다는 것</a></div>
                        <div class="info"><span class="role">글</span> <a href="http://minumsa.com/blog/36655/">김남숙</a> <span class="sep">|</span> <strong>출판사</strong> <a href="http://minumsa.minumsa.com">민음사</a></div>
                        <div class="copy">“내 생각과 이야기를 드러내고 싶고, 그것이 읽혔으면 좋겠다고<br>생각하며 썼지만, 어떤 날은 내가 그런 글을 썼다는 사실이 <br>무서워진다.” 무엇을 쓰고 싶은가? 잘…</div>
                      </div>
                  </div>
                  <div class="inner">
                      <div><img src="/images/m07.jpg">
                        <img src="/images/book-shadow.png" class="shoadow">
                        <div class="title"><a href="http://minumsa.minumsa.com/book/22926/">시대의 조정자</a></div>
                        <div class="info"><span class="role">글</span> <a href="http://minumsa.com/blog/9244/">남재희</a> <span class="sep">|</span> <strong>출판사</strong> <a href="http://minumsa.minumsa.com">민음사</a></div>
                        <div class="copy">신생 독립국의 엘리트 청년에서 언론사 주필, 4선 국회의원, <br>노동부 장관으로 남재희, 그리고 그가 만난 대한민국을 만든<br> 사람들의 이야기 서울대에 두 번 입학한…</div>
                      </div>
                  </div>
                  <div class="inner">
                      <div><img src="/images/m08.jpg">
                        <img src="/images/book-shadow.png" class="shoadow">
                        <div class="title"><a href="http://minumsa.minumsa.com/book/22920/">음식 중독</a></div>
                        <div class="info"><span class="role">글</span> <a href="http://minumsa.com/blog/36633/">마이클 모스</a> <span class="sep">|</span> <span class="role">옮김</span> <a href="http://minumsa.com/blog/35010/">연아람</a> <span class="sep">|</span> <strong>출판사</strong> <a href="http://minumsa.minumsa.com">민음사</a></div>
                        <div class="copy">“당신은 지금 먹는 것에 중독돼 있다!” 음식과 자유의지, 그 틈을<br>파고들어 거대 식품 기업들이 중독을 이용하는 법 ★ <br>《뉴욕 타임스》, 아마존 베스트셀러…</div>
                      </div>
                  </div>
                  <div class="inner">
                      <div><img src="/images/m09.jpg">
                        <img src="/images/book-shadow.png" class="shoadow">
                        <div class="title"><a href="http://minumsa.minumsa.com/book/22916/">빛의 체인</a></div>
                        <div class="info"><span class="role">글</span> <a href="http://minumsa.com/blog/36631/">전수오</a> <span class="sep">|</span> <strong>출판사</strong> <a href="http://minumsa.minumsa.com">민음사</a></div>
                        <div class="copy">빛처럼 만물에 스미어 설계자와 피조물, 현실과 가상을 꿰어<br> 나가는 영혼의 궤적</div>
                      </div>
                  </div>
                  <div class="inner">
                      <div><img src="/images/m10.jpg">
                        <img src="/images/book-shadow.png" class="shoadow">
                        <div class="title"><a href="http://minumsa.minumsa.com/book/22895/">폐허의 청년들, 존재와 탐색</a></div>
                        <div class="info"> <span class="sep">|</span> <strong>출판사</strong> <a href="http://minumsa.minumsa.com">민음사</a></div>
                      </div>
                      <div class="copy">탄생 100년을 맞는 작가들의 문학적 업적과 생애를 객관적으로<br> 조명하고 정리하여 우리 문학의 진로를 모색한다 1922년에<br>태어나 올해 탄생 100주년을 맞은 문학인들은 김구용,…</div>
                  </div>
              </div>
          </div>
 
          <div id="btn"class="btn">
            <button class="버튼1">1</button>
            <button class="버튼2">2</button>
            <button class="버튼3">3</button>
            <button class="버튼4">4</button>
            <button class="버튼5">5</button>
            <button class="버튼6">6</button>
            <button class="버튼7">7</button>
            <button class="버튼8">8</button>
            <button class="버튼9">9</button>
            <button class="버튼10">10</button>
          </div>
          <script>
    
      //  버튼(2) 누르면
      // transform: translate(-100vw)
      
          document.querySelector('.버튼1'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(0vw)';
          });
          document.querySelector('.버튼2'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(-100vw)';
          });
          document.querySelector('.버튼3'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(-200vw)';
          });
          document.querySelector('.버튼4'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(-300vw)';
          });
          document.querySelector('.버튼5'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(-400vw)';
          });
          document.querySelector('.버튼6'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(-500vw)';
          });
          document.querySelector('.버튼7'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(-600vw)';
          });
          document.querySelector('.버튼8'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(-700vw)';
          });
          document.querySelector('.버튼9'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(-800vw)';
          });
          document.querySelector('.버튼10'). addEventListener('click',function(){
              document.querySelector('.container').style.transform = 'translate(-900vw)';
          });
          </script>
<!-- 스크립트 끝 -->
        </div>
      </div>
<!-- 메인배너1 끝 -->
        <div id="mainbanner02" class="mainbanner02"><!-- 하단 배너 통짜 박스-->
          <img src="" alt="">
            <div id="mainbanner03" class="mainbanner03"><!--하단 믿음북클럽 배너-->
                <a href="http://minumsa.minumsa.com/littor/"><img src="/images/main03.png" alt="메인배너3"></a>
            </div>
            <div id="home-bottom" class="home-bottom"><!--하단 아래 통짜 박스-->
              <div id="home-bottom01">
                <%-- <img src="<%=request.getContextPath()%>/images/home-bottom01.png" alt=""><!--하단 왼쪽 진행중인이벤트 목록--> --%>

									<c:if test="${!empty authUser}">
									<h2 style=" margin:0 auto; align:center;">이벤트 게시판</h2>
									<table border="1" style="width: 800px; text-align:center;">
								    	<thead>
								    		<tr>
								    			<th>글번호</th>
								    			<th>작성자</th>
								    			<th>제목</th>
								    		</tr>
								    	</thead>
								    	<tbody>
									    	<c:if test="${eventPage.hasNoEvents()}">
									    	<tr>
									    		<td colspan="5" style="text-align: center;">게시글이 없습니다</td>
									    	</tr>
									    	</c:if>
											<c:if test="${eventPage.hasEvents()}">
									    	<c:forEach var="event" items="${eventPage.content}">
									    	<tr> 
									   			<td>${event.e_no}</td>
									   			<td>${event.e_writer.e_writername}</a></td>
									   			<td><a href="<%=request.getContextPath()%>/read.do?no=${event.e_no}&pageNo=${eventPage.currentPage}&rowSize=${size}">${event.e_title}</td>
									   		</tr>	
									    	</c:forEach>
											</c:if>
								    	</tbody>
								    </table>
				
          				</c:if>
              </div>
              <!-- <div id="home-bottom02">
                <img src="/images/home-bottom02.png" alt="">하단 오른쪽 블로그 목록 도서추천으로 대체할거임
              </div> -->
            </div>
            <div id="sidebar"><!--아래 우측 배너-->
              <a href="#"><img src="/images/sidebar01.png" alt=""></a>
              <a href="http://minumsa.minumsa.com/bookclub-mobile-notify/"><img src="/images/sidebar02.png" alt=""></a>
              <a href="https://minumsa.minumsa.com/bookclub/community/"><img src="/images/sidebar03.png" alt=""></a>
              <a href="https://minumsa.minumsa.com/bookclub/community/"><img src="/images/sidebar04.png" alt=""></a>
              <a href="https://minumsa.minumsa.com/lc-event/"><img src="/images/sidebar05.png" alt=""></a>
              <a href="https://www.youtube.com/minumsatv"><img src="/images/sidebar06.png" alt=""></a>
              <a href="https://www.instagram.com/minumsa_books/"><img src="/images/sidebar07.png" alt=""></a>
            </div>
        </div>
      </div>
      <!--바디 끝-->
      
      <!-- 푸터 시작 -->
      <footer id="footer" role="contentinfo">
        <div id="footer-inside" class="footer-inside">
          <div class="alignleft">
            <div id="footer-quicklinks">
              <!-- <a href="http://minumsa.com">민음사 출판그룹</a>
              <span class="sep">|</span> -->
              <a href="http://minumsa.com/terms">회원약관</a>
              <span class="sep">|</span>
              <a href="http://minumsa.com/privacy-policy"><b><span style="color:#c00;">개인정보처리방침</span></b></a>
              <span class="sep">|</span>
              <a href="http://minumsa.com/contact">문의</a>
              <span class="sep">|</span>
              <span style="color:#e14b4b;">2차 저작권 문의 가이드 : </span><a href="http://minumsa.com/rights-1"><b>국내 저작물</b></a> / <a href="http://minumsa.com/rights"><b>해외 저작물</b></a>
              <span class="sep">|</span>
              <a href="http://minumsa.com/direction">위치 및 연락처</a>
              <span class="sep">|</span>
              <a href="http://minumsa.com/sitemap">사이트맵</a>
              <span class="sep">|</span>
              <a href="http://minumsa.com/feed">소식 RSS</a>
              <span class="sep">|</span>
              <a href="http://minumsa.com/book-feed?brand_id=2">민음사 도서 RSS</a>
              <span class="sep">|</span>
              <a href="http://minumsa.com/english">ENGLISH</a>
            </div>
            <div id="footer-info">
              <span>(주)민음북스</span>
              <span class="sep">|</span>
              <span>대표이사: 박상준</span>
              <span class="sep">|</span>
              <span>사업자등록번호: 211-88-41803</span>
              <span class="sep">|</span>
              <span>통신판매업 신고번호: 제2010-서울강남-00971호</span>
            </div>
          
            <div id="site-info">
              <span>주소: 서울시 강남구 신사동 506 강남출판문화센터 5층</span>
              <span class="sep">|</span> 
              <span>새주소: 서울시 강남구 도산대로 1길 62 5층</span>
            </div> 
            
            <div id="copy-info">
              <span>전화: 02-515-2000</span>
              <span class="sep">|</span>
              webmaster@minumsa.com
            </div>
          </div>
        </div>
      </footer>
      <!--푸터 끝-->
    </div>
  </body>
</html>