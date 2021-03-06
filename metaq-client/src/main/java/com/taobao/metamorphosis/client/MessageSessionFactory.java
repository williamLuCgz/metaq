package com.taobao.metamorphosis.client;

import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.storage.OffsetStorage;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.PartitionSelector;
import com.taobao.metamorphosis.exception.MetaClientException;


/**
 * 消息会话工厂，meta客户端的主接口,推荐一个应用只使用一个MessageSessionFactory
 * 
 * @author boyan
 * @Date 2011-4-27
 * 
 */
public interface MessageSessionFactory extends Shutdownable {

    /**
     * 关闭工厂
     * 
     * @throws MetaClientException
     */
    @Override
    public void shutdown() throws MetaClientException;


    /**
     * 创建消息生产者
     * 
     * @param partitionSelector
     *            分区选择器
     * @return
     */
    public MessageProducer createProducer(PartitionSelector partitionSelector);


    /**
     * 创建消息生产者，默认使用轮询分区选择器
     * 
     * @return
     */
    public MessageProducer createProducer();


    /**
     * 创建消息生产者，默认使用轮询分区选择器。本方法已经废弃，请勿使用，不排除在未来某个版本删除。
     * 
     * @param ordered
     *            是否有序，true为有序，如果有序，则消息按照发送顺序保存在MQ server
     * @return
     */
    @Deprecated
    public MessageProducer createProducer(boolean ordered);


    /**
     * 创建消息生产者,本方法已经废弃，请勿使用，不排除在未来某个版本删除。
     * 
     * @param partitionSelector
     *            分区选择器
     * @param ordered
     *            是否有序，true为有序，如果有序，则消息按照发送顺序保存在MQ server
     * @return
     */
    @Deprecated
    public MessageProducer createProducer(PartitionSelector partitionSelector, boolean ordered);


    /**
     * 创建消息消费者，默认将offset存储在zk
     * 
     * @param consumerConfig
     *            消费者配置
     * @return
     */
    public MessageConsumer createConsumer(ConsumerConfig consumerConfig);


    /**
     * 创建消息消费者，使用指定的offset存储器
     * 
     * @param consumerConfig
     *            消费者配置
     * @param offsetStorage
     *            offset存储器
     * @return
     */
    public MessageConsumer createConsumer(ConsumerConfig consumerConfig, OffsetStorage offsetStorage);

}