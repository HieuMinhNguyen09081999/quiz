function signIp() {
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let messPanel = document.getElementById("mess-panel");
    let mess = document.getElementById("mess");
    let login = document.getElementById("login");
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (email.length == 0) {
        mess.innerHTML = "Vui lòng nhập email!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (!re.test(email)) {
        mess.innerHTML = "Email không đúng định dạng. Vui lòng nhập lại!"
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
    if (email.length > 50) {
        mess.innerHTML = "Email không vượt quá 50 kí tự. Vui lòng nhập lại!"
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
}