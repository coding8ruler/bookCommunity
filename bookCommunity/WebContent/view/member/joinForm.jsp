<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- Stylesheets ============================================= -->
	<link href="http://fonts.googleapis.com/css?family=PT+Sans:300,400,500,600,700" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>" type="text/css" />
	<link rel="stylesheet" href="<c:url value='/css/style.css'/>"type="text/css" />
<style>
 #memberFrm td, #memberFrm th{
 	border: 1px solid #ddd;
 	padding: 5px;
 }
 #memberFrm th{
  text-align:right;
  background-color:#555;
  color:white;
 }
 .c1 {width:120px;}
 /*에러 안내 글자색 설정*/
 .error1{color:red; font-size:10px;}

</style>
<script>
$(document).ready(function(){
	
//이메일 선택반영
$("#email_dd").change(function(){
	let val = $("select#email_dd option:selected").val();
	$("#email_d").val(val);
});

	
}); //jQuery끝
</script>
</head>
<body>
<%-- 컨트롤러에서 아래와 같이 model을 받음
	request.setAttribute("errors", errors);
						  errors.put("duplicateId", Boolean.TRUE); --%>
<a href="<%=request.getContextPath()%>/index.do">HOME</a>
						  <br/>
<center><font size='3'><b> 회원 가입 </b></font> 
<div class="fancy-title title-dotted-border" style="padding-bottom:30px;"><h3>Sign Up</h3></div>
 <form name="joinFrm" id="joinFrm" method="post" action="<%=request.getContextPath() %>/join.do">
 						<div class="row" style="margin-bottom:5px;">
						<div class="col-md-6" style="margin-left:25%;">
							<label for="template-contactform-name" style="margin-right:5px;">아이디</label>
							<input type="text" class="well well-sm" name="mId" id="mId" size="85" placeholder="ID" maxlength="12" autofocus></div>
						<font id="OK" size="1" color="red"></font></div>
						 <span class="error1">
					 	   <c:if test="${errors.mId}">ID를 입력하세요.</c:if>
					 	   <c:if test="${errors.duplicateId}">이미 사용중인 ID입니다.</c:if>
					 	  </span>
					 	  
					 	  <div class="row" style="margin-bottom:5px;">
						<div class="col-md-6" style="margin-left:24.6%;">
							<label for="template-contactform-name" style="margin-right:5px;">비밀번호</label>
							<input type="password" class="well well-sm" id="mPwd" name="mPwd" size="85" placeholder="Password" required="required" maxlength="15"></div>
							</div>
					 	   <span class="error1"><c:if test="${errors.mPwd}">비밀번호를 입력하세요.</c:if></span>
					 	   
					 	  <div class="row" style="margin-bottom:5px;">
						<div class="col-md-6" style="margin-left:23.8%;">
							<label for="template-contactform-name" style="margin-right:5px;">비밀번호 확인</label>
							<input type="password" class="well well-sm" name="re_mPwd" id="re_mPwd" size="85" placeholder="Password Check" required="required" maxlength="15"></div>
						 <span class="error1"><c:if test="${errors.re_mPwd}">재확인용 비밀번호를 입력하세요.</c:if><c:if test="${errors.notMatch}">비밀번호가 일치하지 않습니다.</c:if></span>
							</div>
					<div style="padding-left: 250px; padding-right: 250px;">
					<div class="row">
						<div class="col-md-6">
							<label for="template-contactform-name">우편번호
								<button type="button"  class="btn btn-link" onclick="execDaumPostcode()"> 우편번호 찾기</button>
							</label><br>
							<input type="text" class="well well-sm" name="postcode" id="postcode" size="35" placeholder="ex) 19xxx"  readonly="readonly">
						</div>
						<input type="hidden" name="postcode" id="roadAddress" placeholder="도로명주소">
						<div class="col-md-6">
							<label for="template-contactform-name">기본주소</label><br>
							 <input type="text" class="well well-sm" name="jibunAddress" id="jibunAddress" size="35" placeholder="주소"  required="required">
						</div>
					</div>
					<span id="guide" style="color:#999;display:none"></span>
					
					<div class="row">
					<div class="col-md-6">
							<label for="template-contactform-name">동,면,읍</label><br>
							 <input type="text" class="well well-sm"  id="extraAddress" name="extraAddress" size="35" placeholder="동,면,읍"  required="required">
						</div>
						
						<div class="col-md-6">
							<label for="template-contactform-name">상세주소</label><br>
							<input type="text" class="well well-sm" name="detailAddress" id="detailAddress" size="35" placeholder="상세주소" required="required">
						</div>
						
					</div>
					
					<div class="row">
						<div class="col-md-6">
							<label for="template-contactform-name">성함</label><br>
							<input type="text" class="well well-sm"  name="mName" id="mName" size="35" placeholder="Name" required="required">
							<span class="error1"><c:if test="${errors.mName}">이름을 입력하세요.</c:if></span>
						</div>

						<div class="col-md-6">
							<label for="template-contactform-name">성별</label><br>
							 <input type="radio" class="well well-sm" name="gender" id="gender0" value="1" required="required"><label for="gender1">여성</label>
							 <input type="radio" class="well well-sm" name="gender" id="gender1" value="0" required="required"><label for="gender0">남성</label>
							 <span class="error1"><c:if test="${errors.gender}">성별을 선택하세요.</c:if></span>
						</div>  	
					</div>
					</div>
					
					<div class="col_full">
							<label for="template-contactform-name">E-mail</label>
							<br>
							<div style="padding-left:95px;">
							<input class="well well-sm" type="text" name="email_id" id="email_id" size="50"  placeholder="Email" required="required">
							@
							<input class="well well-sm" type="text" name="email_d" id="email_d" size="50"  placeholder="Email" required="required">

							<select name="email_dd" id="email_dd">
				 				<option value="">직접입력</option>  
				 				<option value="daum.net">daum.net</option>
				 				<option value="gmail.com">gmail.com</option>
				 				<option value="nate.com">nate.com</option>
				 				<option value="naver.com">naver.com</option>
				 			 </select>

				 		 	</div>
					 </div>	
	
                        <div class="clearfix" style=" text-align:center;">
                                <input type="submit" class="button button-medium button-reveal button-3d button-rounded tright nomargin" style="color:black;" value="회원가입"></div>
</form>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
</body>
</html>




