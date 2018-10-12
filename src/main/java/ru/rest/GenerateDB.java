package ru.rest;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GenerateDB {

    public static void main(String[] args) throws Exception {
        ServiceRegistry serviceRegistry = buildCfg();
        MetadataImplementor metadata = (MetadataImplementor) new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport(metadata);
        schemaExport.setOutputFile("hbm2schema.sql");
        schemaExport.create(true, true);
        ( (StandardServiceRegistryImpl) serviceRegistry ).destroy();
    }

    public static StandardServiceRegistryImpl buildCfg() {
        return (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
    }
}
