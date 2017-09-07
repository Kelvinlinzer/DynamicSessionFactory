package org.dynamicsessionfactory.hibernate4.support;

import org.dynamicsessionfactory.SessionFactoryHolder;
import org.hibernate.HibernateException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Kelvin.Li on 07/09/2017.
 */
public class OpenSessionInViewFilter implements Filter {

    private FilterConfig filterConfig;

    private SessionFactoryHolder sessionFactoryHolder;

    private SessionFactoryHolder lookupSessionFactoryProxy() {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        return wac.getBean(SessionFactoryHolder.class);
    }

    @Override public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            if (sessionFactoryHolder == null) {
                sessionFactoryHolder = lookupSessionFactoryProxy();
                if (sessionFactoryHolder == null) {
                    throw new HibernateException("No SessionFactoryHolder found in Spring context!!!");
                }
            }
            // Update the session first, then call super function.
            if (sessionFactoryHolder.isSessionFactoryNeedsUpdate()) {
                sessionFactoryHolder.updateSessionFactory();
            }
        }
    }

    @Override public void destroy() {
    }
}
