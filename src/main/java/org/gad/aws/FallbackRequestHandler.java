package org.gad.aws;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class FallbackRequestHandler implements RequestHandler {

    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        log.info("handling request " + handlerInput.getRequestEnvelope().getRequest().toString());
        String text = "Fallback response too!";
        return handlerInput.getResponseBuilder()
                .withSpeech(text)
                .withReprompt(text)
                .build();
    }
}
