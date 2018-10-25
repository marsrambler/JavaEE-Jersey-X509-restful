package com.jack.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * Wrapper for asset types
 * Date: 12/26/12
 */
@XmlRootElement(name="types")
public class EntityWrapper {

    protected Collection<String> list;

    public EntityWrapper(){}

    /**
     * @param list license types list
     */
    public EntityWrapper(Collection<String> list){
        this.list=list;
    }

    /**
     * @return license types list
     */
    @XmlElement(name="type")
    public Collection<String> getList(){
        return list;
    }
}
