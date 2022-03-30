package com.es.conf;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author ren.xiaolong
 * @date 2022/3/30
 * @Description 配置 连接 es 客户端 注入es client 对象
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${es.url}")
    String esUrl;

    @Value("${es.port}")
    String esPort;

    @Bean
    @Qualifier("esClient")
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(esUrl +":"+esPort).build();
        RestHighLevelClient esClient = RestClients.create(clientConfiguration).rest();
        return esClient;
    }
}
