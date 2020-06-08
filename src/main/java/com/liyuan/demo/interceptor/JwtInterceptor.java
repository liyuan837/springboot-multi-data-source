package com.liyuan.demo.interceptor;

import com.liyuan.demo.annotation.NotToken;
import com.liyuan.demo.util.JSONUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

/**
 * @description:用户Authorthon验证拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor{

    //拦截器白名单
    private List<String> excludes;
    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }
    private PathMatcher pathMatcher = new AntPathMatcher();

    private boolean lookupUrl(String urlPath) {
        for (String registeredPattern : excludes) {
            if (pathMatcher.match(registeredPattern, urlPath)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        String urlPath = httpServletRequest.getRequestURI();
        //[1]请求地址处理
        if (!urlPath.startsWith("/")) {
            urlPath = "/" + urlPath;
        }

        //[2]判断是否在白名单中
        if (lookupUrl(urlPath)) {
            return true;
        }

        //[3]对于使用了@NotToken的接口，不需要校验token
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            NotToken notToken = method.getMethodAnnotation(NotToken.class);
            if (notToken != null){
                return true;
            }
        }

        //[4]模拟拦截所有JwtController下的请求
        if(urlPath.indexOf("/jwt/") >=0){
            String authHeader = httpServletRequest.getHeader("Authorization");
            if (authHeader == null || authHeader.equals("")) {
                //拦截，并且返回错误信息
                errorResponse(httpServletResponse,202,"尚未携带有效的Token");
                return false;
            }else{
                return true;
            }
        }
//        //[4]其他接口都需要JWT token验证
//        String authHeader = httpServletRequest.getHeader("Authorization");
//
//        if (authHeader == null || authHeader.equals("")) {
//            //拦截，并且返回错误信息
//            errorResponse(httpServletResponse,202,"尚未携带有效的Token");
//            return false;
//        }else{
//            return true;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void errorResponse(final ServletResponse res, int errCode, String errMsg) {
        try {
            OutputStream out = res.getOutputStream();
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json; charset=utf-8");
            String retJson = JSONUtil.createJsonString(false, errCode, errMsg, Collections.EMPTY_MAP);

            out.write(retJson.getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {

        }
    }
}
