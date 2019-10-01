<c:choose>
	<c:when test="${element.status==0}">
		<button class="btn-edit-group bg-start width-68" data-toggle="modal"
			disabled="disabled">
			<span><spring:message code="ui.common.start.text" text="Start" /></span>
		</button>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${element.campaignEnable==0}">
				<button class="btn-edit-group bg-start width-68" data-toggle="modal"
					name="${element.campaignEnable}" id="${element.campaignId}"
					onclick="activeQuestionCampaignCoupon(${element.campaignId},'${element.campaignName}',${element.campaignEnable});">
					<span><spring:message code="ui.common.start.text"
							text="Start" /></span>
				</button>
			</c:when>
			<c:otherwise>
				<button class="btn-edit-group bg-pause" data-toggle="modal"
					name="${element.campaignEnable}" id="${element.campaignId}"
					onclick="checkInActiveCampaignCoupon(${element.campaignId},'${element.campaignName}','${element.campaignEnable}');">
					<span><spring:message code="ui.common.Paused" text="Pause" /></span>
				</button>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

<script>
function activeQuestionCampaignCoupon(idCampagin,campaignName, enable)
{
	$('#add-comment-active-coupon').modal('show');
	idCampaginSave=idCampagin;
	campaignNameSave=campaignName;
	campainEnable = enable;
};

function checkInActiveCampaignCoupon(idspecial,namecampaign, enable) {
	$.ajax({
		type : "GET",
		url : "${contextPath}/store/smb/coupon/enable/"+idspecial+"/"+enable,			
		success : function(data) {
			if (Number(data) == 1) {
				var strhtml='<button type="button" class="close" data-dismiss="alert">';
			    strhtml +='<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="ui.common.close.text" text="Close"/></span></button>';
			    strhtml +='The coupon <strong class="color-blue">'+namecampaign+'</strong> has been stopped successfully.';
			
				$('#viewresult').html(strhtml);
			
				$(window).scrollTop($("#viewmanagercampaignall").offset().top);
				refeshSpecial(paging);
				}
		},
		error : function(xhr, textStatus, errorThrown) {
			console.log(textStatus);
		}
	});
};

function checkActiveCampaignCoupon() {
	$.ajax({
		type : "GET",
		url : "${contextPath}/store/smb/coupon/enable/"+idCampaginSave+"/"+campainEnable,			
		success : function(data) {
			if (Number(data) == 1) {
				var strhtml='<button type="button" class="close" data-dismiss="alert">';
			    strhtml +='<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="ui.common.close.text" text="Close"/></span></button>';
			    strhtml +='The coupon <strong class="color-blue">'+campaignNameSave+'</strong> has been started successfully.';
			
				$('#viewresult').html(strhtml);
			
				$(window).scrollTop($("#viewmanagercampaignall").offset().top);
				refeshSpecial(paging);
			}
		},
		error : function(xhr, textStatus, errorThrown) {
			console.log(textStatus);
		}
	});
};
</script>