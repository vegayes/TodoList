document.getElementById("signupID").addEventListener("change", function(){

    const regExp =/^[a-z]{1}[a-zA-Z0-9]{5,13}$/;

    if( !regExp.test(this.value)){
        this.style.backgroundColor = "red";
        this.style.color="white";
    }else{
        this.style.backgroundColor = "green";
        this.style.color="white";
    }
});


/*
비밀번호, 비밀번호 확인 : 키보드가 올라올 때
- "비밀번호" 를 미입력한 상태에서 "비밀번호 확인"을 작성할 경우
"비밀번호 확인"에 작성된 내용을 모두 삭제하고
'비밀번호를 입력해주세요' 경고창 출력
*/

const Pw = document.getElementById("signupPW");
const checkPw = document.getElementById("signupCheckPW");

checkPw.addEventListener("keyup", function() {

    if(Pw.value.length == 0) {
        this.value = "";
        alert("비밀번호를 입력해주세요");
        Pw.focus();
        checkObj.Pw = false;
    }
});






