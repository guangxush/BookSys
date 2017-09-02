<%--
  Created by IntelliJ IDEA.
  User: Consule
  Date: 2017/9/2
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>add.html</title>

    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">

    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

</head>
<body>
<form action="book" method="get">
    <table width="80%" align="center">
        <tr>
            <td colspan="2"> <h3>修改书籍</h3></td>
        </tr>
        <tr>
            <td>书名<input type="hidden" name="op" value="update"/></td>
            <input type="hidden" name="id" value="${book.id}"/>
            <td><input type="text" name="name" value="${book.name}"/></td>
        </tr>
        <tr>
            <td>分类</td>
            <td>
                <select name="categoryId">
                    <c:forEach items="${clist}" var="bean" >
                        <option value=${bean.id}
                                <c:if test="${bean.id==book.categoryId}">
                                        selected="true"
                        </c:if>

                        >${bean.name}</option>
                    </c:forEach>
                </select>

            </td>
        </tr>
        <tr>
            <td>价格</td>
            <td><input type="text" name="price" value="${book.price}"/></td>
        </tr>
        <tr>
            <td>作者</td>
            <td><input type="text" name="author" value="${book.author}"/></td>
        </tr>
        <tr>
            <td>出版日期</td>
            <td><input type="text" name="pubDate" value="${book.pubDate}"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" name="提交"/></td>
        </tr>

    </table>
</form>
</body>
</html>
