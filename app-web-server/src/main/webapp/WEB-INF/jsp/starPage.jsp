<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
            <title>Stars Page</title>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/page.css" />">
    </head>

    <body>
        <div class="main">
            <c:if test="${creationError}">
                <script type="text/javascript"> alert("${wrongParameter}")</script>
            </c:if>
            <c:if test="${updatingError}">
                <script type="text/javascript"> alert("${wrongParameter}")</script>
            </c:if>
            <c:if test="${removingError}">
                <script type="text/javascript"> alert("${wrongParameter}")</script>
            </c:if>
            
            <div class="createForm">
                <form action="${pageContext.request.contextPath}/stars/addStar" method="post">
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
                                <td><label for="date">date</label></td><td><input type="date" id="createDate"  class="dateInput" name="date" value="" /></td>
                            </tr>
                            <tr>
                                <td><label for="galaxyId">galaxyId</label></td><td><input type="number" id="createGalaxyId" name="galaxyId" value="" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="reset" name="Reset" value="reset"><input type="submit" value="add the user" /></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
         </div>            
         <div class="updateForm">
            <form id="updateStarForm" action="${pageContext.request.contextPath}/stars/updateStar" method="put">
                <table>
                    <tbody>
                    <input type="text" id="updateStarId" name="starId" value="" hidden="true"/>
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
                        <td><input type="date" id="updateDate" class="dateInput" name="date" value="" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">Galaxy ID</label></td>
                        <td><input type="number" id="updateGalaxyId" name="galaxyId" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="reset" name="Reset" value="reset"><input type="submit" value="update the star" /></td>
                    
                    </tbody>
                </table>
            </form>
        </div>

        <div>
            <table>
                <tbody>
                    <tr>
                        <td><label for="lowBorder">LowBorder</label></td>
                        <td><input type="date" id="lowBorder" class="dateInput" name="lowBorder"/></td>
                    </tr>
                    <tr>
                        <td><label for="topBorder">TopBorder</label></td>
                        <td><input type="date" id="topBorder" class="dateInput" name="topBorder"/></td>
                     </tr>
                     <tr>
                        <td></td>
                        <td><input type="button" id="findBtn" name="findBtn" value="Find by Date" onclick="filterByDate()"/></td>
                     </tr>
                     
                </tbody>
            </table>
        </div>    
        
        <table class="features-table" id="mainTable" summary="list stars.">
                        <caption><a href="${pageContext.request.contextPath}/stars/">GET ALL STARS</a></caption>
                <tr>
                    <td><h2>Id</h2></td>
                    <td><h2>Name</h2></td>
                    <td><h2>Age</h2></td>
                    <td><h2>Mass</h2></td>
                    <td><h2>Date</h2></td>
                    <td><h2>galaxy Id</h2></td>
                    <td><h2>Update</h2></td>
                    <td><h2>Delete</h2></td>
                </tr>
            <tbody>

            </tbody>
         <table>
         
         <script src="<c:url value="/resources/js/jquery-1.11.1.js" />"></script>
         <script src="<c:url value="/resources/js/date.js" />"></script>
         <script type="text/javascript">
            $(fillInFullTable);
            $(setCurrentDate);
                             
            function setCurrentDate(){
            
                    var currentDateString = (new Date()).toString('yyyy-MM-dd');
                    var currentDate = new Date("");
                    var elements = document.getElementsByClassName('dateInput');
                      
                    for(var i = 0; i < elements.length; i ++){
                        elements[i].setAttribute("value", currentDateString);
                    }
            }
            
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
            $('#updateDate').val(date);         
        };


        function cleanMainTable(){
        
            var table = document.getElementById("mainTable");
            var rows = table.getElementsByTagName("tr");
            var length = rows.length;
        
            for ( var i = 1; i < length; i++ )  {   
                table.deleteRow(1);                                                                               
            }
        }
        
        function filterByDate(){
            var tbody = document.getElementById("mainTable").getElementsByTagName("tbody")[0];
            var lowBorder = $('#lowBorder').val();
            var topBorder = $('#topBorder').val();
            var actionFlag;                         
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
            var string2Html = "";
            var strName = "\'" + name + "\'";
            var strDate = "\'" + date + "\'";

            stringHtml += "<tr><td class=\"grey\">" + starId;
            stringHtml += "<td class=\"green\">"  + name;
            stringHtml += "<td class=\"green\">"  + age;
            stringHtml += "<td class=\"green\">" + mass;
            stringHtml += "<td class=\"green\">"  + date;
            stringHtml += "<td class=\"green\">" + galaxyId;
            stringHtml += "<td class=\"grey\">" + "<input type=\"button\" value=\"Update\" onClick=" + "\"fillInUpdateForm(" + starId + "," + strName + "," + age + "," + mass + "," + strDate + "," + galaxyId + ")\"" + ">";
            
             stringHtml += "<td class=\"grey\">" + "<form action=\"${pageContext.request.contextPath}/stars/removeStar\" method=\"delete\">" +
                "<input type=\"text\" id=\"starId\" name=\"starId\" value=" + starId + " hidden=\"true\">" + 
                "<input type=\"submit\" value=\"remove\" ></form>";
             
             return stringHtml;
        }
       
        </script>
    </body>
</html>