import Charts from "../index.js";
export default class Rose extends Charts {
    /**
     * 构造函数
     * @param {String} title 标题
     * @param {String} containerId 容器ID
     * @param {Object} startWH 初始宽高
     */
    constructor(title, containerId, startWH) {
        super(title, containerId, startWH);
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
            series: [
                {
                    type: 'pie',
                    data: [
                        {
                            value: 100,
                            name: 'R2L'
                        },
                        {
                            value: 200,
                            name: 'U2R'
                        },
                        {
                            value: 300,
                            name: 'Prode'
                        },
                        {
                            value: 400,
                            name: 'DDos'
                        },
                        {
                            value: 500,
                            name: 'normal'
                        }
                    ],
                    roseType: 'area'
                }
            ]
        }
        super.init(option);
    }
}