package com.electronicvoting.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
public class RequestResponseFilterConfiguration implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rep = (HttpServletResponse) servletResponse;
        log.info("----- Request ---------");
        Collections.list(req.getHeaderNames())
                .forEach(n -> log.info("{} : {}",n,req.getHeader(n)));

        filterChain.doFilter(servletRequest, servletResponse);

        log.info("----- Response ---------");

        rep.getHeaderNames()
                .forEach(n -> log.info("{} : {}",n,req.getHeader(n)));

       log.info("Response status: {}", rep.getStatus());
        log.info("\n=================================================================================");
    }

    @Override
    public void destroy() {

    }
}
