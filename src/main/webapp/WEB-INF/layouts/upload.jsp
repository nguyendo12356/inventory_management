<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload One File</title>
</head>
<body>     
    <h3>Upload One File:</h3>
 
    <!-- MyUploadForm -->
    <form:form modelAttribute="myUploadForm" method="POST"
                        action="uploadFile" enctype="multipart/form-data">
 	
        Description:
        <br>
        <form:input path="description" style="width:300px;"/>                
        <br/><br/>  
             
        File to upload: <form:input path="fileDatas" type="file"/><br />  
        
        <img alt="" src='<c:url value="/resources/images/loyalty.png"/>'> 
           
        <input type="submit" value="Upload">
         
    </form:form>
     
</body>
 
</html>