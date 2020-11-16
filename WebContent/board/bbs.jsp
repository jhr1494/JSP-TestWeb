<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file ="../include/header.jsp" %>
        
    <section>
    
        
        <div class="container">
            <div class="row">
            
                <h2>게시판 목록</h2>
			    <div align="right">
					<select onchange="change(this)" >
						<option value="10" ${PageVO.amount == 10? 'selected' : '' }>10개씩 보기</option>
						<option value="20" ${PageVO.amount == 20? 'selected' : '' }>20개씩 보기</option>
						<option value="50" ${PageVO.amount == 50? 'selected' : '' }>50개씩 보기</option>
						<option value="100" ${PageVO.amount == 100? 'selected' : '' }>100개씩 보기</option>
					</select>
				</div>
				
                <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="background-color: #9DCAFF; text-align: center;">번호</th>
                            <th style="background-color: #9DCAFF; text-align: center;">제목</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성자</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성일</th>
                        </tr>
                    </thead>
                    
                    
                    <!-- 리스트 -->
                    <c:forEach var="list" items="${list }">
                    <tbody>
                        <tr>
                            <td>${list.bno }</td>
                            <td><a href="content.board?bno=${list.bno} ">${list.title }</a></td>
                            <td>${list.writer }</td>
                            <td><fmt:formatDate value="${list.ragdate }" pattern="yyyy-MM-dd HH:mm"/></td>
                        </tr>
                        <tr>
                    </tbody>
                    </c:forEach>
                </table>
                
				<!-- 페이징 -->
                <div class="text-center">
                    <ul class="pagination pagination-sm">
                    	<c:if test="${pageVO.prev} ">
                        	<li><a href="list.board?pNum=${pageVO.start-1 }&amount=${pageVO.amount}">이전</a></li>
                        </c:if>
                        
                        
                        <c:forEach var="num" begin="${PageVO.start }" end="${PageVO.end }">
                        
	                        <li class="${num eq PageVO.pNum ? 'active' : '' }">
	                        <a href="list.board?pNum=${num }&amount=${PageVO.amount }">${num }</a></li>
                        
                        </c:forEach>
                        
                        <c:if test="${PageVO.next}">
                        <li><a href="list.board?pNum=${PageVO.start+1 }&amount=${PageVO.amount}">다음</a></li>
                    	</c:if>
                    
                    </ul>
                    <button type="button" class="btn btn-info pull-right" onclick="location.href= 'write.board?id=${sessonScope.user.id}'">글쓰기</button>
                </div>
                
            </div>
        </div>
        
        <script>
		function change(a) {
			location.href = "list.board?pNum=1&amount=" + a.value;
		}

	</script>
        
    </section>
        
<%@ include file ="../include/footer.jsp" %>