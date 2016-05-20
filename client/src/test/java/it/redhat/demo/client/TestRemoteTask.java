package it.redhat.demo.client;

import it.redhat.demo.model.ModelPojo;
import it.redhat.demo.task.LogModelPojoTask;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.impl.ConfigurationProperties;
import org.infinispan.tasks.ServerTask;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@RunWith(Arquillian.class)
public class TestRemoteTask {

    private final static Logger log = LoggerFactory.getLogger(TestRemoteTask.class);

    private static final String TASK_NAME = "logModelPojo";

    private static final String CACHE_NAME = "application-cache";

    @Test
    public void testExecuteTask() {
        // Create a configuration for a locally-running server
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer().host("127.0.0.1").port(ConfigurationProperties.DEFAULT_HOTROD_PORT);
        // Connect to the server
        RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());

        RemoteCache<Object, Object> cache = cacheManager.getCache();

        log.info("execute task {} on cache: {}", TASK_NAME, cache.getName());
        HashMap params = new HashMap();

        ModelPojo pojo = new ModelPojo();
        pojo.setString("ciao ciao ciao");
        params.put("one", pojo);

        Object result = cache.execute(TASK_NAME, params);
        log.info("with outcome {}", result);

        // Stop the cache manager and release resources
        cacheManager.stop();
    }

    @Before
    public void setUp() throws Exception {
        log.debug("setUp() --> client Created!");
    }

    @After
    public void tearDown() throws Exception {
        log.debug("tearDown() --> close!");
    }

    @Deployment(testable = false)
    public static JavaArchive createDeployment() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addClass(ModelPojo.class)
                .addClass(LogModelPojoTask.class)
                .addAsServiceProvider(ServerTask.class, LogModelPojoTask.class);
        System.out.println(archive.toString(true));
        return archive;
    }
}
