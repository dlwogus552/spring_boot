async function addReply(replyObj){
    const response = await axios.post(`/replies/`,replyObj)
    return response.data
}


async function getList({rno,page,size,goLast}){
// async function getList(rno){
    const result = await axios.get(`/replies/list/${rno}`,{params:{page,size}})
    // const result = await axios.get(`/replies/list/${rno}`,rno)
    if(goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({rno:rno, page:lastPage,size:size})
    }
    return  result.data
}

async function deleteReply(rrno){
    const response = await  axios.delete(`/replies/${rrno}`,rrno)
    return response.data
}