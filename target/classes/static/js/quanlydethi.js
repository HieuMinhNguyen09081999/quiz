function validate() {
    let subject = document.getElementById("subject").value;
    let duration = document.getElementById("duration").value;
    let messPanel = document.getElementById("mess-panel");
    let mess = document.getElementById("mess");
    if (subject == 0) {
        mess.innerHTML = "Vui lòng chọn môn!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (duration.length == 0) {
        mess.innerHTML = "Vui lòng nhập thời gian làm bài!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
}