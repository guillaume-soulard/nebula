package com.nebula.core.generationconstraint.cron;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generationconstraint.AcceptationResult;
import com.nebula.core.generationconstraint.GenerationConstraint;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Optional;

class CronGenerationConstraint implements GenerationConstraint {

    private final String cronExpression;

    CronGenerationConstraint(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public AcceptationResult accept(GeneratedObject generatedObject) {

        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);

        CronParser parser = new CronParser(cronDefinition);
        Cron cron = parser.parse(cronExpression).validate();

        Optional<Duration> duration = ExecutionTime.forCron(cron)
                .timeToNextExecution(ZonedDateTime.now());

        duration.ifPresent(duration1 -> {
            try {
                Thread.sleep(duration1.getSeconds() * 1000);
            } catch (InterruptedException e) {
                throw new NebulaException(e.getMessage());
            }
        });

        return AcceptationResult.ACCEPTABLE;
    }
}
