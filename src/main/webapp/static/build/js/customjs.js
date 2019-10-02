//function changeState(id, active) {
//	if (active == 1){
//		var answer = window.confirm("Bạn có muốn kích hoạt tài khoản không ?");
//	}else{
//		var answer = window.confirm("Bạn có muốn khóa tài khoản không ?")
//	}
//	if (answer) {
//		$.ajax({
//			type : "GET",
//			url : "changeState/"+id+"/"+active,
//			success : function(data) {
//				if(active == 0){
//					$('#btn'+id).css('display', 'none');
//					$('#btnhidden'+id).css('display', 'block');
//				}
//				else{
//					$('#btn'+id).css('display', 'block');
//					$('#btnhidden'+id).css('display', 'none');
//				}
//			}
//		});
//	}
//};

var modalConfirm = function(callback){
  
  $("#btn27").on("click", function(){
    $("#popup").modal('show');
  });

  $("#modal_yes").on("click", function(){
    callback(true);
    $("#popup").modal('hide');
  });
  
  $("#modal_no").on("click", function(){
    callback(false);
    $("#popup").modal('hide');
  });
};

modalConfirm(function(confirm){
  if(confirm){
	  alert('YEs');
  }else{
	  alert('No');
  }
});


