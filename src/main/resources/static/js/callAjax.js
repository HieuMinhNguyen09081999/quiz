$(document).ready(function() {
	$('#subject').change(function(event) {
		subject = $('#subject').val();
		$.get("http://localhost:8080/getQuestion/" + subject, function(result) {
			$('#listQuestion').empty();
			$.each(result, function(i, question) {
				console.log(question);
				var q = "<option value='" + question.questionCode + "'>" + question.questionCode + "-" +
					question.question + "</option>";
				$('#listQuestion').append(q);
			});
		});
	});


	$('#sendCode').click(function(event) {
		email = $('#email').val();
		console.log(email.length);
		if (email.trim !== "") {
			$.ajax({
				type: "POST",
				url: "/changepassword/" + email + "",
				headers: {
					"Content-type": "application/json"
				},
				success: function() {
					console.log('success');
					let messPanel = document.getElementById("mess-panel");
					let mess = document.getElementById("mess");
					mess.innerHTML = "Mã xác nhận đã được gửi về email của bạn."
					messPanel.style.visibility = " visible ";
					setTimeout(function() {
						messPanel.style.visibility = " hidden ";
						}, 2000);
					$('#openModal').click();
				}
			});
		}
	});

	$('#changePassword').click(function(event) {
		email = $('#email').val();
		code = $('#code').val();
		password = $('#password').val();
		$.ajax({
			type: "POST",
			url: "/fortgotpassword?email=" + email + "&password=" + password + "&code=" + code + "",
			headers: {
				"Content-type": "application/json"
			},
			success: function(data, textStatus, xhr) {
				if (xhr.status == '200') {

					swal("Good job!", "You clicked the button!", "success");
				} else {
					console.log('4000');
				}
			}
		});
	});
});