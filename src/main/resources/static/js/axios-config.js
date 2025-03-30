// 添加本项目关于 axios 请求和响应的全局配置
// 声明后端服务的完整域名
const host = 'http://localhost:8080'
// 设置axios默认基地址为后端服务的地址
axios.defaults.baseURL = host

Vue.prototype.$http = axios

// 配置请求拦截器
axios.interceptors.request.use(function (config) {
    // 在每次请求执行前先设置一个共同的token请求头
    let loginUser = JSON.parse(sessionStorage.getItem('loginUser'))
    if (loginUser && loginUser.token) {
        // 将token令牌放入请求头
        config.headers.Authorization = loginUser.token
    }
    return config
}, function (error) {
    return Promise.reject(error)
})

// 配置响应拦截器
axios.interceptors.response.use(
  function (response) {
    // 响应状态码在2xx范围内的响应都会触发这个函数
    return response;
  },
  function (error) {
    // 响应状态码超出了2xx范围内的响应会触发这个函数
    if (error.response && error.response.status === 401) {
        $('#myModal').modal('show')
        return error.response.data;
    }
    return Promise.reject(error);
  }
)
