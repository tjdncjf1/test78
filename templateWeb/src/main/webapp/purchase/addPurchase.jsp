<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page import="com.model2.mvc.service.domain.Purchase" %>
<%
	Purchase vo = (Purchase)request.getAttribute("vo");
%> --%>
<html>
<head>
</head>

<body>

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
<%-- 		<td><%=vo.getPurchaseProd().getProdNo()%></td> --%>
		<td>${purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
<%-- 		<td><%=vo.getBuyer().getUserId()%></td> --%>
		<td>${purchase.buyer.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		<c:if test="${purchase.paymentOption == '1'}">
			현금구매
		</c:if>
		<c:if test="${purchase.paymentOption == '2'}">
			신용구매
		</c:if>
		<%-- <%if(vo.getPaymentOption().equals("1")) {%>
			현금구매
		<%} else {%>
			신용구매
		<%} %> --%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
<%-- 		<td><%=vo.getReceiverName()%></td> --%>
		<td>${purchase.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
<%-- 		<td><%=vo.getReceiverPhone()%></td> --%>
		<td>${purchase.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
<%-- 		<td><%=vo.getDivyAddr()%></td> --%>
		<td>${purchase.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
<%-- 		<td><%=vo.getDivyRequest()%></td> --%>
		<td>${purchase.divyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
<%-- 		<td><%=vo.getDivyDate()%></td> --%>
		<td>${purchase.divyDate}</td>
		<td></td>
	</tr>
</table>

</body>
</html>