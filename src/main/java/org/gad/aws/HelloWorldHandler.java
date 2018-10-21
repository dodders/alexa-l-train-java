package org.gad.aws;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class HelloWorldHandler implements RequestHandler {

    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        log.info("canHandle called with input " + handlerInput.getRequestEnvelope().getRequest().getType().toString());
        return handlerInput.matches(Predicates.intentName("HelloWorldIntent"));
        //return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String text = "Hello World";
        log.info("handling request " + handlerInput.toString());
        return handlerInput.getResponseBuilder()
                .withSpeech(text)
                .withShouldEndSession(true)
                .build();
    }
}
