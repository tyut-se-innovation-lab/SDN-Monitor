import Charts from "../index.js";
export default class Line extends Charts {
    /**
     * 构造函数
     * @param {String} title 标题
     * @param {String} containerId 容器ID
     * @param {Array} data 数据
     */
    constructor(title, containerId, data) {
        console.log(666);
        super(title, containerId);
        this.data = data;

        console.log(this.title);
    }

    /**
     * 初始化
     */
    init() {
        let option = {
            title: {
                text: this.title
            },
            legend: {
                data: [],
            },
            // tooltip: {
            //     trigger: "axis"
            // },
            xAxis: {
                type: 'time',
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    showSymbol: false,
                    type: 'line',
                    data: this.data
                }
            ]
        }
        console.log(this.data);
        super.init(option);
    }
}