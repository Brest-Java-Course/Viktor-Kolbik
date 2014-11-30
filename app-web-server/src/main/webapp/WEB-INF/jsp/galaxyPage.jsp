<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/page.css" />">
    </head>

    <body>
        <div class="main">
            <c:if test="${creationError}">
                <script type="text/javascript"> alert("ERROR while creating star")</script>
            </c:if>
            <c:if test="${updatingError}">
                <script type="text/javascript"> alert("ERROR while updating")</script>
            </c:if>
            <c:if test="${removingError}">
                <script type="text/javascript"> alert("ERROR while removing star!")</script>
            </c:if>
            <div class="createForm">
                <form action="${pageContext.request.contextPath}/galaxies/addGalaxy" method="post">
                    <table>
                        <tbody>
                            <tr>
                                <td><label for="name">Name</label></td><td><input type="text" id="createName" name="name" value="" /></td>
                            </tr>
                            <tr>
                                <td><label for="distance">Distance</label></td><td><input type="number" id="createDistance" name="distance" value="" /></td>
                            </tr>
                            <tr>
                                <td><label for="date">date</label></td><td><input type="date" class="dateInput" id="createDate" name="date" value="" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="reset" name="Reset" value="reset"><input type="submit" value="add the galaxy" /></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
             </div>
            <div class="updateForm">
                <form id="updateGalaxyForm" action="${pageContext.request.contextPath}/galaxies/updateGalaxy" method="put">
                    <table>
                        <tbody>

                            <input type="text" id="updateGalaxyId" name="galaxyId" value="" hidden="true"/>
                            <tr>
                                <td><label for="name">Name</label></td>
                                <td><input type="text" id="updateName" name="name" value="" /></td>
                            </tr>
                            <tr>
                                <td><label for="distance">Distance</label></td>
                                <td><input type="number" id="updateDistance" name="distance" value="" /></td>
                            </tr>
                            <tr>
                                <td><label for="date">Date</label></td>
                                <td><input type="date" id="updateDate" class="dateInput" name="date" value="" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="reset" name="Reset" value="reset"><input type="submit" value="update the galaxy" /></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>

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

        <table class="features-table" id="mainTable" summary="list stars.">
            <caption><a href="${pageContext.request.contextPath}/galaxies/">GET ALL GALAIES</a></caption>
                <tr>
                    <td><h2>Id</h2></td>
                    <td><h2>Name</h2></td>
                    <td><h2>Distance</h2></td>
                    <td><h2>averageMass</h2></td>
                    <td><h2>averageAge</h2></td>
                    <td><h2>Date</h2></td>
                    <td><h2>Update</h2></td>
                    <td><h2>Delete</h2></td>
                </tr>
        </div>



        <script src="<c:url value="/resources/js/jquery-1.11.1.js" />"></script>
        <script src="<c:url value="/resources/js/date.js" />"></script>
        <script type="text/javascript">
            $(fillInFullTable);
            $(setCurrentDate);
                             
            function setCurrentDate(){
            
                    var currentDateString = (new Date()).toString('yyyy-MM-dd');
                    var elements = document.getElementsByClassName('dateInput');
                      
                    for(var i = 0; i < elements.length; i ++){
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

             stringHtml += "<tr><td class=\"grey\">" + galaxyId;
             stringHtml += "<td class=\"green\">"  + name;
             stringHtml += "<td class=\"green\">"  + distance;
             stringHtml += "<td class=\"green\">" + averageMass;
             stringHtml += "<td class=\"green\">" + averageAge;
             stringHtml += "<td class=\"green\">"  + date;
             stringHtml += "<td class=\"grey\">" + "<input type=\"button\" value=\"Update\" onClick=" + "\"fillInUpdateForm(" + galaxyId + "," + strName + "," + distance + ", " + strDate + ")\"" + ">";
             stringHtml += "<td class=\"grey\">" + "<form action=\"${pageContext.request.contextPath}/galaxies/removeGalaxy\" method=\"delete\">" +
                                                                 "<input type=\"text\" id=\"galaxyId\" name=\"galaxyId\" value=" + galaxyId + " hidden=\"true\">" +
                                                                 "<input type=\"submit\" value=\"remove\" ></form>";
             return stringHtml;
        }

        </script>
    </body>
</html>