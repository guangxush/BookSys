<%--
  Created by IntelliJ IDEA.
  User: Consule
  Date: 2017/9/2
  Time: 17:53
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
    <base href="<%=basePath%>">

    <title>My JSP 'list.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>
<body>
<table width="80%" align="center" >
    <tr>
        <td>编号</td>
        <td>名称</td>
        <td>分类</td>
        <td>价格</td>
        <td>作者</td>
        <td>出版日期</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="bean" varStatus="status">
        <tr <c:if test="${status.index%2==0 }">style="background:#0f0"</c:if>>
            <td>${bean.id}</td>
            <td>${bean.name}</td>
            <td>
                <c:forEach items="${clist}" var="category" >
                    <c:if test="${category.id==bean.categoryId}">
                        ${category.name}
                    </c:if>
                </c:forEach>
            </td>
            <td>${bean.price}</td>
            <td>${bean.author}</td>
            <td>${bean.pubDate}</td>
            <td><a href="book?op=getById&id=${bean.id }">修改</a>
                <a href="book?op=delete&id=${bean.id }">删除</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan=7 align="right">
            <a href="book?op=list&currentPage=${page.currentPage-1 }"
               <c:if test="${page.currentPage ==1 }">style="color:#ccc" onclick="javascript:return false;"</c:if>
            >上一页</a>
            <c:forEach begin="1" step="1" end="${page.totalPage}" var="pg">
                <a href="book?op=list&currentPage=${pg}">${pg}</a>
            </c:forEach>


            <a href="book?op=list&currentPage=${page.currentPage+1 }"
               <c:if test="${page.currentPage ==page.totalPage }">style="color:#ccc" onclick="javascript:return false;"</c:if>
            >下一页</a>
            <span>共${page.totalPage}页</span>
            到第<input id="cp" type="text" style="width:20px;" maxlength="4"/>页<input type="button" value="确定" onclick="search();"/>
        </td>
    </tr>
</table>
<script type="text/javascript">
    function search(){
        var cp =document.getElementById("cp").value;
        location.href="book?op=list&currentPage="+cp;
    }
</script>
</body>
</html>
