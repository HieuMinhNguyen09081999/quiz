function validate() {
    let subject = document.getElementById("subject").value;
    let question = document.getElementById("question").value;
    let answer_correct1 = document.getElementById("answer_correct1");
    let answer_correct2 = document.getElementById("answer_correct2");
    let answer_correct3 = document.getElementById("answer_correct3");
    let answer_correct4 = document.getElementById("answer_correct4");
    let answer_1 = document.getElementById("answer_1").value;
    let answer_2 = document.getElementById("answer_2").value;
    let answer_3 = document.getElementById("answer_3").value;
    let answer_4 = document.getElementById("answer_4").value;
    let level = document.getElementById("level").value;
    let messPanel = document.getElementById("mess-panel");
    let mess = document.getElementById("mess");
    var regex = /(<([^>]+)>)/ig;
	hasText = !!question.replace(regex, "").length;
    if (subject == 0) {
        mess.innerHTML = "Vui lòng chọn môn!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (!hasText) {
        mess.innerHTML = "Vui lòng nhập câu hỏi!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (answer_1.length == 0) {
        mess.innerHTML = "Vui lòng nhập đáp án!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (answer_2.length == 0) {
        mess.innerHTML = "Vui lòng nhập đáp án!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (answer_3.length == 0) {
        mess.innerHTML = "Vui lòng nhập đáp án!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (answer_4.length == 0) {
        mess.innerHTML = "Vui lòng nhập đáp án!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (answer_correct1.checked == false && answer_correct2.checked == false && answer_correct3.checked == false && answer_correct4.checked == false ) {
        mess.innerHTML = "Vui lòng chọn đáp án chính xác cho câu hỏi!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
    if (level == "") {
        mess.innerHTML = "Vui lòng chọn độ khó!"
        messPanel.style.visibility = " visible ";
        setTimeout(function () {
            messPanel.style.visibility = " hidden "; 
        }, 2000);
        return false;
    }
}