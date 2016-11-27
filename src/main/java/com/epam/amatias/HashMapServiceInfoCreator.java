package com.epam.amatias;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

import java.util.Map;

/**
 * Created by Alexey on 27.11.2016.
 */
public class HashMapServiceInfoCreator extends CloudFoundryServiceInfoCreator<HashMapServiceInfo> {
    private static final Log logger = LogFactory.getLog(HashMapServiceInfoCreator.class);

    public HashMapServiceInfoCreator() {
        super(new Tags(new String[]{"hashmap"}), HashMapServiceInfo.URI_SCHEME);
    }

    @Override
    public HashMapServiceInfo createServiceInfo(Map<String, Object> serviceData) {
        HashMapServiceInfo serviceInfo = new HashMapServiceInfo(
                (String) serviceData.get("name"),
                (String) ((Map)serviceData.get("credentials")).get("url"));
        return serviceInfo;
    }
}
