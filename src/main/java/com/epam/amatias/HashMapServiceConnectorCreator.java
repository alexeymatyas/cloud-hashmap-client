package com.epam.amatias;

import com.epam.amatias.rest.CloudHashMapClient;
import com.epam.amatias.rest.impl.CloudHashMapClientImpl;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

/**
 * Created by Aleksei_Matias on 3/23/2017.
 */
public class HashMapServiceConnectorCreator extends AbstractServiceConnectorCreator<CloudHashMapClient, HashMapServiceInfo> {
    @Override
    public CloudHashMapClient create(HashMapServiceInfo hashMapServiceInfo, ServiceConnectorConfig serviceConnectorConfig) {
        return new CloudHashMapClientImpl(hashMapServiceInfo.getUrl());
    }
}
