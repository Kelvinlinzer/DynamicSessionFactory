package org.dynamicsessionfactory;

import org.hibernate.cfg.Configuration;

/**
 * Created by Kelvin.Li on 08/09/2017.
 */
public class HibernateConfigurationHolder {
    private static Configuration configuration;

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        HibernateConfigurationHolder.configuration = configuration;
    }
}
