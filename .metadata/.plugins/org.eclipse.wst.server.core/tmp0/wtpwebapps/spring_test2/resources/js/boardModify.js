console.log('Board Modify JavaScript in');

document.addEventListener('click',(e)=>{

    if(e.target.classList.contains('file-x')){

        const uuid = e.target.dataset.uuid;
        // console.log(uuid);

        sendBoardModifyToServer(uuid).then(result=>{
            if(result == '1'){
                alert('파일을 삭제하였습니다.');
                e.target.closest('li').remove();
                //파일 뿌리기
                spreadFileList(bnoVal);
            }
        })

    }

})


async function sendBoardModifyToServer(uuid){

    try {
        const url = '/board/fileRemove/'+uuid;
        const config = {
            method : 'delete'
        };

        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
        
    } catch (error) {
        console.log(error);
    }

}



