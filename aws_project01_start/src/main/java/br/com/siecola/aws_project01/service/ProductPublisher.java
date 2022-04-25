package br.com.siecola.aws_project01.service;

import br.com.siecola.aws_project01.config.SnsConfig;
import br.com.siecola.aws_project01.enums.EventType;
import br.com.siecola.aws_project01.model.Envelope;
import br.com.siecola.aws_project01.model.Product;
import br.com.siecola.aws_project01.model.ProductEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(ProductPublisher.class);

    @Autowired
    private AmazonSNS snsClient;

    @Autowired
    @Qualifier("productEventsTopic")
    private Topic productEventsTopic;

    @Autowired
    private ObjectMapper objectMapper;

    public void publishProductEvent(Product product, EventType eventType, String username) throws JsonProcessingException {
        ProductEvent productEvent = new ProductEvent();
        productEvent.setId(product.getId());
        productEvent.setCode(product.getCode());
        productEvent.setUsername(username);

        Envelope envelope = new Envelope();
        envelope.setEventType(eventType);
        envelope.setData(objectMapper.writeValueAsString(productEvent));

        snsClient.publish(
                productEventsTopic.getTopicArn(),
                objectMapper.writeValueAsString(envelope));
    }

}
