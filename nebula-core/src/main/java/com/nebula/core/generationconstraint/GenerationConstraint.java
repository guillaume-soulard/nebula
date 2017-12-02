package com.nebula.core.generationconstraint;

import com.nebula.core.GeneratedObject;

public interface GenerationConstraint {
    AcceptationResult accept(GeneratedObject generatedObject);
}
