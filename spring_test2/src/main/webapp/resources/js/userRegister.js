// console.log('User Register in');

document.getElementById('doubleCheck').addEventListener('click',()=>{

    
    const email = document.getElementById('e').value;

    if(email.length == 0){
        alert('이메일을 입력해주세요.');
        return;
    }
    
    console.log('이메일 확인 : ' + email);

    sendUserToServer(email).then(result=>{

        console.log('result 값 : ' + result);

        if(result == '0'){
            alert(email+' 은중복되었습니다. 다른아이디를 사용해주세요.');
            document.getElementById('e').value = '';
            document.getElementById('e').focus();
            document.getElementById('joinBtn').disabled = true;
            return;
        }
        
        alert(email+'은 사용가능한 아이디입니다.');
        document.getElementById('joinBtn').disabled = false;
    })

})

async function sendUserToServer(email){

    try {
        const url = '/user/doubleCheck/'+email;
        const config = {
            method : 'get'
        };
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }

}