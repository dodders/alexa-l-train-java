package org.gad.aws;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import org.apache.logging.log4j.Logger;

import static com.amazon.ask.request.Predicates.requestType;
import java.util.Optional;
import java.util.logging.LogManager;

public class SessionEndedRequestHandler implements RequestHandler {

    Logger log = org.apache.logging.log4j.LogManager.getLogger(this.getClass());

    @Override
    public boolean canHandle(HandlerInput input) {
        log.info(" canHandle fired with " + input.getRequestEnvelope().getRequest().toString());
        return input.matches(requestType(SessionEndedRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        log.info("handle fired with " + input.getRequestEnvelope().getRequest().toString());
        return input.getResponseBuilder().build();
    }
}