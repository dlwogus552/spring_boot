async function addReply(replyObj){
    const response = await axios.post(`/replies/`,replyObj)
    return response.data
}


async function getList({bno,page,size,goLast}){
    const result = await axios.get(`/replies/list/${bno}`,{params:{page,size}})

    if(goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({bno:bno, page:lastPage,size:size})
    }
    return  result.data
}

async function deleteReply(rno){
    const response = await  axios.delete(`/replies/${rno}`,rno)
    return response.data
}