import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
import App from './App.vue'
//axios可以用来发送 HTTP 请求并处理响应
import axios from "axios";
Vue.prototype.$axios=axios;
//设定PageMain调用的全局地址
Vue.prototype.$httpUrl='http://localhost:8090'
Vue.config.productionTip = false
Vue.use(ElementUI);
new Vue({
  render: h => h(App),
}).$mount('#app')
