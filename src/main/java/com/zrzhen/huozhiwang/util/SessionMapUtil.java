package com.zrzhen.huozhiwang.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class SessionMapUtil {
    /**
     * key:sessionid
     * value:HashMap
     * <p>
     * HashMap
     * key:openid
     */
    public static ConcurrentHashMap<String, Long> sessions = new ConcurrentHashMap();
    /**
     * 通过sessionid获取userId
     * @param sessionid
     * @return
     */
    public static Long getValue(String sessionid) {
        return SessionMapUtil.sessions.get(sessionid);
    }

    public static Long putValue(String sessionid,Long userId) {
        return SessionMapUtil.sessions.put(sessionid,userId);
    }

    /*
    * 获取前端传来的sessionid
    * 如果没有新建一个并返回给前端
    * */
    public static String getSession(HttpServletRequest request, HttpServletResponse response) {
        String  sessionid = CookieUtil.getCookie(request, "sessionid");
        if (StringUtil.isBlank(sessionid)) {
            sessionid = SessionUtil.genSessionid();
            /*添加sessionid给cookie*/
            CookieUtil.addCookie(response, "sessionid", sessionid, 10000);
            return sessionid;
        }
        return sessionid;
    }
}
