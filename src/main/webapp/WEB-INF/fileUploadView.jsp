<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.google.api.services.drive.model.File" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<jsp:useBean id="driveFormModel" type="com.driveImageUpload.model.DriveFormModel" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Google Drive File Upload Example</title>
</head>

<body>
<form:form method="POST" action="/post-file.html" enctype="multipart/form-data">
    <div>
        Name: <input type="file" name="file"/>
    </div>

    <div>
        <input type="submit" value="Add"/>
    </div>
</form:form>
<div>
    <ol>
        <% for (File file : driveFormModel.getFiles()) { %>
        <li><%= file.getName() %>
        </li>
        <% } %>
    </ol>
</div>
</body>
</html>