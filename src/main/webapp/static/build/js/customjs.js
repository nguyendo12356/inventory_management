function changeState(id, active) {
	$.ajax({
		type : "GET",
		url : "changeState/"+id+"/"+active,
		success : function(data) {
			if (data == 'SUCCESS') {
				alert('Hello');
				if(active == 0)
					alert('Trạng thái đã được kích hoạt');
				else
					alert('Trạng thái chưa được kích hoạt');
			}
		}
	});
};

