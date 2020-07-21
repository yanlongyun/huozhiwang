package com.zrzhen.huozhiwang.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;

public class StringUtil {
    /**
     * 判断字符串是否为空
     * 当str为null或""时返回true
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否不为空
     * 当str为null或""时返回false
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 字符串是否为空或空白符
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    /**
     * 字符串是否不为空且不为空白符
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 去掉字符串两端的控制符(control characters, char <= 32) , 如果输入为 null 则返回null
     * 控制符就是用于打印和显示控制、数据结构化、传输控制、以及其他零散用途的特殊符号。如/t /f  whitespace 等制表符、换行符、换页符和回车符
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return StringUtils.trim(str);
    }

    /**
     * 去掉字符串两端的控制符(control characters, char <= 32) ,如果变为 null 或""，则返回 null
     *
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
        return StringUtils.trimToNull(str);
    }

    /**
     * 去掉字符串两端的控制符(control characters, char <= 32) ,如果变为 null 或 "" ，则返回 ""
     *
     * @param str
     * @return
     */
    public static String trimToEmpty(String str) {
        return StringUtils.trimToEmpty(str);
    }

    /**
     * 去掉字符串两端的空白符(whitespace) ，如果输入为 null 则返回 null
     *
     * @param str
     * @return
     */
    public static String strip(String str) {
        return StringUtils.strip(str);
    }

    /**
     * 去掉字符串两端的空白符(whitespace) ，如果变为 null 或""，则返回 null
     *
     * @param str
     * @return
     */
    public static String stripToNull(String str) {
        return StringUtils.stripToNull(str);
    }

    /**
     * 去掉字符串两端的空白符(whitespace) ，如果变为 null 或"" ，则返回""
     *
     * @param str
     * @return
     */
    public static String stripToEmpty(String str) {
        return StringUtils.stripToEmpty(str);
    }

    /**
     * 去掉 str 两端的在 stripChars 中的字符。
     * 如果 str 为 null 或等于"" ，则返回它本身；
     * 如果 stripChars 为 null 或"" ，则返回 strip(String str) 。
     *
     * @param str
     * @param stripChars
     * @return
     */
    public static String strip(String str, String stripChars) {
        return StringUtils.strip(str, stripChars);
    }

    /**
     * 和11相似，去掉 str 前端的在 stripChars 中的字符。
     *
     * @param str
     * @param stripChars
     * @return
     */
    public static String stripStart(String str, String stripChars) {
        return StringUtils.stripStart(str, stripChars);
    }

    /**
     * 和11相似，去掉 str 末端的在 stripChars 中的字符。
     *
     * @param str
     * @param stripChars
     * @return
     */
    public static String stripEnd(String str, String stripChars) {
        return StringUtils.stripEnd(str, stripChars);
    }

    /**
     * 对字符串数组中的每个字符串进行 strip(String str) ，然后返回。
     * 如果 strs 为 null 或 strs 长度为0，则返回 strs 本身
     *
     * @param strs
     * @return
     */
    public static String[] stripAll(String[] strs) {
        return StringUtils.stripAll(strs);
    }

    /**
     * 对字符串数组中的每个字符串进行 strip(String str, String stripChars) ，然后返回。
     * 如果 strs 为 null 或 strs 长度为0，则返回 strs 本身
     *
     * @param strs
     * @param stripChars
     * @return
     */
    public static String[] stripAll(String[] strs, String stripChars) {
        return StringUtils.stripAll(strs, stripChars);
    }

    /**
     * 比较两个字符串是否相等，如果两个均为空则也认为相等。
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return StringUtils.equals(str1, str2);
    }

    /**
     * 比较两个字符串是否相等，不区分大小写，如果两个均为空则也认为相等。
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return StringUtils.equalsIgnoreCase(str1, str2);
    }

    /**
     * 返回字符 searchChar 在字符串 str 中第一次出现的位置。
     * 如果 searchChar 没有在 str 中出现则返回-1，
     * 如果 str 为 null 或 "" ，则也返回-1
     *
     * @param str
     * @param searchChar
     * @return
     */
    public static int indexOf(String str, char searchChar) {
        return StringUtils.indexOf(str, searchChar);
    }

    /**
     * 返回字符 searchChar 在字符串 str 中最后出现的位置。
     * 如果 searchChar 没有在 str 中出现则返回-1，
     * 如果 str 为 null 或 "" ，则也返回-1
     *
     * @param str
     * @param searchChar
     * @return
     */
    public static int lastIndexOf(String str, char searchChar) {
        return StringUtils.lastIndexOf(str, searchChar);
    }

    /**
     * 返回字符 searchChar 从 startPos 开始在字符串 str 中第一次出现的位置。
     * 如果从 startPos 开始 searchChar 没有在 str 中出现则返回-1，
     * 如果 str 为 null 或 "" ，则也返回-1
     *
     * @param str
     * @param searchChar
     * @param startPos
     * @return
     */
    public static int indexOf(String str, char searchChar, int startPos) {
        return StringUtils.indexOf(str, searchChar, startPos);
    }

    /**
     * 返回字符 searchChar 从 startPos 开始在字符串 str 中最后次出现的位置。
     * 如果从 startPos 开始 searchChar 没有在 str 中出现则返回-1，
     * 如果 str 为 null 或 "" ，则也返回-1
     *
     * @param str
     * @param searchChar
     * @param startPos
     * @return
     */
    public static int lastIndexOf(String str, char searchChar, int startPos) {
        return StringUtils.lastIndexOf(str, searchChar, startPos);
    }

    /**
     * 返回字符串 searchStr 在字符串 str 中第一次出现的位置。
     * 如果 str 为 null 或 searchStr 为 null 则返回-1，
     * 如果 searchStr 为 "" ,且 str 为不为 null ，则返回0，
     * 如果 searchStr 不在 str 中，则返回-1
     *
     * @param str
     * @param searchStr
     * @return
     */
    public static int indexOf(String str, String searchStr) {
        return StringUtils.indexOf(str, searchStr);
    }

    /**
     * 返回字符串 searchStr 在字符串 str 中最后次出现的位置。
     * 如果 str 为 null 或 searchStr 为 null 则返回-1，
     * 如果 searchStr 为 "" ,且 str 为不为 null ，则返回0，
     * 如果 searchStr 不在 str 中，则返回-1
     *
     * @param str
     * @param searchStr
     * @return
     */
    public static int lastIndexOf(String str, String searchStr) {
        return StringUtils.lastIndexOf(str, searchStr);
    }

    /**
     * 返回字符串 searchStr 在字符串 str 中第 ordinal 次出现的位置。
     * 如果 str=null 或 searchStr=null 或 ordinal<=0 则返回-1
     *
     * @param str
     * @param searchStr
     * @param ordinal
     * @return
     */
    public static int ordinalIndexOf(String str, String searchStr, int ordinal) {
        return StringUtils.ordinalIndexOf(str, searchStr, ordinal);
    }

    /**
     * 返回字符串 searchStr 从 startPos 开始在字符串 str 中第一次出现的位置。
     *
     * @param str
     * @param searchStr
     * @param startPos
     * @return
     */
    public static int indexOf(String str, String searchStr, int startPos) {
        return StringUtils.indexOf(str, searchStr, startPos);
    }

    /**
     * 返回字符串 searchStr 从 startPos 开始在字符串 str 中最后次出现的位置。
     *
     * @param str
     * @param searchStr
     * @param startPos
     * @return
     */
    public static int lastIndexOf(String str, String searchStr, int startPos) {
        return StringUtils.lastIndexOf(str, searchStr, startPos);
    }

    /**
     * 将返回直接首尾拼接的字符串
     *
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> String join(T... elements) {
        return StringUtils.join(elements);
    }

    /**
     * 将返回用separator字符串参数拼接的字符串
     *
     * @param array
     * @param separator
     * @return
     */
    public static String join(Object[] array, String separator) {
        return StringUtils.join(array, separator);
    }

    /**
     * 将返回用separator字符串参数从元素startIndex开始到元素endIndex结束拼接的字符串
     *
     * @param array
     * @param separator
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        return StringUtils.join(array, separator, startIndex, endIndex);
    }

    /**
     * 将返回实现迭代接口的对象的拼接字符串
     *
     * @param iterable
     * @param separator
     * @return
     */
    public static String join(Iterable<?> iterable, String separator) {
        return StringUtils.join(iterable, separator);
    }

    /**
     * 将返回以separator参数拼接Object可变参数的字符串
     *
     * @param separator
     * @param objects
     * @return
     */
    public static String joinWith(String separator, Object... objects) {
        return StringUtils.joinWith(separator, objects);
    }

    /**
     * 按字节长度截取字符串
     *
     * @param str         要截取的字符串
     * @param len         要截取的字节长度
     * @param charsetName 编码
     * @return
     * @throws IOException
     */
    public static String cutStrByBytes(String str, int len, String charsetName) throws IOException {
        if (null == charsetName || !charsetName.equalsIgnoreCase("gbk")) {
            charsetName = "utf-8";
        }
        byte[] buf = str.getBytes(charsetName);
        int count = 0;
        if (buf.length < len) {
            len = buf.length;
        }
        for (int x = len - 1; x >= 0; x--) {
            if (buf[x] < 0) {
                count++;
            } else {
                break;
            }
        }
        if ("GBK".equalsIgnoreCase(charsetName)) {
            if (count % 2 == 0) {
                return new String(buf, 0, len, "gbk");
            } else {
                return new String(buf, 0, len - 1, "gbk");
            }
        } else {
            if (count % 3 == 0) {
                return new String(buf, 0, len, "utf-8");
            } else if (count % 3 == 1) {
                return new String(buf, 0, len - 1, "utf-8");
            } else {
                return new String(buf, 0, len - 2, "utf-8");
            }
        }
    }

    /**
     * 根据字符长度截取字符串
     *
     * @param str
     * @param len
     * @return
     */
    public static String cutStrBySize(String str, int len) {
        if (str == null) {
            return null;
        }
        return str.length() < len ? str : str.substring(0, len - 1);
    }


    /**
     * 获取字符串中的第一个数字，如果带小数点则忽略
     *
     * @param str
     * @return
     */
    public static Integer firstIntOfStr(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            if (Character.isDigit(x) == true) {
                sb.append(x);
            } else if (sb.length() > 0) {
                break;
            }
        }
        return sb.length() > 0 ? Integer.valueOf(sb.toString()) : null;
    }


    /**
     * 获取字符串中的第一个数字，可带小数点
     *
     * @param str
     * @return Double
     */
    public static Double firstDoubleOfStr(String str) {
        StringBuilder sb = new StringBuilder();
        boolean point = false;
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            if (Character.isDigit(x) == true) {
                sb.append(x);
                /*小数点处理*/
            } else if (sb.length() > 0 && String.valueOf(x).equalsIgnoreCase(".") && point == false) {
                sb.append(x);
                point = true;
            } else if (sb.length() > 0) {
                break;
            }
        }
        return sb.length() > 0 ? Double.valueOf(sb.toString()) : null;
    }

    /**
     * 获取字符串中的第一个数字，可带小数点
     *
     * @param str
     * @return BigDecimal
     */
    public static BigDecimal firstBigDecimalOfStr(String str) {
        Double num = firstDoubleOfStr(str);
        return num != null ? BigDecimal.valueOf(num) : null;
    }

}
