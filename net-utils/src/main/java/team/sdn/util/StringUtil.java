package team.sdn.util;

/**
 * @author Big-Bai
 **/
public class StringUtil {
    /**
     * 删除字符串中的某个值
     * @param body 要操作的字符串
     * @param str1
     * @param str2
     * @return
     */
    public static String subRangeString(String body,String str1,String str2) {
        while (true) {
            int index1 = body.indexOf(str1);
            if (index1 != -1) {
                int index2 = body.indexOf(str2, index1);
                if (index2 != -1) {
                    String str3 = body.substring(0, index1) + body.substring(index2 +    str2.length(), body.length());
                    body = str3;
                }else {
                    return body;
                }
            }else {
                return body;
            }
        }
    }
}
