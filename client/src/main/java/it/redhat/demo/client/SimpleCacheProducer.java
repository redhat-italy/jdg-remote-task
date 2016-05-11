package it.redhat.demo.client;

import org.infinispan.client.hotrod.Flag;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 08/05/16
 */
public class SimpleCacheProducer {

    private static final String CACHE_NAME = "application-cache";

    @Inject
    private Logger log;

    @Inject
    @HotRodClient
    private RemoteCacheManager cacheContainer;

    @Produces
    @ApplicationScoped
    @SimpleCache
    public RemoteCache<String, String> getCache() {

        log.info("simple remote cache {} :: produce", CACHE_NAME);

        return cacheContainer.<String, String>getCache(CACHE_NAME).withFlags(Flag.FORCE_RETURN_VALUE);

    }

}
