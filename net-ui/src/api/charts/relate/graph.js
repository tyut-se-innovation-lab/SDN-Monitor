/**
 * 图形
 */
export default class GraphGenerate {
    /**
     * 构造函数
     * @param {Number} pointNumObj 顶点数 
     * @param {Number} sLength 边长度
     * @param {Number} pointX 连接基点 X坐标
     * @param {Number} pointY 连接基点 Y坐标
     * @returns 
     */
    constructor(pointNumObj, sLength, centerX, centerY) {
        this.pointNumObj = pointNumObj;
        this.sLength = sLength;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    /**
     * 初始化
     */
    init() {
        //正图形坐标
        this.pointsCoordinate = this.getEquilateralGraph();
        for (let i = 0; i < this.pointsCoordinate.length; i++) {
            if (i === this.pointsCoordinate.length - 1) {
                console.log(this.pointsConnection(this.pointsCoordinate[i], i + 1));
            }
            this.pointsCoordinate[i]["children"] = this.pointsConnection(this.pointsCoordinate[i], i + 1);
        }
    }

    /**
     * 获得正图形
     * @returns 
     */
    getEquilateralGraph() {
        //交换机节点个数
        let nodeNum = Object.keys(this.pointNumObj).length;
        //内角度
        let gAngle = this.getRadAngle(360 / nodeNum);
        //内边长
        let gLength = Math.abs(this.sLength / Math.sin(gAngle / 2) / 2);
        //所有顶点的坐标
        let pointsCoordinate = [];
        for (let i = 0; i < nodeNum; i++) {
            //单个顶点坐标,下标 0表示 x,下标 1表示 y
            pointsCoordinate[i] = this.clacPointCoordinate(this.centerX, this.centerY, i * gAngle, gLength);
        }
        return pointsCoordinate;
    }

    /**
     * 将度转换为 rad单位的角度
     * @param {Number} angle 以度为单位的角度 
     * @returns 返回以rad为单位的角度
     */
    getRadAngle(angle) {
        return angle / 180 * Math.PI;
    }

    /**
     * 以某个交换机为中心生成其子节点坐标
     * @param {Object} param0 交换机点坐标 
     * @param {Object} index 交换机的下标
     * @returns 
     */
    pointsConnection({ pointX, pointY }, index) {
        //交换机节点个数
        let nodeNum = Object.keys(this.pointNumObj).length;
        //键
        let nodeKeys = Object.keys(this.pointNumObj);
        //每个交换机的节点个数
        let points = this.pointNumObj[nodeKeys[index - 1]];

        //角度(度表示法)
        let angle = 90 - (index - 1) * (360 / nodeNum);
        //偏移量
        let offsetAngle = 180 / (points + 1);

        let pointCoordinate = [];
        for (let i = 0; i < points; i++) {
            //偏移后的角度
            let offsetedAngle = angle - (i + 1) * offsetAngle;
            //角度(rad表示法)
            let gAngle = this.getRadAngle(offsetedAngle);
            //单个顶点坐标,下标 0表示 x,下标 1表示 y
            pointCoordinate[i] = this.clacPointCoordinate(pointX, pointY, -gAngle, this.sLength);
        }

        return pointCoordinate;
    }

    /**
     * 计算点的坐标
     * @param {Number} X  
     * @param {Number} Y 
     * @param {Number} gAngle 内角度
     * @param {Number} gLength 边长
     * @returns 
     */
    clacPointCoordinate(X, Y, gAngle, gLength) {
        let pointCoordinate = {};
        pointCoordinate.pointX = parseInt(X + gLength * Math.sin(gAngle));
        pointCoordinate.pointY = parseInt(Y - gLength * Math.cos(gAngle));
        // console.log(X, Y, gAngle, gLength, Math.cos(gAngle), Math.sin(gAngle));
        return pointCoordinate;
    }
}