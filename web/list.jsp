<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/10/13
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>WebDemo</title>
</head>
<body>
    <h3>客户列表</h3>
    <table border="1" width="70%" align="center">
        <tr>
            <th>客户姓名</th>
            <th>客户性别</th>
            <th>手机</th>
            <th>邮箱</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.beanList}" var="customer">
            <tr>
                <td>${customer.name}</td>
                <td>${customer.gender}</td>
                <td>${customer.phone}</td>
                <td>${customer.email}</td>
                <td>${customer.description}</td>
                <td>
                    <a href="<c:url value='/CustomerServlet?method=preEdit&id=${customer.id}'/> ">修改</a>
                    <a href="<c:url value='/CustomerServlet?method=delete&id=${customer.id}'/> ">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
<br/>
<center>
    第${pb.getPc()}页/共${pb.getTp()}页
    <a href="${pb.getUrl()}&pc=1">首页</a>
    <c:if test="${pb.getPc() > 1}">
        <a href="${pb.getUrl()}&pc=${pb.getPc() - 1}">上一页</a>
    </c:if>

    <c:choose>
        <c:when test="${pb.getTp()<=10}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${pb.getTp()}"/>
        </c:when>

        <c:otherwise>
            <c:set var="begin" value="${pb.getPc() - 4}"/>
            <c:set var="end" value="${pb.getPc() + 5}"/>

            <c:if test="${begin<1}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="10"/>
            </c:if>

            <c:if test="${end>pb.getTp()}">
                <c:set var="begin" value="${pb.getTp()-9}"/>
                <c:set var="end" value="${pb.getTp()}"/>
            </c:if>
        </c:otherwise>
    </c:choose>

    <c:forEach var="i" begin="${begin}" end="${end}">
        <c:choose>
            <c:when test="${i eq pb.getPc()}">
                [${i}]
            </c:when>
            <c:otherwise>
                <a href="${pb.getUrl()}&pc=${i}">[${i}]</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${pb.getPc()<pb.getTp()}">
        <a href="${pb.getUrl()}&pc=${pb.getPc()-1}">下一页</a>
    </c:if>
    <a href="${pb.getUrl()}&pc=${pb.getTp()}">尾页</a>
</center>
</body>
</html>
