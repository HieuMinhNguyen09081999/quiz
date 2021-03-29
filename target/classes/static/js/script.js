$(document).ready(function(){
	$('#subjectReal').change(function(event){
		$('#formReal').submit();
	});
	
	$('#subjectTest').change(function(event){
		$('#formTest').submit();
	});
});