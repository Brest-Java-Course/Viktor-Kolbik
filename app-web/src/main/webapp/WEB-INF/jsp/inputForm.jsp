<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <style>
            .main {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: blanchedalmond;
            }

            .test{
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: blanchedalmond;
            }


            html {
                min-height: 100%
            }
            body {
                min-height: 100%;
                background: #fff;
                font: 14px/125% Tahoma;
            }

            .row {
                margin: 1em 0
            }
            label {
                display: inline-block;
                width: 120px;
                text-align: left
            }
            input[type="text"] {
                margin: 0;
                padding: 2px;
                border: 1px solid;
                border-color: #999 #ccc #ccc;
                border-radius: 2px
            }


            .features-table
            {
                width: 100%;
                margin: 0 auto;
                border-collapse: separate;
                border-spacing: 0;
                border: 0;
                text-shadow: 0 1px 0 #fff;
                color: #2a2a2a;
                background: #fafafa;
                background-image: -moz-linear-gradient(top, #fff, #eaeaea, #fff); /* Firefox 3.6 */
                background-image: -webkit-gradient(linear,center bottom,center top,from(#fff),color-stop(0.5, #eaeaea),to(#fff));
                margin-top:20px;
                margin-bottom:20px;
            }
            .features-table td
            {
                height: 50px;
                padding: 0 20px;
                border-bottom: 1px solid #cdcdcd;
                box-shadow: 0 1px 0 white;
                -moz-box-shadow: 0 1px 0 white;
                -webkit-box-shadow: 0 1px 0 white;
                text-align: center;
                vertical-align: middle;
                display: table-cell;
            }
            .features-table tbody td
            {
                text-align: center;
                width: 150px;
            }
            .features-table td.grey
            {
                background: #efefef;
                background: rgba(144,144,144,0.15);
                border-right: 0px;
            }
            .features-table td.green
            {
                background: #e7f3d4;
                //                background: rgba(184,243,85,0.3);
            }
            .features-table td:nowrap
            {
                white-space: nowrap;
            }
            .features-table thead td
            {
                font-size: 120%;
                font-weight: bold;
                -moz-border-radius-topright: 10px;
                -moz-border-radius-topleft: 10px;
                border-top-right-radius: 10px;
                border-top-left-radius: 10px;
                border-top: 1px solid #eaeaea;
            }
            .features-table tfoot td
            {
                font-size: 120%;
                font-weight: bold;
                -moz-border-radius-bottomright: 10px;
                -moz-border-radius-bottomleft: 10px;
                border-bottom-right-radius: 10px;
                border-bottom-left-radius: 10px;
                border-bottom: 1px solid #dadada;
            }
        </style>
    </head>

    <body>
        <div class="main">
            <c:if test="${errorCreation}">
                <script type="text/javascript"> alert("ERROR while creating user")</script>
            </c:if>
            <c:if test="${errorRemoving}">
                <script type="text/javascript"> alert("ERROR while removing user!")</script>
            </c:if>

            <form action="${pageContext.request.contextPath}/mvc/usersByName" method="get">
                <table align="right">
                    <tbody>
                        <tr>
                            <td><input type="text" id="name" name="name" value="" /></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="get users by name" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>

            <form action="${pageContext.request.contextPath}/mvc/submitData" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td><label for="name">name</label></td><td><input type="text" id="name" name="name" value="" /></td>
                        </tr>
                        <tr>
                            <td><label for="login">login</label></td><td><input type="text" id="login" name="login" value="" /></td>
                        </tr>
                        <tr>
                            <td></td><td><input type="reset" name="Reset" value="reset">
                                <input type="submit" value="add the user" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <TABLE class="features-table" summary="list users."> 

                <c:choose>
                    <c:when test="${usersByName}">
                        <CAPTION><a href="${pageContext.request.contextPath}/mvc/">REFERENCE TO LIST WITH ALL USERS</a></CAPTION>
                    </c:when>
                    <c:otherwise>
                        <CAPTION>LIST USERS</CAPTION>
                    </c:otherwise>
                </c:choose>

                <COLGROUP align="center"><COLGROUP align="left"> <COLGROUP align="center" span="2"><COLGROUP align="center" span="3">
                <tr><td><h2>Id</h2><td><h2>Login</h2><td><h2>Name</h2><td><h2>Update</h2><td><h2>Delete</h2>

                <c:forEach var="user" items="${users}">
                    <TR>
                        <TD class="grey"> <c:out value="${user.userId}"></c:out> 
                    <TD class="green"><c:out value="${user.login}"></c:out>
                    <TD class="green"> <c:out value="${user.userName}"></c:out>
                    <TD class="grey"> <form action="${pageContext.request.contextPath}/mvc/fillInUpdateForm" method="put">
                            <input type="text" id="id" name="id" value="${user.userId}" hidden="true" />
                            <input type="text" id="login" name="login" value="${user.login}" hidden="true" />
                            <input type="text" id="name" name="name" value="${user.userName}" hidden="true" />
                            <input type="submit" value="update" />
                        </form>
                    <TD class="grey"> <form action="${pageContext.request.contextPath}/mvc/removeUser" method="delete">
                            <input type="text" id="id" name="id" value="${user.userId}" hidden="true" />
                            <input type="submit" value="remove" />
                        </form>
                </c:forEach>

        </div>

    </body>
</html>