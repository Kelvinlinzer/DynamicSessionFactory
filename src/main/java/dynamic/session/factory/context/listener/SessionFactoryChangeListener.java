package dynamic.session.factory.context.listener;

import dynamic.session.factory.SessionFactoryHolder;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by Kelvin.Li on 07/09/2017.
 */
public class SessionFactoryChangeListener implements ApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(SessionFactoryChangeListener.class);

    private SessionFactoryHolder sessionFactoryHolder;

    @Override public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof SessionFactoryChangeEvent) {
            sessionFactoryHolder.setNewSessionFactory((SessionFactory) applicationEvent.getSource());
            sessionFactoryHolder.setSessionFactoryNeedsUpdate(true);
        }
    }

    public void setSessionFactoryHolder(SessionFactoryHolder sessionFactoryHolder) {
        this.sessionFactoryHolder = sessionFactoryHolder;
    }
}
