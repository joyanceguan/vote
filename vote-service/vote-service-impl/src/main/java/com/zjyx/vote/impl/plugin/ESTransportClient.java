package com.zjyx.vote.impl.plugin;

import java.net.InetAddress;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class ESTransportClient implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ESTransportClient.class);
    private String clusterNodes;
    private String clusterName;
    private Boolean clientTransportSniff = true;
    private Boolean clientIgnoreClusterName = Boolean.FALSE;
    private String clientPingTimeout;
    private String clientNodesSamplerInterval;
    private TransportClient client;
    private Properties properties;
    static final String COLON = ":";
    static final String COMMA = ",";

    @Override
    public void destroy() throws Exception {
        try {
            logger.info("Closing elasticSearch  client");
            if (client != null) {
                client.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<TransportClient> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildClient();
    }

    protected void buildClient() throws Exception {
        client = new PreBuiltTransportClient(settings());
        Assert.hasText(clusterNodes, "[Assertion failed] clusterNodes settings missing.");
        for (String clusterNode : clusterNodes.split(COMMA)) {
        	String[] hostPort=clusterNode.split(COLON);
            String hostName = hostPort[0];
            String port = hostPort[1];
            Assert.hasText(hostName, "[Assertion failed] missing host name in 'clusterNodes'");
            Assert.hasText(port, "[Assertion failed] missing port in 'clusterNodes'");
            logger.info("adding transport node : " + clusterNode);
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port)));
        }
        client.connectedNodes();
    }

    private Settings settings() {
        if (properties != null) {
            return Settings.builder().put(properties).build();
        }
        return Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", clientTransportSniff)
                .put("client.transport.ignore_cluster_name", clientIgnoreClusterName)
                .put("client.transport.ping_timeout", clientPingTimeout)
                .put("client.transport.nodes_sampler_interval", clientNodesSamplerInterval)
                .build();
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setClientTransportSniff(Boolean clientTransportSniff) {
        this.clientTransportSniff = clientTransportSniff;
    }

    public String getClientNodesSamplerInterval() {
        return clientNodesSamplerInterval;
    }

    public void setClientNodesSamplerInterval(String clientNodesSamplerInterval) {
        this.clientNodesSamplerInterval = clientNodesSamplerInterval;
    }

    public String getClientPingTimeout() {
        return clientPingTimeout;
    }

    public void setClientPingTimeout(String clientPingTimeout) {
        this.clientPingTimeout = clientPingTimeout;
    }

    public Boolean getClientIgnoreClusterName() {
        return clientIgnoreClusterName;
    }

    public void setClientIgnoreClusterName(Boolean clientIgnoreClusterName) {
        this.clientIgnoreClusterName = clientIgnoreClusterName;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    
    public TransportClient getClient(){
    	try {
			return getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public void refreshIndex(String index){
    	RefreshRequestBuilder bu=client.admin().indices().prepareRefresh(index);
    	try {
			bu.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}