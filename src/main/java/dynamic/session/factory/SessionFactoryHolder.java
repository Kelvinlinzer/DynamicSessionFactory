package dynamic.session.factory;

import dynamic.session.factory.context.listener.SessionFactoryChangeListener;
import dynamic.session.factory.proxy.SessionFactoryProxy;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Kelvin.Li on 07/09/2017.
 */
public class SessionFactoryHolder {

    private static final Logger logger = LoggerFactory.getLogger(SessionFactoryChangeListener.class);

    private SessionFactory newSessionFactory;

    private volatile SessionFactoryProxy sessionFactoryProxy;

    private AtomicBoolean sessionFactoryNeedsUpdate = new AtomicBoolean(false);

    public boolean isSessionFactoryNeedsUpdate() {
        return sessionFactoryNeedsUpdate.get();
    }

    public void setSessionFactoryNeedsUpdate(boolean needsUpdate) {
        this.sessionFactoryNeedsUpdate.set(needsUpdate);
    }

    public void setNewSessionFactory(SessionFactory newSessionFactory) {
        this.newSessionFactory = newSessionFactory;
    }

    public void setSessionFactoryProxy(SessionFactoryProxy sessionFactoryProxy) {
        this.sessionFactoryProxy = sessionFactoryProxy;
    }

    public synchronized void updateSessionFactory() {
        if (sessionFactoryNeedsUpdate.get()) {
            SessionFactory existingSessionFactory = sessionFactoryProxy.get();
            sessionFactoryProxy.set(newSessionFactory);
            sessionFactoryNeedsUpdate.set(false);
            try {
                existingSessionFactory.close();
            } catch (HibernateException ex) {
                logger.warn("Error happened during closing old SessionFactory!", ex);
                throw ex;
            }
        }
    }
}
