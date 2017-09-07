package dynamic.session.factory;

import dynamic.session.factory.proxy.SessionFactoryProxy;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class LocalSessionFactoryProxyFactoryBean extends LocalSessionFactoryBean {

    private SessionFactoryProxy product;

    @Override public SessionFactoryProxy getObject() {
        this.product = new SessionFactoryProxy(super.getObject());
        return product;
    }

    @Override public Class<?> getObjectType() {
        return SessionFactoryProxy.class;
    }
}
