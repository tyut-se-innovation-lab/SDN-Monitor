import Charts from "../index.js";
export default class Line extends Charts {
    /**
     * 构造函数
     * @param {String} title 标题
     * @param {String} containerId 容器ID
     * @param {Object} data 数据
     */
    constructor(title, containerId, startWH) {
        super(title, containerId, startWH);
    }

    /**
     * 初始化
     */
    init(data) {
        let option = {
            title: {
                text: this.title
            },
            legend: {
                data: [],
            },
            tooltip: {
                trigger: "axis"
            },
            xAxis: {
                type: 'time',
            },
            yAxis: {
                type: 'value'
            },
            series: this.initOptions(data)
        }
        super.init(option);
    }

    initOptions(data) {
        let keys = Object.keys(data);
        let arr = ["输入", "输出"];
        let n = 0;
        return keys.map(value => {
            let obj = {
                name: arr[n],
                showSymbol: false,
                type: 'line',
                emphasis: {
                    focus: 'series'
                },
                data: data[value]
            }
            n++;
            return obj;
        })
    }
}