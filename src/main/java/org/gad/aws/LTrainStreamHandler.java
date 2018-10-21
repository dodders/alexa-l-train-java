package org.gad.aws;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

public class LTrainStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new FallbackRequestHandler(),
                        new LaunchRequestHandler(),
                        new HelloWorldHandler()
                ).build();
    }

    public LTrainStreamHandler() {
        super(getSkill());
    }
}
