
jQuery("#subjectId").change(function(){
	sId=jQuery("#subjectId").val()
	getAllTargets(sId);
	console.log(sId);
})
	



function getAllTargets(sId){
	jQuery.ajax({
		url:"targets/"+sId,
		methos:"get",
		success:function(data){
			var targets=data.map.targets;
			//console.log(targets);
			var html="";
			var j=0;
			for(i in targets){
				j=j+1;
				html+="<option value='"+targets[i].id+"'>"+j+"、"+targets[i].content+"</option>"
			}
			jQuery("#targetSelect").html(html);
		}
	});
}
	