package org.gad.aws;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        log.info("can handle invoked with " + handlerInput.getRequestEnvelope().getRequest().toString());
        return handlerInput.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        log.info("handle fired with " + handlerInput.getRequestEnvelope().getRequest().toString());
        String text = "greeter launch request!";
        return handlerInput.getResponseBuilder()
                .withSpeech(text)
                .withReprompt(text)
                .build();
    }
}
