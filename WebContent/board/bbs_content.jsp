<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file ="../include/header.jsp" %> 
    
     <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-10 col-sm-12 join-form">
                    <h2>게시판 상세보기<small>(디자인이궁금하세요?)</small></h2>

                    <form action="modify.board" method="post" >
                        <div class="form-group" align="left">
                            
                        </div>
                        <div class="form-group">
                            <label>등록일</label>
                            <fmt:formatDate value="${vo.ragdate }" pattern="yyyy-MM-dd HH:mm"/>
                        </div>
                        <div class="form-group">
                            <label>글번호</label>
                            <input type="text" class="form-control" name="bno" value="${vo.bno }" readonly>
                        </div>
                        <div class="form-group">
                            <label>글쓴이</label>
                            <input type="text" class="form-control" name="writer" placeholder="자유" value="${vo.writer }" readonly>
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" class="form-control" name="title" placeholder="자유" value="${vo.title }" readonly>
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea class="form-control"  name="content" rows="5"  readonly>${vo.content }</textarea>
                        </div>
                        
                        <!--구현로직: 버튼은 온클릭을 사용하던 자바스크립트를 이용해야 합니다-->
                        <div class="form-group">
                            <button type="button" class="btn btn-success" onclick="location.href='list.board'">목록</button>
                            <button type="button" class="btn btn-info" onclick=" location.href = 'modify.board?bno=${vo.bno}&writer=${vo.writer}' " >수정</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>


        </section>
    
<%@ include file ="../include/footer.jsp" %>