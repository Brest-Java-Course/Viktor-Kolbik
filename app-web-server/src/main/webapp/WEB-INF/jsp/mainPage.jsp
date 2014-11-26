<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
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
                        .createStarForm{
                                float: left;
            }
        </style>
    </head>

    <body>
        <div class="main">
            <c:if test="${creationError}">
                <script type="text/javascript"> alert("ERROR while creating star")</script>
            </c:if>
                        <c:if test="${updatingError}">
                <script type="text/javascript"> alert("${wrongParameter}")</script>
            </c:if>
            <c:if test="${removingError}">
                <script type="text/javascript"> alert("ERROR while removing star!")</script>
            </c:if>
<div class="createStarForm">
            <form action="${pageContext.request.contextPath}/mvcServer/addStar" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td><label for="name">name</label></td><td><input type="text" id="createName" name="name" value="" /></td>
                        </tr>
                        <tr>
                            <td><label for="age">age</label></td><td><input type="number" id="createAge" name="age" value="" /></td>
                        </tr>
                        <tr>
                            <td><label for="mass">mass</label></td><td><input type="number" id="createMass" name="mass" value="" /></td>
                        </tr>
                        <tr>
                            <td><label for="date">date</label></td><td><input type="date" id="createDate" name="date" value="" /></td>
                        </tr>
                        <tr>
                            <td><label for="galaxyId">galaxyId</label></td><td><input type="number" id="createGalaxyId" name="galaxyId" value="" /></td>
                        </tr>
                        <tr>
                            <td></td><td><input type="reset" name="Reset" value="reset">
                                <input type="submit" value="add the user" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
         </div>            
            <div class="updateStarForm">
<form id="updateStarForm" action="${pageContext.request.contextPath}/mvcServer/updateStar" method="put">
                <table>
                    <tbody>

                    <input type="text" id="updateStarId" name="starId" value="" hidden="true"/><br>
                    <tr>
                        <td><label for="name">Name</label></td>
                        <td><input type="text" id="updateName" name="name" value="" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">Age</label></td>
                        <td><input type="number" id="updateAge" name="age" value="" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">Mass</label></td>
                        <td><input type="number" id="updateMass" name="mass" value="" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">Date</label></td>
                        <td><input type="date" id="updateDate" name="date" value="" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">Galaxy ID</label></td>
                        <td><input type="number" id="updateGalaxyId" name="galaxyId" value="" /></td>
                    </tr>
                    <input type="reset" name="Reset" value="reset">
                    <input type="submit" value="update the star" />
                    </tbody>
                </table>
            </form>
            </div>


        <table>
            <tbody>
                <tr>
                    <td><label for="lowBorder">LowBorder</label></td>
                    <td><input type="date" id="lowBorder" name="lowBorder"/></td>
                    <td><label for="topBorder">TopBorder</label></td>
                    <td><input type="date" id="topBorder" name="topBorder"/></td>
                    <input type="button" id="findBtn" name="findBtn" value="Find" onclick="filterByDate()"/>
                </tr>
            </tbody>
        </table>
            
            
            <input type="button" id="findBtn" name="findBtn" value="TEST_CLEAR_TABLE" onclick="cleanMainTable()"/>
            <TABLE class="features-table" id="mainTable" summary="list stars.">


                        <CAPTION><a href="${pageContext.request.contextPath}/mvcServer/">GET ALL STARS</a></CAPTION>


                <COLGROUP align="center"><COLGROUP align="left"> <COLGROUP align="center" span="2"><COLGROUP align="center" span="3">
                <tr><td><h2>Id</h2><td><h2>Name</h2><td><h2>Age</h2><td><h2>Mass</h2><td><h2>Date</h2><td><h2>galaxy Id</h2><td><h2>Update</h2><td><h2>Delete</h2>
            
                <!--<c:forEach var="star" items="${stars}">
                    <TR>
                        <TD class="grey"> <c:out value="${star.starId}"></c:out>
                    <TD class="green"> <c:out value="${star.name}"></c:out>
                    <TD class="green"> <c:out value="${star.age}"></c:out>
                    <TD class="green"> <c:out value="${star.mass}"></c:out>
                    <TD class="green"> <c:out value="${star.date}"></c:out>
                    <td class="green"> <input type="button" id="updateStarButton" value=<c:out value="${star.galaxyId}"></c:out>>
                    <TD class="grey"> <input type="button" id="updateStarButton" value="Update" onclick="fillInUpdateForm(${star.starId}, '${star.name}', ${star.age}, ${star.mass}, ${star.date}, ${star.galaxyId})"/>
                    <td class="grey"><form action="${pageContext.request.contextPath}/mvcServer/removeStar" method="delete">
                                                                 <input type="text" id="starId" name="starId" value="${star.starId}" hidden="true" />
                                                                 <input type="submit" value="remove" />
                                                             </form>
                </c:forEach>-->
        </div>

        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript">
            $(fillInFullTable);
        function fillInFullTable(){
            var stringHtml = "";
                
                                <c:forEach var="star" items="${stars}">
                                    stringHtml += setStringHtml(${star.starId}, '${star.name}', ${star.age}, ${star.mass}, '${star.date}', ${star.galaxyId});      
                                </c:forEach>
                        
                                $(document.getElementById("mainTable").getElementsByTagName("tbody")[0]).append(stringHtml);
        }

        function fillInUpdateForm(starId, name, age, mass, date, galaxyId){

            $('#updateStarId').val(starId);
            $('#updateName').val(name);
            $('#updateAge').val(age);
            $('#updateMass').val(mass);
            $('#updateGalaxyId').val(galaxyId);            
        };

        function cleanMainTable(){
        var table = document.getElementById("mainTable");
        var rows = table.getElementsByTagName("tr");
        var length = rows.length;
            for ( var i = 1; i < length; i++ )  {   
                table.deleteRow(1);                         //всегда удаляю первую, потому что после удаления первой строки, вторая становиться первой
                                                            //можно было начать с конца, но так проще 
                                                            //нулевая - заголовок таблицы
        }
      }
        
        function filterByDate(){
                    var tbody = document.getElementById("mainTable").getElementsByTagName("tbody")[0];
                    var lowBorder = $('#lowBorder').val();
                    var topBorder = $('#topBorder').val();
                    var actionFlag;                         //0 - вывести все старше lowBorder,
                                                            //1 - все младше topBorder
                                                            //2 - диапазон между lowBorder, и topBorder
                    var stringHtml = "";
                     
                    if(lowBorder == "" && topBorder == ""){
                        return;
                    } else if(lowBorder != "" && topBorder == "") {
                        actionFlag = 0;
                    } else if(lowBorder == "") {
                        actionFlag = 1;
                    } else if(lowBorder > topBorder ){
                        return;
                    }else {
                        actionFlag = 2;
                    }

                    cleanMainTable();


                                <c:forEach var="star" items="${stars}">
                                    if(actionFlag == 0 && "${star.date}" >= lowBorder){

                                        stringHtml += setStringHtml(${star.starId}, '${star.name}', ${star.age}, ${star.mass}, '${star.date}', ${star.galaxyId});
                                    } else if(actionFlag == 1 && "${star.date}" <= topBorder){
                                        stringHtml += setStringHtml(${star.starId}, '${star.name}', ${star.age}, ${star.mass}, '${star.date}', ${star.galaxyId});
                                    }else if(actionFlag == 2 && ("${star.date}" >= lowBorder && "${star.date}" <= topBorder)){
                                        stringHtml += setStringHtml(${star.starId}, '${star.name}', ${star.age}, ${star.mass}, '${star.date}', ${star.galaxyId});
                                    }
                                    
                                    
                                </c:forEach>
                        
                                $(document.getElementById("mainTable").getElementsByTagName("tbody")[0]).append(stringHtml);
        }
        
        function setStringHtml(starId, name, age, mass, date, galaxyId){
            var stringHtml = "";
            var strName = "\'" + name + "\'";
             stringHtml += "<tr><td class=\"grey\">" + starId;
             stringHtml += "<td class=\"green\">"  + name;
             stringHtml += "<td class=\"green\">"  + age;
             stringHtml += "<td class=\"green\">" + mass;
             stringHtml += "<td class=\"green\">"  + date;
             stringHtml += "<td class=\"green\">" + galaxyId;
             stringHtml += "<td class=\"grey\">" + "<input type=\"button\" value=\"Update\" onClick=" + "\"fillInUpdateForm(" + starId + "," + strName + "," + age + "," + mass + "," + date + "," + galaxyId + ")\"" + ">";
             stringHtml += "<td class=\"grey\">" + "<form action=\"${pageContext.request.contextPath}/mvcServer/removeStar\" method=\"delete\">" + 
                                                                 "<input type=\"text\" id=\"starId\" name=\"starId\" value=" + starId + " hidden=\"true\">" + 
                                                                 "<input type=\"submit\" value=\"remove\" ></form>";
             return stringHtml;
        }
       
        </script>
    </body>
</html>