<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>

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

            <span class="float_right">
                <a href="?lang=en">en</a>
                |
                <a href="?lang=ru">ru</a>
            </span>

            <div class="createForm">
                <form action="<spring:url value='/stars/addStar'/>" method="post">
                    <table>
                        <tbody>
                            <tr>
                                <td><label for="name"><spring:message code="name"/></label></td>
                                <td><input type="text" id="createName" name="name" value="" /></td>
                            </tr>
                            <tr>
                                <td><label for="age"><spring:message code="star.age"/></label></td>
                                <td><input type="number" id="createAge" name="age" value="" /></td>
                            </tr>
                            <tr>
                                <td><label for="mass"><spring:message code="star.mass"/></label>
                                </td><td><input type="number" id="createMass" name="mass" value="" /></td>
                            </tr>
                            <tr>
                                <td><label for="date"><spring:message code="date"/></label></td>
                                <td><input type="date" id="createDate"  class="dateInput" name="date" value="" /></td>
                            </tr>
                            <tr>
                                <td><label for="galaxyId"><spring:message code="star.galaxyId"/></label></td>
                                <td><input type="number" id="createGalaxyId" name="galaxyId" value="" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="reset" name="Reset" value=<spring:message code="reset"/>>
                                	<input type="submit" value=<spring:message code="add"/>>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
         </div>            
         <div class="updateForm">
            <form id="updateStarForm" action="<spring:url value='/stars/updateStar'/>" method="put">
                <table>
                    <tbody>
                    <input type="text" id="updateStarId" name="starId" value="" hidden="true"/>
                    <tr>
                        <td><label for="name"><spring:message code="name"/></label></td>
                        <td><input type="text" id="updateName" name="name" value="" /></td>
                    </tr>
                    <tr>
                        <td><label for="name"><spring:message code="star.age"/></label></td>
                        <td><input type="number" id="updateAge" name="age" value="" /></td>
                    </tr>
                    <tr>
                        <td><label for="name"><spring:message code="star.mass"/></label></td>
                        <td><input type="number" id="updateMass" name="mass" value="" /></td>
                    </tr>
                    <tr>
                        <td><label for="name"><spring:message code="date"/></label></td>
                        <td><input type="date" id="updateDate" class="dateInput" name="date" value="" /></td>
                    </tr>
                    <tr>
                        <td><label for="name"><spring:message code="star.galaxyId"/></label></td>
                        <td><input type="number" id="updateGalaxyId" name="galaxyId" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="reset" name="Reset" value=<spring:message code="reset"/>>
                        	<input type="submit" value=<spring:message code="update"/>>
                        </td>
                    
                    </tbody>
                </table>
            </form>
        </div>

        <div>
            <table>
                <tbody>
                    <tr>
                        <td><label for="lowBorder"><spring:message code="lowBorder"/></label></td>
                        <td><input type="date" id="lowBorder" class="dateInput" name="lowBorder"/></td>
                    </tr>
                    <tr>
                        <td><label for="topBorder"><spring:message code="topBorder"/></label></td>
                        <td><input type="date" id="topBorder" class="dateInput" name="topBorder"/></td>
                     </tr>
                     <tr>
                        <td></td>
                        <td><input type="button" id="findBtn" name="findBtn" value=<spring:message code="find"/> onclick="filterByDate()"/></td>
                     </tr>
                     
                </tbody>
            </table>
        </div>    
        <div>
	        <table class="features-table" id="mainTable" summary="list stars.">
	                        <caption><a href="<spring:url value='/stars/'/>"><spring:message code="star.get_all"/></a></caption>
	                <tr>
	                    <td><h2><spring:message code="id"/></h2></td>
	                    <td><h2><spring:message code="name"/></h2></td>
	                    <td><h2><spring:message code="star.mass"/></h2></td>
	                    <td><h2><spring:message code="star.age"/></h2></td>
	                    <td><h2><spring:message code="date"/></h2></td>
	                    <td><h2><spring:message code="star.galaxyId"/></h2></td>
	                    <td><h2><spring:message code="update"/></h2></td>
	                    <td><h2><spring:message code="remove"/></h2></td>
	                </tr>
	            <tbody>

	            </tbody>
	         </table>
         </div>
            <a class="float_right" href="<spring:url value='/galaxies/'/>"><spring:message code="to_galaxies_page"/></a>

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
            stringHtml += "<td class=\"grey\">" + "<input type=\"button\" value=<spring:message code="update"/> onClick=" + "\"fillInUpdateForm(" + starId + "," + strName + "," + age + "," + mass + "," + strDate + "," + galaxyId + ")\"" + ">";
            
             stringHtml += "<td class=\"grey\">" + "<form action=\"<spring:url value='/stars/removeStar'/>\" method=\"delete\">" +
                "<input type=\"text\" id=\"starId\" name=\"starId\" value=" + starId + " hidden=\"true\">" + 
                "<input type=\"submit\" value=<spring:message code="remove"/> ></form>";
             
             return stringHtml;
        }
       
        </script>
    </body>
</html>