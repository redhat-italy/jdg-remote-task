package it.redhat.demo.model;

import java.io.Serializable;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 11/05/16
 */
public class ModelPojo implements Serializable {

    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
