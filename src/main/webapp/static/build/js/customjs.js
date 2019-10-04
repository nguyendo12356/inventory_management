function changeState(id, active) {
	if (active == 1) {
		$('#p-content').html("Bạn có muốn kích hoạt tài khoản không ?");
	} else {
		$('#p-content').html("Bạn có muốn khóa tài khoản không ?");
	}

	$("#popup").modal('show');

	$("#modal_yes").on("click", function() {
		$("#popup").modal('hide');
		$.ajax({
			type : "GET",
			url : "changeState/" + id + "/" + active,
			success : function(data) {
				if (active == 0) {
					$('#btn' + id).css('display', 'none');
					$('#btnhidden' + id).css('display', 'block');
				} else {
					$('#btn' + id).css('display', 'block');
					$('#btnhidden' + id).css('display', 'none');
				}
			}
		});
	});

	$("#modal_no").on("click", function() {
		$("#popup").modal('hide');
	});
};


//var modalConfirm = function(callback){
//	
//	$('#p-content').html("Bạn có muốn xóa tài khoản không ?");
//	
//	  $("#btnDel"+id).on("click", function(){
//	    $("#popup").modal('show');
//	  });
//
//	  $("#modal_yes").on("click", function(){
//	    callback(true);
//	    $("#popup").modal('hide');
//	  });
//	  
//	  $("#modal_no").on("click", function(){
//	    callback(false);
//	    $("#popup").modal('hide');
//	  });
//	};
//
//	modalConfirm(function(confirm){
//	});
