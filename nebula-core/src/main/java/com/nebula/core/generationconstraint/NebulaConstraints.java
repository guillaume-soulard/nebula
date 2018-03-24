package com.nebula.core.generationconstraint;

import com.nebula.core.generationconstraint.amount.AmountGenerationConstraintBuilder;
import com.nebula.core.generationconstraint.cron.CronGenerationConstraintBuilder;
import com.nebula.core.generationconstraint.custom.CustomGenerationConstraintBuilder;
import com.nebula.core.generationconstraint.during.DuringGenerationConstraintBuilder;
import com.nebula.core.generationconstraint.every.EveryGenerationConstraintBuilder;

import java.util.concurrent.TimeUnit;

public final class NebulaConstraints {

    public static AmountGenerationConstraintBuilder amount(int maxObjectsToGenerate) {
        return new AmountGenerationConstraintBuilder(maxObjectsToGenerate);
    }

    public static CustomGenerationConstraintBuilder custom(GenerationConstraint generationConstraint) {
        return new CustomGenerationConstraintBuilder(generationConstraint);
    }

    public static DuringGenerationConstraintBuilder during(long duration, TimeUnit timeUnit) {
        return new DuringGenerationConstraintBuilder(duration, timeUnit);
    }

    public static EveryGenerationConstraintBuilder every(long duration, TimeUnit timeUnit) {
        return new EveryGenerationConstraintBuilder(duration, timeUnit);
    }

    public static CronGenerationConstraintBuilder cron() {
        return new CronGenerationConstraintBuilder();
    }
}
