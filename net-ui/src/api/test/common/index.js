import request from '@/utils/request';

export default function commonLink() {
    return request({
        url: "/test/string",
        method: "get"
    })

}