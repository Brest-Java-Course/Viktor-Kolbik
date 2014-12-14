<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>

<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/page.css" />">
    </head>

    <body>
        <div class="main">
            <c:if test="${creationError}">
                <script type="text/javascript">
                    alert("${wrongParameter}");
                    setTimeout('window.location="${pageContext.request.contextPath}/galaxies/"',1);
                </script>
            </c:if>
            <c:if test="${updatingError}">
                <script type="text/javascript">
                    alert("${wrongParameter}");
                    setTimeout('window.location="${pageContext.request.contextPath}/galaxies/"',1);
                </script>
            </c:if>

            <span class="float_right">
                <a href="?lang=en">en</a>
                |
                <a href="?lang=ru">ru</a>
            </span>
           
            <div class="createForm">
                <form action="<spring:url value='/galaxies/addGalaxy'/>" method="post">
                    <table>
                        <tbody>
                            <tr>
                                <td><label for="name"><spring:message code="name"/></label></td>
                                <td><input type="text" id="createName" name="name" value="" required="true" /></td>
                            </tr>
                            <tr>
                                <td><label for="distance"><spring:message code="galaxy.distance"/></label></td>
                                <td><input type="number" id="createDistance" name="distance" value="" min="0" required="true" /></td>
                            </tr>
                            <tr>
                                <td><label for="date"><spring:message code="date"/></label></td>
                                <td><input type="date" class="dateInput" id="createDate" name="date" value="" required="true" /></td>
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
                <form id="updateGalaxyForm" action="<spring:url value='/galaxies/updateGalaxy'/>" method="put">
                    <table>
                        <tbody>

                            <input type="text" id="updateGalaxyId" name="galaxyId" value="" hidden="true"/>
                            <tr>
                                <td><label for="name"><spring:message code="name"/></label></td>
                                <td><input type="text" id="updateName" name="name" value="" required="true" /></td>
                            </tr>
                            <tr>
                                <td><label for="distance"><spring:message code="galaxy.distance"/></label></td>
                                <td><input type="number" id="updateDistance" name="distance" value="" min="0" required="true" /></td>
                            </tr>
                            <tr>
                                <td><label for="date"><spring:message code="date"/></label></td>
                                <td><input type="date" id="updateDate" class="dateInput" name="date" value="" required="true" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="reset" name="Reset" value=<spring:message code="reset"/>>
                                	<input type="submit" value=<spring:message code="update"/> >
                              	</td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        <div>
            <table>
                <tbody>
                        <tr>
                            <td><label for="lowBorder"><spring:message code="lowBorder"/></label></td>
                            <td><input type="date" id="lowBorder" class="dateInput" name="lowBorder" required="true" /></td>
                        </tr>
                        <tr>
                            <td><label for="topBorder"><spring:message code="topBorder"/></label></td>
                            <td><input type="date" id="topBorder" class="dateInput" name="topBorder" required="true" /></td>
                         </tr>
                         <tr>
                            <td></td>
                            <td><input type="button" id="findBtn" name="findBtn" value=<spring:message code="find"/> onclick="filterByDate()"/></td>
                         </tr>
                </tbody>
            </table>
        </div>
        
        <div class="place" id="place">

        </div>
        <div>
            <table class="features-table" id="mainTable" summary="list of stars.">
                <caption><a href="<spring:url value='/galaxies/'/>"><spring:message code="galaxy.get_all"/></a></caption>
                    <tr>
                        <td><h2><spring:message code="id"/></h2></td>
                        <td><h2><spring:message code="name"/></h2></td>
                        <td><h2><spring:message code="galaxy.distance"/></h2></td>
                        <td><h2><spring:message code="galaxy.average_mass"/></h2></td>
                        <td><h2><spring:message code="galaxy.average_age"/></h2></td>
                        <td><h2><spring:message code="date"/></h2></td>
                        <td><h2><spring:message code="update"/></h2></td>
                        <td><h2><spring:message code="remove"/></h2></td>
                    </tr>
                    <tbody>

	                </tbody>
            </table>
        </div>
        <a class="float_right" href="<spring:url value='/stars/'/>"><spring:message code="to_stars_page"/></a>

        <script src="<c:url value="/resources/js/jquery-1.11.1.js" />"></script>
        <script src="<c:url value="/resources/js/date.js" />"></script>
        <script src="<c:url value="/resources/js/detect.js" />"></script>
        <script type="text/javascript">
            $(changeInput);
            $(fillInFullTable);
            $(setCurrentDate);
            
                        function changeInput(){
                                                                        
                        var user = detect.parse(navigator.userAgent);
                        var regExp = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
                        
                        if(user.browser.family === "Firefox"){
                            $(document.getElementById("place")).append("<br><br><br><br><br><br><br>");
                            
                            $("#createDate").replaceWith("<input type='text' id='createDate'  class='dateInput' name='date' value='' required='true' pattern='" + regExp + "'/>");
                            $("#updateDate").replaceWith("<input type='text' id='updateDate'  class='dateInput' name='date' value='' required='true' pattern='" + regExp + "'/>");
                            $("#topBorder").replaceWith("<input type='text' id='topBorder'  class='dateInput' name='date' value='' required='true' pattern='" + regExp + "'/>");
                            $("#lowBorder").replaceWith("<input type='text' id='lowBorder'  class='dateInput' name='date' value='' pattern='" + regExp + "'/>");
                            
                        }
            }
                             
            function setCurrentDate(){
            
                    var currentDateString = (new Date()).toString('yyyy-MM-dd');
                    var elements = document.getElementsByClassName('dateInput');
                      
                    for(var i = 0; i < elements.length; i++){
                        elements[i].setAttribute("value", currentDateString);
                    }
            }



        function fillInFullTable(){
  
            var stringHtml = "";

            <c:forEach var="galaxy" items="${galaxies}">
                stringHtml += setStringHtml(${galaxy.galaxyId}, '${galaxy.name}', ${galaxy.distance}, ${galaxy.averageMass}, ${galaxy.averageAge}, '${galaxy.date}');
            </c:forEach>

            $(document.getElementById("mainTable").getElementsByTagName("tbody")[0]).append(stringHtml);
        }

        function fillInUpdateForm(galaxyId, name, distance, date){

            $('#updateGalaxyId').val(galaxyId);
            $('#updateName').val(name);
            $('#updateDistance').val(distance);
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


                    <c:forEach var="galaxy" items="${galaxies}">
                        if(actionFlag == 0 && "${galaxy.date}" >= lowBorder){

                            stringHtml += setStringHtml(${galaxy.galaxyId}, '${galaxy.name}', ${galaxy.distance}, ${galaxy.averageMass}, ${galaxy.averageAge}, '${galaxy.date}');
                        } else if(actionFlag == 1 && "${galaxy.date}" <= topBorder){
                            stringHtml += setStringHtml(${galaxy.galaxyId}, '${galaxy.name}', ${galaxy.distance}, ${galaxy.averageMass}, ${galaxy.averageAge}, '${galaxy.date}');
                        }else if(actionFlag == 2 && ("${galaxy.date}" >= lowBorder && "${galaxy.date}" <= topBorder)){
                            stringHtml += setStringHtml(${galaxy.galaxyId}, '${galaxy.name}', ${galaxy.distance}, ${galaxy.averageMass}, ${galaxy.averageAge}, '${galaxy.date}');
                        }
                     </c:forEach>

                     $(document.getElementById("mainTable").getElementsByTagName("tbody")[0]).append(stringHtml);
        }

        function setStringHtml(galaxyId, name, distance, averageMass, averageAge, date){
            var stringHtml = "";
            var string2Html = "";
            var strName = "\'" + name + "\'";
            var strDate = "\'" + date + "\'";

             stringHtml += "<tr><td class=\"grey\">" + "<form action=\"<spring:url value='/stars/starsIntoGalaxy/'/>\">" +
                "<input type=\"text\" id=\"galaxyId\" name=\"galaxyId\" value=" + galaxyId + " hidden=\"true\">" + 
                "<input type=\"submit\" value=" + galaxyId + " ></form>";
             stringHtml += "<td class=\"green\">"  + name;
             stringHtml += "<td class=\"green\">"  + distance;
             stringHtml += "<td class=\"green\">" + averageMass;
             stringHtml += "<td class=\"green\">" + averageAge;
             stringHtml += "<td class=\"green\">"  + date;
             stringHtml += "<td class=\"grey\">" + "<input type=\"button\" value=<spring:message code="update"/> onClick=" + "\"fillInUpdateForm(" + galaxyId + "," + strName + "," + distance + ", " + strDate + ")\"" + ">";
             stringHtml += "<td class=\"grey\">" + "<form action=\"<spring:url value='/galaxies/removeGalaxy'/>\" method=\"delete\">" +
                                                                 "<input type=\"text\" id=\"galaxyId\" name=\"galaxyId\" value=" + galaxyId + " hidden=\"true\">" +
                                                                 "<input type=\"submit\" value=<spring:message code="remove"/> ></form>";
             return stringHtml;
        }

        </script>
    </body>
</html>