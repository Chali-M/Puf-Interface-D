<%@page import="model.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.css">

<script src="Components/jquery-3.6.0.min.js"> </script> 

<script src="Components/Fund.js"></script>  

<script>

</script>
</head>

<body>

<div class="row">
    <div class = "col-6">
<h1>FUNDING DETAIS</h1>

<form id="formFund" name="formFund" method="post" action="Fund.jsp">

Customer Name: 
<input id="name" name="name" type="text" 
         class="form-control form-control-sm">
   
   <br> Institue Name: 
   
   <input id="institue" name="institue" type="text"
              class="form-control form-control-sm">
   
   <br> Position: 
   <input id="position" name="position" type="text" 
                class="form-control form-control-sm">
   
   <br> Project Name:
    <input id="projectname" name="projectname" type="text" class="form-control form-control-sm">
    
   
   <br> Description:
    <input id="description" name="description" type="text" class="form-control form-control-sm">
    
     <br> Funding type: 
   <input id="fundingtype" name="fundingtype" type="text" 
                class="form-control form-control-sm">
   
   <br> Amount:
    <input id="amount" name="amount" type="text" class="form-control form-control-sm">
    
     <br> Account Name: 
   <input id="accname" name="accname" type="text" 
                class="form-control form-control-sm">
   
   <br> Account Number:
    <input id="accnumber" name="accnumber" type="text" class="form-control form-control-sm">
    
     <br> Date:
    <input id="date" name="date" type="text" class="form-control form-control-sm">
    
    <br>
    <input id="btnSave" name="btnSave" type="button" value="Save"
                class="btn btn-primary">
    
    <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
    
</form>
</div>
</div>


<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id ="divItemsGrid">
			<%
			 Fund FundObj = new  Fund();
				out.print(FundObj.readItems());
			%>
</div>

		
		
		<br>


</body>
</html>