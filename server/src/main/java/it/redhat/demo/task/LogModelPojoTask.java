package it.redhat.demo.task;

import it.redhat.demo.model.ModelPojo;
import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 11/05/16
 */
public class LogModelPojoTask implements ServerTask {

    private static final Logger log = LoggerFactory.getLogger(LogModelPojoTask.class);
    private static final String TASK_NAME = "logModelPojo";
    private TaskContext taskContext;

    @Override
    public void setTaskContext(TaskContext taskContext) {
        this.taskContext = taskContext;
    }

    @Override
    public String getName() {
        return TASK_NAME;
    }

    @Override
    public Object call() throws Exception {
        //ModelPojo modelPojo = new ModelPojo();
        ModelPojo modelPojo = fromBytes((byte[]) taskContext.getParameters().get().get("one"));
        log.info("ciao [" + modelPojo + "]");
        return modelPojo;
    }

    <T> T fromBytes(byte[] bytes) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(is);
            return (T) ois.readObject();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

}
