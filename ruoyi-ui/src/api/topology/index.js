import request from '@/utils/request';
import getData from '@/utils'

let baseUrl = 'ws://192.168.0.109:8080';

export default class WebsocketLink {
    constructor(url, protol, { token, data }) {
        if (this.ws) {
            return this.ws;
        } else {
            this.ws = new WebSocket(`${baseUrl}${url}`, [protol]);
        }

        this.token = token;
        this.data = data;
    }

    init() {
        let connect = false;
        // 连接成功时触发
        this.ws.onopen = function () {
            // 发送数据
            if (this.ws.readyState === 1) {

                if (!connect) {
                    //第一次向服务端发送请求

                    this.ws.send(this.token)


                    connect = true;
                } else {
                    this.ws.send(JSON.stringify(this.data));
                }

            }
        }

        return this.ws;

        // // 连接失败时触发
        // this.ws.onerror = function () {
        //     alert("连接失败")
        // }

        // // 接收消息时触发
        // this.ws.onmessage = function (MessagEvent) {
        //     return new Promise((resolve, reject) => { })
        //     console.log(JSON.parse(MessagEvent.data));
        //     console.log(JSON.parse(JSON.parse(MessagEvent.data)));
        // }
        // // 连接关闭的回调函数
        // this.ws.onclose = function () {
        //     alert("close")
        // }
    }

    sendWs(data) {
        if (this.ws.readyState === 1) {
            this.ws.send(JSON.stringify(data)); // 向服务端发送请求
        }
    }
}

