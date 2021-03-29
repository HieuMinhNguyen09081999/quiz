function validate() {
    let fullName = document.getElementById("fullName").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let admin = document.getElementById("admin").value;
    let managerExam = document.getElementById("managerExam").value;
    let student = document.getElementById("student").value;
    let messPanel = document.getElementById("mess-panel");
    let mess = document.getElementById("mess");
    if (fullName == 0) {
        mess.innerHTML = "Vui lòng nhập họ tên!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (email.length == 0) {
        mess.innerHTML = "Vui lòng nhập email!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (password.length == 0) {
        mess.innerHTML = "Vui lòng nhập mật khẩu!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (admin.checked== false && managerExam.checked == false  && student.checked == false) {
    	mess.innerHTML = "Vui lòng chọn quyền!";
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (password.length < 8 || password.length > 50) {
        mess.innerHTML = "Mật khẩu phải từ 8 đến 50 kí tự. Vui lòng nhập lại!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
}