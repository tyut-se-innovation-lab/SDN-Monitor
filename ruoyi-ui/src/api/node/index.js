export default function handlerData(data) {
    let arr = [];
    data.map(value => {
        value.ports.map(item => {
            console.log()
            if (item.portId !== `${value.switchId}:LOCAL`) {
                item.switchId = value.switchId;
                arr[arr.length] = item;
            }
        })
    })
    return arr;
}