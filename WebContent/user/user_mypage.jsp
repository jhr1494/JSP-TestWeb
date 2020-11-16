<%@page import="com.testweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<%@include file="../include/header.jsp" %>
<%
	if(session.getAttribute("user") == null){
		response.sendRedirect("login.user");
	}


%>
    
<section>
        <div class="container">
            <div class="row join-wrap">
                
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER                   
                    </div>
                    <div>
                        <p>${sessionScope.user.id }(${sessionScope.user.name })님 회원정보</p>
                    </div>
                   
                  
                  
                    <div>
                        <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath }/user/update.user'">회원정보변경</button>
                        <button type="button" class="btn btn-primary" id="delCheck" onclick="delete-hidden">회원 탈퇴</button>
                        
                    	${msg }
                    </div>
                    <div class="delete-hidden">
                        <form action="delete.user" method="post" name="deleteForm">
                        <input type="password"  name="pw" class="form-control" placeholder="비밀번호를 입력하세요">
                        <button type="button" class="btn btn-primary" onclick="submit()">확인</button>
                        </form>
                    </div>
                    

                    <br>
                    <div>
                        <p>${sessionScope.user.id }님의 작성 게시물</p>
                        <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="text-align: center;">번호</th>
                            <th style="text-align: center;">제목</th>
                            <th style="text-align: center;">작성자</th>
                            <th style="text-align: center;">작성일</th>
                        </tr>
                    </thead>
                    
                    <!-- 내가쓴글 리스트 업로드 -->
                    
                    <tbody>
                    
                    <c:forEach var="userList" items="${userList }">
                        <tr>
                            <td>${userList.bno }</td>
                            <td><a href="${pageContext.request.contextPath }/board/content.board?bno=${userList.bno} ">${userList.title }</a></td>
                            <td>${userList.writer }</td>
                            <td><fmt:formatDate value="${userList.ragdate }" pattern="yyyy-MM-dd HH:mm"/></td>
                        </tr>
                        
                        
                	</c:forEach>
                    </tbody>
                    
                </table>
                
                <div class="text-center">
                <ul class="pagination pagination-sm">
                    	<c:if test="${uPVO.prev} ">
                        	<li><a href="userList.board?pNum=${uPVO.start-1 }&amount=${uPVO.amount}">이전</a></li>
                        </c:if>
                        
                        
                        <c:forEach var="num" begin="${uPVO.start }" end="${uPVO.end }">
                        
                        <li class="${num eq uPVO.pNum ? 'active' : '' }">
                        <a href="userList.board?pNum=${num }&amount=${uPVO.amount }">${num }</a></li>
                        
                        </c:forEach>
                        
                        <c:if test="${uPVO.next}">
                        <li><a href="userList.board?pNum=${uPVO.start+1 }&amount=${uPVO.amount}">다음</a></li>
                    	</c:if>
                    
                    </ul>
	                
                    </div>
                    </div>
                    
                    
                </div>


            </div>

        </div>

	<script>
	function submit() {
		document.deleteForm.pw.value;
	}
	</script>
	
	<script>
        //탈퇴버튼 디스플레이 처리
        var delCheck = document.getElementById("delCheck");
        delCheck.onclick = function() {
            var del  = document.querySelector(".delete-hidden");
            if(del.style.display == "none" || del.style.display == "") {
                del.style.display = "inline";
            } else {
                del.style.display = "none";
            }
        }
    </script>

    </section>
    
    
    

    
    
<%@include file="../include/footer.jsp" %>
    
    
    