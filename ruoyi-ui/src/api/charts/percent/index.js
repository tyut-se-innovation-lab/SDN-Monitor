import * as echarts from "echarts";
import 'echarts-liquidfill';
export default class PercentCircle {
    /**
     * 构造函数
     * @param {String} title 标题
     * @param {String} containerId 容器ID
     * @param {Array} data 数据
     */
    constructor({ title, containerId, data }) {
        // console.log(title, containerId, data)
        this.title = title;
        this.container = document.getElementById(containerId);
        this.data = data;
    }
    Pie() {
        let dataArr = [];
        for (var i = 0; i < 150; i++) {
            if (i % 2 === 0) {
                dataArr.push({
                    name: (i + 1).toString(),
                    value: 50,
                    itemStyle: {
                        normal: {
                            color: '#00AFFF',
                            borderWidth: 0,
                            borderColor: 'rgba(0,0,0,0)',
                        },
                    },
                });
            } else {
                dataArr.push({
                    name: (i + 1).toString(),
                    value: 100,
                    itemStyle: {
                        normal: {
                            color: 'rgba(0,0,0,0)',
                            borderWidth: 0,
                            borderColor: 'rgba(0,0,0,0)',
                        },
                    },
                });
            }
        }
        return dataArr;
    }

    /**
     * 初始化
     */
    init(value) {
        let option = {
            backgroundColor: 'rgba(255, 255, 255)',
            // title: {
            //   text: '',
            //   textStyle: {
            //     fontWeight: 'normal',
            //     fontSize: 25,
            //     color: 'rgb(97, 142, 205)'
            //   }
            // },
            series: [
                {
                    // value: 50, //  内容 配合formatter
                    type: 'liquidFill',
                    radius: '70%', // 控制中间圆球的尺寸（此处可以理解为距离外圈圆的距离控制）
                    center: ['50%', '50%'],
                    data: [
                        0.4,
                        {
                            value: 0.4,
                            direction: 'left', //波浪方向
                        },
                    ], // data个数代表波浪数
                    backgroundStyle: {
                        borderWidth: 1,
                        color: 'rgba(62, 208, 255, 1)', // 球体本景色
                    },
                    amplitude: '6  %', //波浪的振幅
                    // 修改波浪颜色
                    // color: ['#0286ea', 'l#0b99ff'], // 每个波浪不同颜色，颜色数组长度为对应的波浪个数
                    color: [
                        {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [
                                {
                                    offset: 1,
                                    color: '#6CDEFC',
                                },
                                {
                                    offset: 0,
                                    color: '#429BF7',
                                },
                            ],
                            globalCoord: false,
                        },
                    ],
                    label: {
                        normal: {
                            // formatter: 0.87 * 100 + '\n%',
                            formatter: 0.4 * 100 + '\n{d|%}',
                            //  formatter: function(params){
                            //     return params.value* 100 + " \n%";
                            // },
                            rich: {
                                d: {
                                    fontSize: 25,
                                },
                            },
                            textStyle: {
                                fontSize: 36,
                                color: '#fff',
                            },
                        },
                    },
                    outline: {
                        show: false,
                    },
                },
                {
                    type: 'pie',
                    z: 1,
                    center: ['50%', '50%'],
                    radius: ['72%', '73.5%'], // 控制外圈圆的粗细
                    hoverAnimation: false,
                    data: [
                        {
                            name: '',
                            value: 500,
                            labelLine: {
                                show: false,
                            },
                            itemStyle: {
                                color: '#00AFFF',
                            },
                            emphasis: {
                                labelLine: {
                                    show: false,
                                },
                                itemStyle: {
                                    color: '#00AFFF',
                                },
                            },
                        },
                    ],
                },
                {
                    //外发光
                    type: 'pie',
                    z: 1,
                    zlevel: -1,
                    radius: ['70%', '90.5%'],
                    center: ['50%', '50%'],
                    hoverAnimation: false,
                    clockWise: false,
                    itemStyle: {
                        normal: {
                            borderWidth: 20,
                            color: 'rgba(224,242,255,1)',
                        },
                    },
                    label: {
                        show: false,
                    },
                    data: [100],
                },
                {
                    //底层外发光
                    type: 'pie',
                    z: 1,
                    zlevel: -2,
                    radius: ['70%', '100%'],
                    center: ['50%', '50%'],
                    hoverAnimation: false,
                    clockWise: false,
                    itemStyle: {
                        normal: {
                            borderWidth: 20,
                            color: 'rgba(224,242,255,.4)',
                        },
                    },
                    label: {
                        show: false,
                    },
                    data: [100],
                },
                {
                    type: 'pie',
                    zlevel: 0,
                    silent: true,
                    radius: ['78%', '80%'],
                    z: 1,
                    label: {
                        normal: {
                            show: false,
                        },
                    },
                    labelLine: {
                        normal: {
                            show: false,
                        },
                    },
                    data: this.Pie(),
                },
            ],
        };
        console.log(this.Pie());
        let myChart = echarts.init(this.container);
        option && myChart.setOption(option);
    }

}