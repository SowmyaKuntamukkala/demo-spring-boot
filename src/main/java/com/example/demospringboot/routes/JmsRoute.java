package com.example.demospringboot.routes;


import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JmsRoute extends RouteBuilder {

    @Value("${app.messageSource}")
    private String messageSource;

    @Value("${app.messageDestination}")
    private String messageDestination;


    @Override
    public void configure() throws Exception {
        consumeMessageFromAMQ();
        produceMessage2AmqUsingTimer();
        PostFileContentToAmq();
        ConsumeToMove();
        connectUsingDirect();
    }

    private void ConsumeToMove() {
        from("activemq:queue:fileMessage")
                .routeId("Consume-To-Move")
                .log(LoggingLevel.INFO, "Message consumed from the ActiveMQ Queue: ${body}")
                .setHeader(Exchange.FILE_NAME, simple("${file:name.noext}Received_on_${date:now:yyyyMMdd}.${file:name.ext}"))
                .to("file:" +messageDestination)
                .log("File created at the destination");

    }

    private void PostFileContentToAmq() {
        from("file:" + messageSource)
                .routeId("Message-from-File-Route")
                .log(LoggingLevel.INFO,"Incoming Message ${body}")
                .to("activemq:queue:fileMessage")
                .log("Message posted to the ActiveMQ Queue");
    }


    private void consumeMessageFromAMQ(){
        from("activemq:queue:demo")
                .routeId("JMS-Message-Route")
                .log(LoggingLevel.INFO,"Incoming message ${body}");

    }

    private void produceMessage2AmqUsingTimer(){
        from("timer:mytimer?period=5000")
                .routeId("Produce-Message-Route")
                .setBody(constant("Hello from camel!"))
                .to("direct:messageproducer");
    }

    private void connectUsingDirect(){
        from("direct:messageproducer")
                .routeId("Connect-Using-Direct")
                .to("activemq:queue:demo");
    }

}
