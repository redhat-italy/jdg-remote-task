package it.redhat.demo.client;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 06/05/16
 */
@Singleton
@Startup
public class HotRod7ClentManager {

    @Inject
    private Logger log;

    @Inject
    @HotRodClient
    private RemoteCacheManager cacheManager;

    @PostConstruct
    public void start() {
        cacheManager.start();
        log.info("remote cache manager :: started");
    }

    @PreDestroy
    public void stop() {
        cacheManager.stop();
        log.info("remote cache manager :: stopped");
    }

}
