
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
