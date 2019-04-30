package com.eagle.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GenericEncodingFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 转型为与协议相关对象
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 对request包装增强
        HttpServletRequest myrequest = new MyRequest(httpServletRequest);
        filterChain.doFilter(myrequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}

// 自定义request对象
class MyRequest extends HttpServletRequestWrapper{

    private HttpServletRequest request;
    private boolean hasEncode;

    public MyRequest(HttpServletRequest request) {
        super(request); // super必须写
        this.request = request;

    }

    @Override
    public String getParameter(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        if (values == null) {
            return null;
        }
        return values[0]; // 取回参数的第一个值
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        // 先获取请求方式
        String method = request.getMethod();
        if(method.equalsIgnoreCase("post")){
            try{
                request.setCharacterEncoding("utf-8");
                return request.getParameterMap();
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }else if(method.equalsIgnoreCase("get")){
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (!hasEncode) { // 确保get手动编码逻辑只运行一次
                for (String parameterName : parameterMap.keySet()) {
                    String[] values = parameterMap.get(parameterName);
                    if (values != null) {
                        for (int i = 0; i < values.length; i++) {
                            try {
                                // 处理get乱码
                                values[i] = new String(values[i].getBytes("ISO-8859-1"), "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                hasEncode = true;
            }
            return parameterMap;
        }


        return super.getParameterMap();
    }

    @Override
    public String[] getParameterValues(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        return values;
    }
}
