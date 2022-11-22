<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="var" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border = "1">
    <thead>
        <tr>
            <th>강의 번호</th>
            <th>강의 이름</th>
            <th>강의 교수</th>
            <th>강의 수정하기</th>
            <th>강의 삭제하기</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="course" items = "${courses}">
            <tr>
                <td>${course.id}</td>
                <td>${course.subject.name}</td>
                <td>${course.teacher.name}</td>
                <td><a href = "/course/update/${course.getId()}"}>강의 수정하기 버튼</a></td>
                <td><a href = "/course/delete/${course.getId()}">강의 삭제하기 버튼</a></td>
            </tr>

        </c:forEach>
    </tbody>
</table>

<br/><br/>
<h1>강의 등록하기</h1>
    <form method = "post" action = "/course/insert">
        강사님 이름 :
        <select name = "teacherId">
            <c:forEach var="teacher" items = "${teachers}">
                <option value = " ${teacher.id}">${teacher.name}</option>
            </c:forEach>
        </select>
        과목 이름 :
        <select name = "subjectId">
            <c:forEach var = "subject" items = "${subjects}">
                <option value = "${subject.id}"> ${subject.name}</option>
            </c:forEach>
        </select>
        <input type = "submit" value = "등록완료"/>
    </form>
</body>
</html>
