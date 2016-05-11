package it.redhat.demo.rest;

import it.redhat.demo.client.SimpleCache;
import it.redhat.demo.model.ModelPojo;
import org.infinispan.client.hotrod.RemoteCache;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.HashMap;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 11/05/16
 */
@Path("modelPojo")
public class ModelPojoRestService {

    public static final String LOG_SIMPLE_POJO = "logModelPojo";

    @Inject
    private Logger log;

    @Inject
    @SimpleCache
    private RemoteCache cache;

    @POST
    public void add() {

        log.info("execute task {} on cache: {}", LOG_SIMPLE_POJO, cache.getName());
        HashMap params = new HashMap();

        ModelPojo pojo = new ModelPojo();
        pojo.setString("ciao ciao ciao");
        params.put("one", pojo);

        Object result = cache.execute(LOG_SIMPLE_POJO, params);
        log.info("with outcome {}", result);

    }

}
