console.log("boardDetailCommnt Js 잘 연결되었나 콘솔 확인");
console.log('bnoVal 확인 : '+bnoVal);

let moreCount = 5;

//비동기로 cmtAddBtn을 클릭하면 bno,writer,content를 비동기로 DB에 넣기.

document.getElementById('cmtAddBtn').addEventListener('click',()=>{

    const writerVal = document.getElementById('cmtWriter').innerText;
    const contentVal = document.getElementById('cmtText').value;

    if(contentVal == null || contentVal == ''){
        alert('댓글을 입력해주세요.');
        document.getElementById('cmtText').focus();
        return false;
    }

    let cmtData = {

        bno : bnoVal,
        writer : writerVal,
        content : contentVal
    };

    console.log(cmtData);

    postCommentToServer(cmtData).then(result=>{
        
        if(result == '1'){
            alert('댓글이 정상적으로 등록이 완료되었습니다.');
            document.getElementById('cmtText').value = '';
            //댓글 뿌려야됌
            spreadCommentList(bnoVal);
        }

    })
    


})



async function postCommentToServer(cmtData){

    try {

        const url = '/comment/post';
        const config = {
            method : 'post',
            headers : {
                'content-type' : 'application/json; charset = utf-8'
            },
            body : JSON.stringify(cmtData)
        };
        
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;  

    } catch (error) {
        console.log(error);
    }
}

function spreadCommentList(bno){

    getCommentListFormServer(bnoVal).then(result=>{

        //만약 리스트보다 숫자가 적을경우 more버튼 활성화
        if(result.length>moreCount){
            document.getElementById('moreBtn').style.visibility = 'visible';
        }else{
            moreCount = result.length;
            document.getElementById('moreBtn').style.visibility = 'hidden';
        }

        console.log(result);
        const div = document.getElementById('cmtListArea');
        if(result.length > 0){
            div.innerHTML = ''; //초기화
            for(let i=0; i<moreCount; i++){
                let str = `<li class="list-group-item">`;
                str += `<div class="input-group mb-3">No.${result[i].cno}/`;
                str += `<div class="fw-bold">${result[i].writer} </div>`;
                str += ` : ${result[i].content}`;
                str += `</div> <span class="badge rounded-pill text-bg-warning">${result[i].regDate}</span>`;
                str += `<button type="button" data-cno="${result[i].cno}" class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                str += `<button type="button" data-cno="${result[i].cno}" class="btn btn-outline-danger btn-sm del">삭제</button>`;
                str += `</li>`;

                div.innerHTML += str;
            }
        }else{
            div.innerHTML = '등록된 댓글이 없습니다. 댓글을 등록해보세요~';
        }

    })
}

document.getElementById('moreBtn').addEventListener('click',()=>{

    moreCount += 5;
    spreadCommentList(bnoVal);

})

async function getCommentListFormServer(bno){
    try {

        const resp = await fetch('/comment/'+bno);
        const result = await resp.json();
        return result;
        
    } catch (error) {
        console.log(error);
    }
}

