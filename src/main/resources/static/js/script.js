$(document).ready(function() {
	$('#subjectReal').change(function(event) {
		$('#formReal').submit();
	});

	$('#subjectTest').change(function(event) {
		$('#formTest').submit();
	});
	$('.chat_icon').click(function(event){
			$('.chat_box').toggleClass('active');
	});
	$.getJSON("/chat-box/demo-data/simple-data.json", function(dataJSON) {
				$('#flowchat').flowchat({
					dataJSON: dataJSON
				});
	});
});