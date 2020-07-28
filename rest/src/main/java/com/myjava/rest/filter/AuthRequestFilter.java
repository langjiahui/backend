package com.myjava.rest.filter;

import com.alibaba.fastjson.JSON;
import com.myjava.rest.utils.JwtUtil;
import com.myjava.rest.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 请求统一拦截
 *
 * @author ljh
 * @note 对Request仅做header业务参数的验证，不做业务处理
 * @Date 2020/7/28
 */
@WebFilter(urlPatterns = {"*"}, asyncSupported = true, filterName = "authFilter")
@Order(0)
@Slf4j
public class AuthRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("--------------- ==>AuthRequestFilter init method: init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest requestContext = (HttpServletRequest) request;
        String url = requestContext.getServletPath();
        String token = requestContext.getHeader("token");
        log.info(url+"|"+token);

        if(StringUtils.isBlank(token)){
            returnRes(response,AjaxResult.fail("token empty!"),url);
            return;
        }

        boolean verity = JwtUtil.verity(token);
        if (!verity) {
            returnRes(response,AjaxResult.fail("verity fail!"),url);
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        log.info("--------------- ==> destroy method: destroy");
    }


    public static void returnRes(ServletResponse response, AjaxResult bean, String url) {
        log.info(url+"|filter|ResultBean="+ JSON.toJSONString(bean));
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(bean));

        } catch (IOException e) {
            log.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
