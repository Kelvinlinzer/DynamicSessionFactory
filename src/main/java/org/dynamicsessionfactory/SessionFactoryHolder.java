package org.dynamicsessionfactory;

import org.dynamicsessionfactory.proxy.SessionFactoryProxy;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 * Created by Kelvin.Li on 07/09/2017.
 */
public class SessionFactoryHolder {

    private SessionFactory newSessionFactory;

    private SessionFactoryProxy sessionFactoryProxy;

    /**
     * Make it thread-local, so that no race condition will happen.
     */
    private static ThreadLocal<Boolean> sessionFactoryNeedsUpdate = new ThreadLocal<Boolean>();

    public boolean isSessionFactoryNeedsUpdate() {
        return (sessionFactoryNeedsUpdate.get() != null) && sessionFactoryNeedsUpdate.get();
    }

    public void setSessionFactoryNeedsUpdate(boolean needsUpdate) {
        this.sessionFactoryNeedsUpdate.set(needsUpdate);
    }

    public void setNewSessionFactory(SessionFactory newSessionFactory) {
        this.newSessionFactory = newSessionFactory;
    }

    public SessionFactoryProxy getSessionFactoryProxy() {
        return sessionFactoryProxy;
    }

    public void setSessionFactoryProxy(SessionFactoryProxy sessionFactoryProxy) {
        this.sessionFactoryProxy = sessionFactoryProxy;
    }

    public void updateSessionFactory() {
        if (sessionFactoryNeedsUpdate.get()) {
            SessionFactory existingSessionFactory = sessionFactoryProxy.get();
            sessionFactoryProxy.set(newSessionFactory);
            sessionFactoryNeedsUpdate.set(false);
            try {
                existingSessionFactory.close();
            } catch (HibernateException ex) {
                throw ex;
            }
        }
    }
}
