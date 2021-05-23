$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 

// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
   url : "FundingAPI", 
   type : type, 
   data : $("#formItem").serialize(), 
   dataType : "text", 
   complete : function(response, status) 
{ 
   onItemSaveComplete(response.responseText, status); 
} }); 
});

function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
   var resultSet = JSON.parse(response); 
   if (resultSet.status.trim() == "success") 
 { 
   $("#alertSuccess").text("Successfully saved."); 
   $("#alertSuccess").show(); 
   $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
    $("#alertError").text(resultSet.data); 
    $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
    $("#alertError").text("Error while saving."); 
    $("#alertError").show(); 
 } else
 { 
   $("#alertError").text("Unknown error while saving.."); 
   $("#alertError").show(); 
 } 
   $("#hidItemIDSave").val(""); 
   $("#formItem")[0].reset(); 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidItemIDSave").val($(this).data("id"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#insition").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#position").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#projectname").val($(this).closest("tr").find('td:eq(3)').text());
 $("#description").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#fundingtype").val($(this).closest("tr").find('td:eq(5)').text()); 
 $("#amount").val($(this).closest("tr").find('td:eq(6)').text()); 
 $("#accname").val($(this).closest("tr").find('td:eq(7)').text());
 $("#accnumber").val($(this).closest("tr").find('td:eq(8)').text()); 
 $("#date").val($(this).closest("tr").find('td:eq(9)').text()); 

}); 

//remove
$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "FundingAPI", 
		 type : "DELETE", 
		 data : "id=" + $(this).data("id"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onItemDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});


function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
    $("#alertSuccess").text("Successfully deleted."); 
    $("#alertSuccess").show(); 
    $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
    $("#alertError").text(resultSet.data); 
    $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
    $("#alertError").text("Error while deleting."); 
    $("#alertError").show(); 
 } else
 { 
     $("#alertError").text("Unknown error while deleting.."); 
     $("#alertError").show(); 
 } 
}
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
	
// name
if ($("#name").val().trim() == "") 
 { 
 return "Insert Item name."; 
 } 

// insititue-----
if ($("#institue").val().trim() == "") 
 { 
 return "Insert Item institue."; 
 }

// position-------------------------------
if ($("#position").val().trim() == "") 
{ 
return "Insert Item position."; 
}

// project name.........
if ($("#projectname").val().trim() == "") 
{ 
return "Insert Item Projectname."; 
}
// description.........
if ($("#description").val().trim() == "") 
{ 
return "Insert Item description."; 
}
//funding type.........
if ($("#fundingtype").val().trim() == "") 
{ 
return "Insert Item fundingtype."; 
}
//amount.........
if ($("#amount").val().trim() == "") 
{ 
return "Insert fund amount."; 
}
//is numerical value
var tmpPrice = $("#amount").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
{ 
return "Insert a numerical value for amount."; 
} 
//convert to decimal price
$("#amount").val(parseFloat(tmpPrice).toFixed(2)); 

//account number------------------------
if ($("#accname").val().trim() == "") 
{ 
return "Insert Item accname."; 
}
//.........account number
if ($("#accnumber").val().trim() == "") 
{ 
return "Insert Item number."; 
}
//date.........
if ($("#date").val().trim() == "") 
{ 
return "Insert Item date."; 
}

return true; 
}