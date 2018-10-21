package org.gad.aws;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LTrainStreamHandler extends SkillStreamHandler {

    Logger log = LogManager.getLogger(this.getClass());

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new FallbackRequestHandler(),
                        new LaunchRequestHandler(),
                        new HelloWorldHandler(),
                        new SessionEndedRequestHandler()
                ).build();
    }

    public LTrainStreamHandler() {
        super(getSkill());
        log.info("starting...");
    }

}
