/**
 * 处理关系数据
 */
export default class RelateData {
    /**
     * 构造函数
     * @param {Object} data 数据
     */
    constructor(data) {
        this.topology = data["network-topology"]["topology"][0];
    }

    /**
     * 初始化
     */
    init() {
        let data = getPointNum();
        let pointData = data[0];
        let data1 = data[1];
        linkRemoveDuplicates();
        this.link = getSide();

        let ggtmp = new GraphGenerate(pointData, 100, 200, 200);
        ggtmp.init();
        let point = ggtmp.pointsCoordinate;

        switchData(point, data1);

        let fdata = flatteningData(data1);

        console.log(fdata);
    }

    /**
     * 获取节点数
     * @returns 
     */
    getPointNum() {
        let node = this.topology.node;
        //各自交换机子节点个数
        let obj = {};
        //交换机和子节点树状结构
        let resultArr = [];
        //找到所有的交换机节点
        node.map((value) => {
            let arr = value["node-id"].split(":");
            if (arr[0] === "openflow") {

                obj[arr[1]] = 0;

                let tmpobj = {};
                tmpobj["name"] = value["node-id"];
                tmpobj["type"] = "Switch";
                tmpobj["children"] = [];
                resultArr[resultArr.length] = tmpobj;
            }
        })
        //找到所有的主机节点
        node.map((value) => {
            let arr = value["node-id"].split(":");
            if (arr[0] === "host") {
                let arr2 = value["host-tracker-service:attachment-points"][0]["tp-id"].split(":");
                let str = `${arr2[0]}:${arr2[1]}`;
                obj[arr2[1]]++;

                let tmpobj = {};
                tmpobj["name"] = value["node-id"];
                tmpobj["children"] = [];
                tmpobj["type"] = "Host";
                tmpobj["ip"] = value["host-tracker-service:addresses"][0]["ip"];

                //将主机节点放到指定交换机中
                for (let i = 0; i < resultArr.length; i++) {
                    if (resultArr[i].name === str) {
                        let length = resultArr[i]["children"].length;
                        resultArr[i]["children"][length] = tmpobj;
                    }
                }
            }
        })
        return [obj, resultArr];
    }

    /**
     * 连接去重
     */
    linkRemoveDuplicates() {
        let link = this.topology.link;
        //标记数组
        let arrTip = [];
        //整理link-id属性并得出标记数组
        link.map(value => {
            let arr;
            if (value["link-id"].indexOf("/") !== -1) {
                arr = value["link-id"].split("/");
                arrTip[arrTip.length] = arr[0].indexOf("openflow") !== -1 ? `${arr[0]}/${arr[1]}` : `${arr[1]}/${arr[0]}`;
            } else {
                arr = value["link-id"].split(":");
                arr.sort((a, b) => {
                    return a - b;
                })
                arrTip[arrTip.length] = arr.join("/");
            }
        })

        //实现数组去重
        let i = 0;
        //需删除元素下标
        let deleteIndex = [];
        arrTip.map(value => {
            if (arrTip.indexOf(value) !== i) {
                //标记数组与原数组同步
                deleteIndex[deleteIndex.length] = i;
            }
            i++;
        })
        for (let i = link.length - 1; i >= 0; i--) {
            let j = deleteIndex.pop();
            if (i === j) {
                link.splice(i, 1);
            } else {
                deleteIndex.push(j);
            }
            if (deleteIndex[0] === undefined) {
                break;
            }
        }
        console.log(arrTip, link);
    }

    /**
     * 获得边的数组
     * @returns 
     */
    getSide() {
        let link = this.topology.link;
        let linkArr = [];
        link.map(value => {
            let arr = [];
            //更新字符串
            if (value["link-id"].indexOf("/") !== -1) {
                arr = value["link-id"].split("/");
                if (arr[0].indexOf("openflow") !== -1) {
                    let tmp = arr[0].split(":");
                    arr[0] = `${tmp[0]}:${tmp[1]}`;
                } else {
                    let tmp = arr[1].split(":");
                    arr[1] = `${tmp[0]}:${tmp[1]}`;
                }
            } else {
                let arr1 = value["link-id"].split(":");
                if (arr1[1] !== arr1[2]) {
                    arr[0] = `${arr1[0]}:${arr1[1]}`;
                    arr[1] = `${arr1[0]}:${arr1[2]}`;
                }
            }
            //返回边数组元素
            if (arr[0] !== undefined) {
                linkArr[linkArr.length] = {
                    source: arr[0],
                    target: arr[1]
                }
            }

        })
        return linkArr;
    };

    /**
     * 补全数据
     * @param {Object} point 坐标数据
     * @param {Object} data 具体信息
     */
    switchData(point, data) {
        //将坐标数据赋值给data
        for (let i = 0; i < point.length; i++) {
            data[i]["x"] = point[i]["pointX"];
            data[i]["y"] = point[i]["pointY"];
            if (data[i].children[0] !== undefined) {
                this.switchData(point[i]["children"], data[i]["children"]);
            }
        }
        console.log(point, data);
    }

    /**
     * 扁平化数据
     * @param {Number} data 数据
     * @returns 
     */
    flatteningData(data) {
        console.log(data);
        let node = [];
        data.map(value => {
            node[node.length] = value;
            if (value.children[0] !== undefined) {
                value.children.map(value1 => {
                    node[node.length] = value1;
                })
            }
        })
        node.forEach(item => {
            this.deleteObjAttribute(item, 'children');
        })
        return node;
    }

    /**
     * 删除指定对象的指定属性
     * @param {Object} obj 要删除的对象名称
     * @param {String} attrName 属性名称
     */
    deleteObjAttribute(obj, attrName) {
        delete obj[attrName];
    }
}