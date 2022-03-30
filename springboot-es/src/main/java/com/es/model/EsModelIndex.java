package com.es.model;

import com.es.conf.RestClientConfig;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ren.xiaolong
 * @date 2022/3/30
 * @Description 案例操作es 索引  增删改查
 */
@Component
public class EsModelIndex {

    @Qualifier("esClient")
    @Autowired
    RestHighLevelClient esClient;

    /**
     * 创建索引
     * @param indexName  索引名
     * @param mapping    es映射 (用来描述插入文档的类型和字段结构)
     */
    public void createIndex(String indexName,String mapping) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        createIndexRequest.mapping(mapping, XContentType.JSON);
        CreateIndexResponse createIndexResponse = esClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.isAcknowledged());
        esClient.close();
    }

}
