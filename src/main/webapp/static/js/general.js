
function isEmpty(content){
	return content.length == 0;
}

function addNewLine(func){
	var newLine = "<tr><td>1</td>" +
			"<td>"+arguments[2]+"</td>" +
			"<td>"+arguments[3]+"</td>" +
			"<td>"+arguments[4]+"</td>" +
			"</tr>";
	var id = "#"+arguments[0];
	$(id+" tbody").append(newLine);
}

function paginationEvent(isNext, contextPath){
	let pageSplit = document.URL.split("/");
	let pageNumber;
	if(isNext){
		pageNumber = Number(pageSplit[6]) + 1;
	}else{
		pageNumber = Number(pageSplit[6]) - 1;	
	}
	let url = contextPath+"/"+pageSplit[4]+"/"+pageSplit[5]+"/"+pageNumber+"/"+pageSplit[7];
	window.location.href = url;
}

function disabledNextAndPreviousButton(num){
	let pageSplit = document.URL.split("/");
	let pageNumber = Number(pageSplit[6]);
	if (pageNumber == 1){
		$('#previousButton').removeAttr('onclick');
		$('#previousButton').css('opacity','0.5');
		$('#previousButton').css('cursor','not-allowed');
	}else if (pageNumber == parseInt(num)){
		$('#nextButton').removeAttr('onclick');
		$('#nextButton').css('opacity','0.5');
		$('#nextButton').css('cursor','not-allowed');
	}
	$('#page-'+pageSplit[6]).parent().addClass("active");
	
}


