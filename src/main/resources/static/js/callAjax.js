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
		if (email.trim !== "") {
			$.ajax({
				type: "POST",
				url: "/changepassword/" + email + "",
				headers: {
					"Content-type": "application/json"
				},
				success: function() {
					let messPanel = document.getElementById("mess-panel");
					let mess = document.getElementById("mess");
					mess.innerHTML = "Mã xác nhận đã được gửi về email của bạn."
					messPanel.style.visibility = " visible ";
					setTimeout(function() {
						messPanel.style.visibility = " hidden ";
					}, 2000);

					$('#openModal').click();

					let time = 60;
					const countDown = document.getElementById('expireCode');
					var inter = setInterval(updateCountDown, 1000);
					function updateCountDown() {
						let seconds = time;
						var timeZone = `${seconds}`;
						console.log(time);
						if (time == 0) {
							$('#close').click();
							clearInterval(inter);
						}
						countDown.innerHTML = timeZone;
						time--;
					}
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
					console.log('change password success');
					swal("Bạn đã đổi mật khẩu thành công!")
						.then((value) => {
							$('#close').click();
						});
				}
			},
			statusCode: {
				400: function() {
					console.log('change password fail');
					let messPanel = document.getElementById("mess-panel");
					let mess = document.getElementById("mess");
					mess.innerHTML = "Đổi mật khẩu thất bại"
					messPanel.style.visibility = " visible ";
					setTimeout(function() {
						messPanel.style.visibility = " hidden ";
					}, 2000);
					$('#close').click();
				}
			}
		});
	});
});