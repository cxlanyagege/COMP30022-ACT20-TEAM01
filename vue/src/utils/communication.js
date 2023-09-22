import axios from "axios";
// 基础地址
axios.defaults.baseURL = 'http://localhost:8080';

// 如果要访问'http://localhost:8080/api/getRequest', url = "/api/getRequest"就可以了

export const getAction = (url, params) => {
    return axios({
        url: url,
        method: 'get',
        params
    })
}

export const postAction = (url, data) => {
    return axios({
        url: url,
        method: 'post',
        data // since data are stored in the body
    })
}

export const putAction = (url, data) => {
    return axios({
        url: url,
        method: 'put',
        data
    })
}

export const deleteAction = (url, params) => {
    return axios({
        url: url,
        method: 'delete',
        params
    })
}