
// 1) 회원가입 아이디 검사
const signupId = document.getElementById("signupID");
var sucId = 0;
signupId.addEventListener("change", function(){

    const regExp =/^[a-z]{1}[a-zA-Z0-9]{5,13}$/;

    if( !regExp.test(this.value)){
        this.style.backgroundColor = "red";
        this.style.color="white";
        signupId.value = "";
        signupId.focus();
        sucId = 0;
    }else{
        this.style.backgroundColor = "green";
        this.style.color="white";
        sucId = 1;
    }
});


// 2) 회원가입 비밀번호 검사
const pw = document.getElementById("signupPW");
const checkPw = document.getElementById("signupCheckPw");
const pwMsg = document.getElementById("pwMsg");
var sucPw = 0;


//  2-1) 비밀번호 확인 먼저 입력 검사
checkPw.addEventListener("keyup", function() {

    if(pw.value.length == 0) {
        this.value = "";
        alert("비밀번호를 입력해주세요");
        pw.focus();
        checkObj.pw = false;
    }
});



/*
pw.addEventListener("change", function() {
    if( (pw.value == checkPw.value) && pw.value.length != 0 ) {
        pwMsg.innerText = "비밀번호 일치";
        pwMsg.classList.add("confirm");
        pwMsg.classList.remove("error");
        checkObj.pw = true;
        checkObj.checkPw = true;
    }else {
        pwMsg.innerText="비밀번호 불일치";
        pwMsg.classList.add("error");
        pwMsg.classList.remove("confirm");
        checkObj.checkPw = false;
    }
});
*/

// 2-2) 비밀번호랑 확인이 일치한지 확인
checkPw.addEventListener("change", function() {
    console.log(pwMsg);
    if( (pw.value == checkPw.value) && pw.value.length != 0 ) {
        pwMsg.innerText = "비밀번호 일치";
        console.log(pwMsg);
        pwMsg.classList.add("confirm");
        pwMsg.classList.remove("error");
        //checkObj.pw = true;
        //checkObj.checkPw = true;
        sucPw = 1;
        
    }else {
        pwMsg.innerText="비밀번호 불일치";
        pwMsg.classList.add("error");
        pwMsg.classList.remove("confirm");


        pw.value = "";
        pw.focus();
        checkPw.value = "";
       
        
        //checkObj.checkPw = false;
        sucPw = 0;
    }
});



// 3) 닉네임 검사
const nickName = document.getElementById("signupNick");
const nickMsg = document.getElementById("nickMsg");
var sucNick = 0;
nickName.addEventListener("change", function(){
    const nickReg = /^[가-힣]{2,5}$/;

    if( nickReg.test(this.value)){
        nickMsg.innerText = "정상입력"
        nickMsg.classList.add("confirm");
        nickMsg.classList.remove("error");
        sucNick = 1;
    }else{
        nickMsg.innerText = "한글만 입력하세요"
        nickMsg.classList.add("error");
        nickMsg.classList.remove("confirm");

        nickName.value = "";
        nickName.focus();
        
        sucNick = 0;
    }

});




// 4) 모든 유효성 검사를 통과했을 때, 회원가입 
/*
const signupBtn = document.getElementById("signupBtn");
signupBtn.addEventListener("click", function(){

    if( sucId == 1 && sucPw == 1 && sucNick == 1){
          sucAll = 1;
       
     }else{
         sucAll = 0;
     }
 })

*/


// const signupBtn = document.getElementById("signupBtn");
// const container = document.getElementById("signupEx");
// signupBtn.addEventListener("mouseover", function(){
//     if(sucId == 1 && sucPw == 1 && sucNick == 1){
//        signupBtn.disabled = true;
//  	}else{
// 	   signupBtn.disabled = false;
// 	}
// });




const signupButton = document.getElementById("signupBtn");

signupButton.addEventListener("click", function(event) {

	if(sucId == 0 || sucPw == 0 || sucNick ==0){
   	 	event.preventDefault();
   	 	alert(sucId+sucPw+sucNick);

	} 

});

// const $form = document.querySelector('form');

// $form.addEventListener("submit", (event) => {
//   // 동작(이벤트)을 실행하지 못하게 막는 메서드입니다.

//   if(sucId== 0){
//     event.preventDefault();

//     alert('전송 막기 성공!');

//   }

// });

