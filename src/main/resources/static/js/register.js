function signUp() {
    var fullName = document.getElementById("fullName").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var managerExam = document.getElementById("managerExam");
    var student = document.getElementById("student");
    var rePassword = document.getElementById("repassword").value;
    var messPanel = document.getElementById("mess-panel");
    var mess = document.getElementById("mess");
    var login = document.getElementById("login");
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (fullName.length == 0) {
        mess.innerHTML = "Vui lòng nhập họ tên!";
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (email.length == 0) {
        mess.innerHTML = "Vui lòng nhập email!";
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (password.length == 0) {
        mess.innerHTML = "Vui lòng nhập mật khẩu!";
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (managerExam.checked == false  && student.checked == false) {
    	mess.innerHTML = "Vui lòng chọn quyền!";
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (fullName.length > 50) {
        mess.innerHTML = "Họ tên không vượt quá 50 kí tự. Vui lòng nhập lại!";
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (email.length > 50) {
        mess.innerHTML = "Email không vượt quá 50 kí tự. Vui lòng nhập lại!";
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    } 
	if (password.length < 8 || password.length > 50) {
        mess.innerHTML = "Mật khẩu phải từ 8 đến 50 kí tự. Vui lòng nhập lại!";
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    } 
	if(password !== rePassword) {
        mess.innerHTML = "Mật khẩu không giống nhau. Vui lòng nhập lại!";
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
}