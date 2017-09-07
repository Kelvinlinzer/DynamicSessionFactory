package org.dynamicsessionfactory;

import org.dynamicsessionfactory.proxy.SessionFactoryProxy;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class LocalSessionFactoryProxyFactoryBean extends LocalSessionFactoryBean {

    private SessionFactoryProxy product;

    @Override public SessionFactoryProxy getObject() {
        if (this.product == null) {
            this.product = new SessionFactoryProxy(super.getObject());
        }

        return this.product;
    }

    @Override public Class<?> getObjectType() {
        return SessionFactoryProxy.class;
    }
}
