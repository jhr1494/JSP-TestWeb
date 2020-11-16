<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!--login만 적용되는 css-->   
    <style>
        .table-striped>tbody>tr {
            background-color: #f9f9f9
        }

        .join-form {
            margin: 100px auto;
            padding: 20px;
            width: 50%;
            float: none;
            background-color: white;
        }
    </style>


<%@include file="../include/header.jsp" %>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-9 col-sm-12 join-form">
                    <h2 align="center">로그인</h2>
                    
                    <p align="center"><span>${msg }</span></p>
                    <form action="loginForm.user" method="post">
                        <div class="form-group">
                            <label for="id">아이디</label>
                            <input type="text" class="form-control" id="id" name="id" placeholder="아이디">
                        </div>
                        <div class="form-group">
                            <label for="password">비밀번호</label>
                            <input type="password" class="form-control" id="password" name="pw" placeholder="비밀번호 ">
                        </div>
                        
                        <div class="form-group">
                            <button type="button" class="btn btn-lg btn-success btn-block" onclick="location.href='${pageContext.request.contextPath }/user/join.user'">회원가입</button>
                        </div>

                        <div class="form-group">
                            <input type="hidden" name="userlist" value="location.href= 'userList.board?id=${id}'">
                            <button type="submit" class="btn btn-lg btn-info btn-block" >로그인</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </section>



<%@include file="../include/footer.jsp" %>