package com.yooxinz.filter;

import com.yooxinz.common.LogConstant;
import com.yooxinz.utils.TraceLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-09-03
 */
@Component
@Slf4j
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // "traceId"
        MDC.put(LogConstant.LOG_TRACE_ID, TraceLogUtils.getTraceId());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}