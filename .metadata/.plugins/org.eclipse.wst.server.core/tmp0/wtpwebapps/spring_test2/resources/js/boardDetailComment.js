// console.log("boardDetailCommnt Js 잘 연결되었나 콘솔 확인");
// console.log('bnoVal 확인 : '+bnoVal);
console.log(nickNameVal +"닉네임 확인");

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

async function getCommentListFormServer(bno, page){
    try {
        
        const resp = await fetch('/comment/'+bno+'/'+page);
        const result = await resp.json();
        return result;
        
    } catch (error) {
        console.log(error);
    }
}

//들어오는 매개변수가 없다면(예를들어 처음들어왔을때 page의 매개변수가 없을때)처음 page의 매개변수값이 1이된다.
function spreadCommentList(bno, page=1){

    getCommentListFormServer(bnoVal, page).then(result=>{

        // console.log(result);
        const div = document.getElementById('cmtListArea');
        if(result.cmtList.length > 0){
            //   div.innerHTML = ''; //초기화

            //1페이지면 5개만 뿌리기
            //1page이상이면 1page랑 2page랑 같이 뿌릴거
            if(page==1){
                div.innerHTML = '';
            }
            
            for(let cvo of result.cmtList){


                let str = `<li class="list-group-item" data-cno="${cvo.cno}">`;
                str += `<div class="input-group mb-3">No.${cvo.cno}/`;
                str += `<div class="fw-bold">${cvo.writer}:</div>`;
                str += `${cvo.content}`;
                str += `</div> <span class="badge rounded-pill text-bg-warning">${cvo.regDate}</span>`;
                if(nickNameVal == cvo.writer){
                    str += `<button type="button" class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                    str += `<button type="button" data-cno=${cvo.cno} class="btn btn-outline-danger btn-sm del">삭제</button>`;
                }
                str += `</li>`;

                div.innerHTML += str;
            }

            //더보기 버튼 코드
            let moreBtn = document.getElementById('moreBtn');
            // console.log(moreBtn);
            //moreBtn 표시되는 조건
            // pgvo.pageNo = 1 / realEndPage = 3
            //realEndPage보다 현재 내 페이지가 작으면 표시
            if(result.pgvo.pageNo < result.realEndPage){
                //style.visibility = 'visible' 표시 / style.visibility = 'hidden';
                moreBtn.style.visibility = 'visible'; //버튼 표시
                moreBtn.dataset.page = page+1; //한페이지 늘린다.
            }else{
                moreBtn.style.visibility = 'hidden';
            }
            

        }else{
            div.innerHTML = '등록된 댓글이 없습니다. 댓글을 등록해보세요~';
        }

    })
}


document.addEventListener('click',(e)=>{

    if(e.target.id == 'moreBtn'){
        let page = parseInt(e.target.dataset.page);
        spreadCommentList(bnoVal,page);
        return;
    }

    if(e.target.classList.contains('mod')){
        console.log('수정버튼 클릭');
        //내가 수정버튼을 누른 댓글의 li
        let li = e.target.closest('li');
        //nextSibling : 한 부모 안에서 다음 형제를 찾기
        let cmtText = li.querySelector('.fw-bold').nextSibling;
        // console.log(cmtText);
        document.getElementById('cmtTextMod').value = cmtText.nodeValue;

        //수정 => cno dataset으로 달기 cno, content
        document.getElementById('cmtModBtn').setAttribute("data-cno",li.dataset.cno);
        return;
    }

    if(e.target.id == 'cmtModBtn'){
        let cmtModData = {
            cno : e.target.dataset.cno,
            content : document.getElementById('cmtTextMod').value,
        };

        console.log(cmtModData);

        //비동기로 보내기
        modifyCommentToServer(cmtModData).then(result=>{

            if(result == '1'){
                alert('댓글 수정이 완료되었습니다.');
                //모달창 닫기
                document.querySelector('.btn-close').click();
            }else{
                alert('수정이 실패하였습니다.');
                document.querySelector('.btn-close').click();
                
            }
            //댓글 새로 뿌리기
            spreadCommentList(bnoVal);
        })
    }

    if(e.target.classList.contains('del')){
        const cnoVal = e.target.dataset.cno;
        console.log(cnoVal);

        const cmtData = {
            cno : cnoVal,
            bno : bnoVal
        };

        removeCommentToServer(cmtData).then(result=>{
            if(result == '1'){
                alert('삭제가 완료되었습니다.');
                spreadCommentList(bnoVal);
                return;
            }
        })
        
    }

})


//수정시 모달창을 통해 댓글 입력받기

async function modifyCommentToServer(cmtData){

    try {
        const url = '/comment/modify';
        const config = {
            method : 'put',
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


//댓글 삭제 서버로 요청
async function removeCommentToServer(cmtData){

    try {
        const url = '/comment/remove';
        config = {
            method : 'delete',
            headers : {
                'content-type' : 'application/json; charset = utf-8'
            },
            body : JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }

}


