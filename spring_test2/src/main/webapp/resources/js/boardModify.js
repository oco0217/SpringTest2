console.log('Board Modify JavaScript in');

document.addEventListener('click',(e)=>{

    if(e.target.classList.contains('file-x')){

        const uuid = e.target.dataset.uuid;
        const bnoVal = e.target.dataset.bno;
        console.log(uuid);
        console.log(bnoVal);
        const fileData = {
            uuid : uuid,
            bno : bnoVal
        };

        sendBoardModifyToServer(fileData).then(result=>{
            if(result == '1'){
                alert('파일을 삭제하였습니다.');
                e.target.closest('li').remove();
            }
        })

    }

})


async function sendBoardModifyToServer(fileData){

    try {
        const url = '/board/fileRemove';
        const config = {
            method : 'delete',
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(fileData)
        };

        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
        
    } catch (error) {
        console.log(error);
    }

}



