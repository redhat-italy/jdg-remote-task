package it.redhat.demo.task;

import it.redhat.demo.model.ModelPojo;
import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 11/05/16
 */
public class LogModelPojoTask implements ServerTask {

    private static final Logger log = LoggerFactory.getLogger(LogModelPojoTask.class);

    @Override
    public void setTaskContext(TaskContext taskContext) {
    }

    @Override
    public String getName() {
        return "logModelPojo";
    }

    @Override
    public Object call() throws Exception {
        ModelPojo modelPojo = new ModelPojo();
        log.info("ciao [" + modelPojo + "]");
        return modelPojo;

    }

}
