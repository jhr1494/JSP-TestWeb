<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <!--회원가입 폼만 적용되는 css-->
    <style type="text/css">
        .table-striped>tbody>tr {
            background-color: #f9f9f9
        }

        .join-form {
            margin: 0 auto;
            padding: 20px;
            width: 50%;
            float: none;
            background-color: white;
        }
        
        
        .form-group > .sel {
            width: 120px;
            display: inline-block;
            
        }
    </style>
    
    
<%@include file="../include/header.jsp" %>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-9 col-sm-12 join-form">
                    <h2>회원가입<small>(가운데정렬)</small></h2>

                    <form action="joinForm.user" method="post" name="userJoin">
                    
                        <div class="form-group">
                            <label for="id">아이디</label>
                            <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 (영문포함 4~12자 이상)">
                        </div>
                        <div class="form-group">
                            <label for="password">비밀번호</label>
                            <input type="password" class="form-control" id="password" name="pw" placeholder="비밀번호 (영 대/소문자, 숫자, 특수문자 3종류 이상 조합 8자 이상)">
                        </div>
                        <div class="form-group">
                            <label for="password-confrim">비밀번호 확인</label>
                            <input type="password" class="form-control" id="password-confrim" name="pwCheck" placeholder="비밀번호를 확인해주세요.">
                        </div>
                        <div class="form-group">
                            <label for="name">이름</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요.">
                        </div>
                        <!--input2탭의 input-addon을 가져온다 -->
                        <div class="form-group">
                            <label for="hp">휴대폰번호</label><br>
                            
                            <input class="form-control sel" name="hp1" placeholder="010"> -
                            <input class="form-control sel" name="hp2" placeholder="xxxx"> -
                            <input class="form-control sel" name="hp3" placeholder="xxxx">
                        
                        </div>
                        <div class="form-group">
                             <label for="email">이메일</label><br>
                            <input class="form-control sel" name="email1">@
                            <select class="form-control sel" name="email2">
                                <option>naver.com</option>
                                <option>gmail.com</option>
                                <option>daum.net</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="addr-num">주소</label>
                            <input type="text" class="form-control" id="addr-basic" name="add_B" placeholder="기본주소">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="addr-detail" name="add_D" placeholder="상세주소">
                        </div>
                        
                        
                        <p>${msg }</p>
                        <div class="form-group">
                            <button type="button" class="btn btn-lg btn-success btn-block" onclick="check()">회원가입</button>
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-lg btn-info btn-block" onclick=" location.href='${pageContext.request.contextPath }/user/login.user' ">로그인</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </section>
    
<script>
	function check() {
		
		var idType = /^[A-Za-z0-9]{4,12}$/;
		var pwType = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@!%*#?&])[A-Za-z\d$@$!%*#?&]{8,15}$/; //패스워드 자리수 8자 이상 --- 영대/소문자, 숫자, 특수문자 3가지 이상 섞어서
		var hpType = /^\d{3}-\d{3,4}-\d{4}$/; //휴대폰번호 -- 숫자만 3, 4
		
		var id = document.userJoin.id.value;
		var pw = document.userJoin.pw.value;
		var hp = document.userJoin.hp1.value + "-" + document.userJoin.hp2.value + "-" + document.userJoin.hp3.value;
		
		if (!idType.test(id)) {
			alert('ID는 영문,숫자를 포함한 4자~12자로 입력해주세요');
			return;

		}else if (!pwType.test(pw)) {
			alert('PW는 영문,숫자,특수문자를 포함한 8자이상으로 입력하세요');
			return;
			
		} else if (pw != document.userJoin.pwCheck.value) {
			alert('비밀번호가 일치하지 않습니다');
			return;

		} else if (document.userJoin.name.value == '') {
			alert('이름은 필수 입력입니다');
			return;

		} else if (!hpType.test(hp)) {
			alert('휴대폰번호는 숫자만 가능합니다');
			return;
		}else
			document.userJoin.submit();
		
	}

</script>



<%@include file="../include/footer.jsp" %>