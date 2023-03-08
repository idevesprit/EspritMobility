package tn.esprit.mobiliteinternationall.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MoyenneFilter implements Filter {

   Logger LOGGER = LoggerFactory.getLogger(MoyenneFilter.class);

   @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOGGER.info("[MoyenneFilter] - Inside doFilter method");
        LOGGER.info("Local Port : " + request.getLocalPort());
        LOGGER.info("Server Name : " + request.getServerName());

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        LOGGER.info("Method Name : " + httpServletRequest.getMethod());
        LOGGER.info("Request URI : " + httpServletRequest.getRequestURI());
        LOGGER.info("Servlet Path : " + httpServletRequest.getServletPath());
        chain.doFilter(request, response);
    }

}
