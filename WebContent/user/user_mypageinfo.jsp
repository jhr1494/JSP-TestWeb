<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<%
	if(session.getAttribute("user") == null){
		response.sendRedirect("login.user");
	}
%>
    
<section>
        <div class="container">
            <div class="row join-wrap">
                <!--join-form을 적용한다 float해제 margin:0 auto-->
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    
                  <form action="updateForm.user" method="post" name="regForm">
                    <p>*표시는 필수 입력 표시입니다</p>
                    <table class="table" >
                        <tbody class="m-control">
                            <tr>
                                <td class="m-title">*ID</td>
                                <td><input class="form-control input-sm" name="id" value="${sessionScope.user.id }" readonly></td>
                            </tr>
                            <tr>
                                <td class="m-title">*이름</td>
                                <td><input class="form-control input-sm" name="name" value="${sessionScope.user.name }"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호</td>
                                <td><input type="password" class="form-control input-sm" name="pw"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호확인</td>
                                <td><input type="password" class="form-control input-sm" name="pwCheck"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*E-mail</td>
                                <td>
                                    <input class="form-control input-sm" name="email1" >@
                                    <select class="form-control input-sm sel" name="email2">
                                        <option>naver.com</option>
                                        <option>gmail.com</option>
                                        <option>daum.net</option>
                                    </select>
                                    <p>${msg }</p>
                                    <input type="button" class="btn btn-info" value="중복확인" onclick="mailCheck()"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*휴대폰</td>
                                <td>
                                    <input class="form-control input-sm sel" name="hp1"> -
                                    <input class="form-control input-sm sel" name="hp2"> -
                                    <input class="form-control input-sm sel" name="hp3">
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*주소</td>
                                <td><input class="form-control input-sm add" name="add_B" value="${sessionScope.user.address_basic } " ></td>
                            </tr>
                            <tr>
                                <td class="m-title">*상세주소</td>
                                <td><input class="form-control input-sm add" name="add_D" value="${sessionScope.user.address_detaile } "></td>
                            </tr>
                        </tbody>
                    </table>
                    
                    <div class="titlefoot">
                        <input type="button" class="btn" value="수정" onclick="check()"/>
                        <input type="button" class="btn" value="목록" onclick="history.go(-1)"/>
                    </div>
                  </form>
                
                    
                </div>
                


            </div>

        </div>

    </section>
    
    
    
    <script>
	function check() {
	
		var pwType = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@!%*#?&])[A-Za-z\d$@$!%*#?&]{8,15}$/; //패스워드 자리수 8자 이상 --- 영대/소문자, 숫자, 특수문자 3가지 이상 섞어서
		var hpType = /^\d{3}-\d{3,4}-\d{4}$/; //휴대폰번호 -- 숫자만 3, 4
		
		var pw = document.regForm.pw.value;
		var hp = document.regForm.hp1.value +"-"+ document.regForm.hp2.value +"-"+ document.regForm.hp3.value;
		var email = document.regForm.email1.value;
		var add_B = document.regForm.add_B.value;
		var add_D = document.regForm.add_D.value;
		
		if (pw == '' || email == '' || add_B == '' || add_D == '' ) {
			alert('* 은 필수입력사항입니다');
			return;

		}else if (!pwType.test(pw)) {
			alert('PW는 영문,숫자,특수문자를 포함한 8자이상으로 입력하세요');
			return;
			
		} else if (pw != document.regForm.pwCheck.value) {
			alert('비밀번호가 일치하지 않습니다');
			return;

		} else if (!hpType.test(hp)) {
			alert('휴대폰번호를 다시입력해주세요');
			return;
		}else
			document.regForm.submit();
			return;
		}	

</script>
    
    
<%@include file="../include/footer.jsp" %>