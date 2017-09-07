package org.dynamicsessionfactory.listener;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Kelvin.Li on 07/09/2017.
 */
public class SessionFactoryChangeEvent extends ApplicationEvent {

    public SessionFactoryChangeEvent(Object source) {
        super(source);
        if (!(source instanceof SessionFactory)) {
            throw new IllegalArgumentException("SessionFactoryChangeEvent needs an org.hibernate.SessionFactory as parameter!!!");
        }
    }

}
