import * as echarts from "echarts";
export default class Charts {
    /**
     * 构造函数
     * @param {String} title 标题
     * @param {String} containerId 容器ID
     */
    constructor(title, containerId, startWH) {
        //Charts只能被继承,不能被实例化
        if (new.target === Charts) {
            throw new Error("Charts 类不能被实例化");
        }
        this.title = title;
        this.container = document.getElementById(containerId);
        if (startWH) {
            this.myChart = echarts.init(this.container, null, startWH);
        } else {
            this.myChart = echarts.init(this.container);
        }

    }

    /**
     * 初始化
     */
    init(option) {
        option && this.myChart.setOption(option);
    }
}