package dynamic.session.factory.proxy;

import org.hibernate.*;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Kelvin.Li on 07/09/2017.
 */
public class SessionFactoryProxy implements SessionFactory {

    private SessionFactory target;

    public SessionFactoryProxy(SessionFactory target) {
        if (target == null) {
            throw new HibernateException("Target SessionFactory is null!!!");
        }
        this.target = target;
    }

    public void set(SessionFactory target) {
        if (target == null) {
            throw new HibernateException("Target SessionFactory is null!!!");
        }
        this.target = target;
    }

    public SessionFactory get() {
        return this.target;
    }

    @Override public SessionFactoryOptions getSessionFactoryOptions() {
        return target.getSessionFactoryOptions();
    }

    @Override public SessionBuilder withOptions() {
        return target.withOptions();
    }

    @Override public Session openSession() throws HibernateException {
        return target.openSession();
    }

    @Override public Session getCurrentSession() throws HibernateException {
        return target.getCurrentSession();
    }

    @Override public StatelessSessionBuilder withStatelessOptions() {
        return target.withStatelessOptions();
    }

    @Override public StatelessSession openStatelessSession() {
        return target.openStatelessSession();
    }

    @Override public StatelessSession openStatelessSession(Connection connection) {
        return target.openStatelessSession(connection);
    }

    @Override public ClassMetadata getClassMetadata(Class entityClass) {
        return target.getClassMetadata(entityClass);
    }

    @Override public ClassMetadata getClassMetadata(String entityName) {
        return target.getClassMetadata(entityName);
    }

    @Override public CollectionMetadata getCollectionMetadata(String roleName) {
        return target.getCollectionMetadata(roleName);
    }

    @Override public Map<String, ClassMetadata> getAllClassMetadata() {
        return target.getAllClassMetadata();
    }

    @Override public Map getAllCollectionMetadata() {
        return target.getAllCollectionMetadata();
    }

    @Override public Statistics getStatistics() {
        return target.getStatistics();
    }

    @Override public void close() throws HibernateException {
        target.close();
    }

    @Override public boolean isClosed() {
        return target.isClosed();
    }

    @Override public Cache getCache() {
        return target.getCache();
    }

    @Override public void evict(Class persistentClass) throws HibernateException {
        target.evict(persistentClass);
    }

    @Override public void evict(Class persistentClass, Serializable id) throws HibernateException {
        target.evict(persistentClass, id);
    }

    @Override public void evictEntity(String entityName) throws HibernateException {
        target.evictEntity(entityName);
    }

    @Override public void evictEntity(String entityName, Serializable id) throws HibernateException {
        target.evictEntity(entityName, id);
    }

    @Override public void evictCollection(String roleName) throws HibernateException {
        target.evictCollection(roleName);
    }

    @Override public void evictCollection(String roleName, Serializable id) throws HibernateException {
        target.evictCollection(roleName, id);
    }

    @Override public void evictQueries(String cacheRegion) throws HibernateException {
        target.evictQueries(cacheRegion);
    }

    @Override public void evictQueries() throws HibernateException {
        target.evictQueries();
    }

    @Override public Set getDefinedFilterNames() {
        return target.getDefinedFilterNames();
    }

    @Override public FilterDefinition getFilterDefinition(String filterName) throws HibernateException {
        return target.getFilterDefinition(filterName);
    }

    @Override public boolean containsFetchProfileDefinition(String name) {
        return target.containsFetchProfileDefinition(name);
    }

    @Override public TypeHelper getTypeHelper() {
        return target.getTypeHelper();
    }

    @Override public Reference getReference() throws NamingException {
        return target.getReference();
    }

    /**
     * Override this function helps TransactionSynchronizationManager to get current SessionFactory to grab the correct session.
     * For more details, please refer TransactionSynchronizationManager.bindResource(Object key, Object value) .
     * @return
     */
    @Override public int hashCode() {
        return this.target.hashCode();
    }
}
