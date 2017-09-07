package org.dynamicsessionfactory.hibernate4.support;

import org.dynamicsessionfactory.SessionFactoryHolder;
import org.hibernate.HibernateException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kelvin.Li on 07/09/2017.
 */
public class OpenSessionInViewFilter extends org.springframework.orm.hibernate4.support.OpenSessionInViewFilter {

    private volatile SessionFactoryHolder sessionFactoryHolder;

    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
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
        super.doFilterInternal(request, response, filterChain);
    }

    private SessionFactoryHolder lookupSessionFactoryProxy() {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        return wac.getBean(SessionFactoryHolder.class);
    }
}
