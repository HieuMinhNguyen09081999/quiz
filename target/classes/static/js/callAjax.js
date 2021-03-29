$(document).ready(function() {
	$('#subject').change(function(event) {
		subject = $('#subject').val();
		$.get("http://localhost:8080/getQuestion/" + subject, function(result) {
			$('#listQuestion').empty();
			$.each(result, function(i, question) {
				console.log(question);
				var q = "<option value=''" + question.questionId + "''>" + question.questionCode + "-" +
					question.question + "</option>";
				$('#listQuestion').append(q);
			});
		});
	});
});