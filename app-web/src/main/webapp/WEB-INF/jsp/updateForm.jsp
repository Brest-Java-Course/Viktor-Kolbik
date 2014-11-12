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
            label {
                display: inline-block;
                width: 120px;
                text-align: left
            }
            input[type="text"]{
                margin: 0;
                padding: 2px;
                border: 1px solid;
                border-color: #999 #ccc #ccc;
                border-radius: 2px
            }
        </style>
    </head>

    <body>
        <div class="main">
            <c:if test="${userExistedError}">
                <script type="text/javascript"> alert("ERROR while updating user. User, with such login, has already existed")</script>
            </c:if>

            <form action="${pageContext.request.contextPath}/mvc/updateUser" method="put">
                <table>
                    <tbody>
                    <input type="text" id="id" name="id" value="${user.userId}" hidden="true"/><br>
                    <tr>
                        <td><label for="login">login</label></td>
                        <td><input type="text" id="login" name="login" value="${user.login}" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">name</label></td>
                        <td><input type="text" id="name" name="name" value="${user.userName}" /></td>
                    </tr>
                    <input type="reset" name="Reset" value="reset">
                    <input type="submit" value="update the user" />
                    </tbody>
                </table>
            </form>
            <a href="${pageContext.request.contextPath}/mvc/">REFERENCE TO LIST WITH ALL USERS</a>
        </div>

    </body>
</html>