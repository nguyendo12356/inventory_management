function changeState(id, active) {
	alert($(this).find("span").id);
	$(this).find("span").html('Hello');
	$.ajax({
		type : "GET",
		url : "changeState/"+id+"/"+active,
		success : function(data) {
			if(active == 0){
				alert('Tài khoản đã được kích hoạt');
			}
			else{
				alert('Tài khoản đã được khóa');
			}
		}
	});
};

