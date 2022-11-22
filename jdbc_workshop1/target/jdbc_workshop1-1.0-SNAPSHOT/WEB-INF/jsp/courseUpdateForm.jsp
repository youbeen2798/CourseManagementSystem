<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="var" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>강의 수정 페이지</h1>
<h3>바꾸려는 강의</h3>
<table border = "1">
    <thead>
    <tr>
        <th>강의 번호</th>
        <th>강의 이름</th>
        <th>강의 교수</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${course.id}</td>
        <td>${course.subject.name}</td>
        <td>${course.teacher.name}</td>
    </tr>
    </tbody>
</table>
<br/>
<h3>강의 수정하기(강사님, 과목 수정 가능)</h3>
<form method = "post" action = "/course/update/${courseId}">
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
