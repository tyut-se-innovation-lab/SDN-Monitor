import * as echarts from "echarts";
export default class Relate {
    /**
     * 构造函数
     * @param {String} title 标题
     * @param {String} containerId 容器ID
     * @param {Array} node 节点数组
     * @param {Array} link 边数组
     */
    constructor(title, containerId, { node, link }) {
        this.title = title;
        this.container = document.getElementById(containerId);
        this.node = node;
        this.link = link;
    }

    /**
     * 初始化
     */
    init() {
        let myChart = echarts.init(this.container);

        let option = {
            title: {
                text: 'Controls'
            },

            tooltip: {},
            animationDurationUpdate: 1500,
            animationEasingUpdate: 'quinticInOut',
            series: [
                {
                    type: 'graph',
                    //图的类型
                    layout: 'none',
                    //节点标记的大小
                    symbolSize: 50,
                    //是否开启鼠标缩放和平移漫游
                    roam: true,
                    //是否显示文本标签
                    label: {
                        show: true
                    },
                    //边两端的标记类型
                    //下面表示起点为圆，终点为箭头
                    edgeSymbol: ['circle', 'circle'],
                    //边两段的标记大小
                    edgeSymbolSize: [4, 4],
                    //边上文字的大小
                    edgeLabel: {
                        fontSize: 20
                    },
                    data: [
                        {
                            name: 'Node 1',
                            x: 300,
                            y: 300,
                            tooltip: {
                                formatter: function () {
                                    return '777<br/>999';
                                }
                            }
                        },
                        {
                            name: 'Node 2',
                            x: 800,
                            y: 300
                        },
                        {
                            name: 'Node 3',
                            x: 550,
                            y: 100
                        },
                        {
                            name: 'Node 4',
                            x: 550,
                            y: 500
                        }
                    ],
                    // links: [],
                    links: [
                        {
                            //数字表示节点索引
                            source: 0,
                            target: 1,
                            symbolSize: [5, 20],
                            //是否显示边上文字
                            label: {
                                show: true,
                                //边字符串内容
                                formatter: function () {
                                    return "666";
                                }
                            },
                            //边样式
                            lineStyle: {
                                width: 5,
                                //边曲度
                                curveness: 0.2
                            }
                        },
                        {
                            //名字表示索引
                            source: 'Node 2',
                            target: 'Node 1',
                            label: {
                                show: true
                            },
                            lineStyle: {
                                curveness: 0.2
                            }
                        },
                        {
                            source: 'Node 1',
                            target: 'Node 3'
                        },
                        {
                            source: 'Node 2',
                            target: 'Node 3'
                        },
                        {
                            source: 'Node 2',
                            target: 'Node 4'
                        },
                        {
                            source: 'Node 1',
                            target: 'Node 4'
                        }
                    ],
                    lineStyle: {
                        opacity: 0.9,
                        width: 2,
                        curveness: 0
                    }
                }
            ]
        };
        //设置节点
        option.series[0].data = this.setNode(this.node);
        option.series[0].links = this.link;
        //初始化echarts
        option && myChart.setOption(option);
    }
    /**
     * 设置端点信息
     * @param {Object} node 端点信息
     * @returns 
     */
    setNode(node) {
        return node.map(value => {
            value.tooltip = {};
            value.itemStyle = {};
            value.label = {};
            //文字偏移,[x,y]
            value.label.offset = [0, 30];
            //文字颜色
            value.label.color = "black";
            if (value.type === "Switch") {
                //文字图标,path路径表示
                value.symbol = "path://M213.6 707.3h84.8c11.9 0 21.6-9.7 21.6-21.6v-20.2c0-11.9-9.7-21.6-21.6-21.6h-84.8c-11.9 0-21.6 9.7-21.6 21.6v20.2c0 11.9 9.7 21.6 21.6 21.6zM213.6 834.8h84.8c11.9 0 21.6-9.7 21.6-21.6V793c0-11.9-9.7-21.6-21.6-21.6h-84.8c-11.9 0-21.6 9.7-21.6 21.6v20.2c0 12 9.7 21.6 21.6 21.6zM414.7 707.3h0.7c17.5 0 31.7-14.2 31.7-31.7v-0.1c0-17.5-14.2-31.7-31.7-31.7h-0.7c-17.5 0-31.7 14.2-31.7 31.7v0.1c0 17.5 14.2 31.7 31.7 31.7zM414.7 834.8h0.7c17.5 0 31.7-14.2 31.7-31.7v-0.1c0-17.5-14.2-31.7-31.7-31.7h-0.7c-17.5 0-31.7 14.2-31.7 31.7v0.1c0 17.6 14.2 31.7 31.7 31.7zM542.7 707.3h0.7c17.5 0 31.7-14.2 31.7-31.7v-0.1c0-17.5-14.2-31.7-31.7-31.7h-0.7c-17.5 0-31.7 14.2-31.7 31.7v0.1c0 17.5 14.2 31.7 31.7 31.7zM542.7 834.8h0.7c17.5 0 31.7-14.2 31.7-31.7v-0.1c0-17.5-14.2-31.7-31.7-31.7h-0.7c-17.5 0-31.7 14.2-31.7 31.7v0.1c0 17.6 14.2 31.7 31.7 31.7zM669.7 707.3h0.7c17.5 0 31.7-14.2 31.7-31.7v-0.1c0-17.5-14.2-31.7-31.7-31.7h-0.7c-17.5 0-31.7 14.2-31.7 31.7v0.1c0 17.5 14.2 31.7 31.7 31.7zM669.7 834.8h0.7c17.5 0 31.7-14.2 31.7-31.7v-0.1c0-17.5-14.2-31.7-31.7-31.7h-0.7c-17.5 0-31.7 14.2-31.7 31.7v0.1c0 17.6 14.2 31.7 31.7 31.7zM797.7 707.3h0.7c17.5 0 31.7-14.2 31.7-31.7v-0.1c0-17.5-14.2-31.7-31.7-31.7h-0.7c-17.5 0-31.7 14.2-31.7 31.7v0.1c0 17.5 14.2 31.7 31.7 31.7zM797.7 834.8h0.7c17.5 0 31.7-14.2 31.7-31.7v-0.1c0-17.5-14.2-31.7-31.7-31.7h-0.7c-17.5 0-31.7 14.2-31.7 31.7v0.1c0 17.6 14.2 31.7 31.7 31.7z M960 577.6L893.8 91.3c-2.2-15.8-15.7-27.6-31.7-27.6H163.8c-15.9 0-29.4 11.7-31.7 27.5L64 577.6h0.8l-0.8 0.8v345.4c0 20.2 16.4 36.6 36.6 36.6h822.9c20.2 0 36.6-16.4 36.6-36.6V578.4l-0.8-0.8h0.7zM479.4 129.3l-4.1 66h310.8l0.8 67.1H471.2l-4.3 69.2L266 228.1l213.4-98.8zM259.3 374.5h314.8l2.8-76.3 211.4 106.5-219.5 111.9 2.6-69.5h-320l7.9-72.6zM896 896.4H128V577.6h768v318.8z";
                //图标大小
                value.symbolSize = [50, 35];
                //图标颜色
                value.itemStyle.color = "blue";
                //端点悬浮文字
                value.tooltip.formatter = function () {
                    return `Name:${value.name}<br/>Type:${value.type}`;
                }
            } else {
                value.symbol = "path://M816.394521 7.013699H206.20274c-19.638356 0-35.068493 15.430137-35.068493 35.068493v941.238356c0 19.638356 15.430137 35.068493 35.068493 35.068493h610.191781c19.638356 0 35.068493-15.430137 35.068493-35.068493V42.082192c0-19.638356-15.430137-35.068493-35.068493-35.068493z m-35.068494 941.238356H241.271233V77.150685h540.054794v871.10137z M319.824658 276.339726h396.975342c19.638356 0 35.068493-15.430137 35.068493-35.068493V131.857534c0-19.638356-15.430137-35.068493-35.068493-35.068493H319.824658c-19.638356 0-35.068493 15.430137-35.068494 35.068493v109.413699c0 18.235616 15.430137 35.068493 35.068494 35.068493z m35.068493-109.413699h326.838356v39.276713H354.893151v-39.276713zM319.824658 464.306849c19.638356 0 35.068493-15.430137 35.068493-35.068493v-70.136986c0-19.638356-15.430137-35.068493-35.068493-35.068493s-35.068493 15.430137-35.068494 35.068493v70.136986c0 19.638356 15.430137 35.068493 35.068494 35.068493z";
                value.symbolSize = [30, 45];
                value.itemStyle.color = "black";
                value.tooltip.formatter = function () {
                    return `IP:${value.ip}<br/>Type:${value.type}`;
                }
            }
            return value;
        });
    }
}