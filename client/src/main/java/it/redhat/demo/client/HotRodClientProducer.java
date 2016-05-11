package it.redhat.demo.client;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 06/05/16
 */
public class HotRodClientProducer {

    private static final String DEFAULT_HOTROD_BIND_ADDRESS = "127.0.0.1";
    private static final int DEFAULT_HOTROD_PORT = 11322;

    @Inject
    private Logger log;

    @ApplicationScoped
    @Produces
    @HotRodClient
    public RemoteCacheManager getCacheManager() {

        Configuration config = new ConfigurationBuilder()
                .addServer()
                    .host(DEFAULT_HOTROD_BIND_ADDRESS).port(DEFAULT_HOTROD_PORT)
                .build();

        log.info("remote cache manager :: produce");

        return new RemoteCacheManager(config);

    }


}
