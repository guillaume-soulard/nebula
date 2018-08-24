package com.nebula.core.generationrule.oneshoot;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedObjectIterator;
import com.nebula.core.NebulaException;
import com.nebula.core.formatter.Formatter;
import com.nebula.core.generationconstraint.AcceptationResult;
import com.nebula.core.generationconstraint.GenerationConstraint;
import com.nebula.core.generationrule.GenerationRule;
import com.nebula.core.output.Output;
import com.nebula.core.output.OutputParameter;

import java.util.List;

class OneShootGenerationRule implements GenerationRule {

    private final List<Output> outputsToWrite;
    private final Formatter formatterToUse;
    private final GeneratedObjectIterator generatedObjectSource;
    private final List<GenerationConstraint> generationConstraints;
    private Entity entity;

    OneShootGenerationRule(List<Output> outputsToWrite,
                           Formatter formatterToUse,
                           GeneratedObjectIterator generatedObjectSource,
                           List<GenerationConstraint> generationConstraints) {
        this.outputsToWrite = outputsToWrite;
        this.formatterToUse = formatterToUse;
        this.generatedObjectSource = generatedObjectSource;
        if (generatedObjectSource != null) {
            this.entity = generatedObjectSource.getEntity();
        }
        this.generationConstraints = generationConstraints;
        verifyState();
    }

    private void verifyState() {
        if (generatedObjectSource == null) {
            throw new NebulaException("generatedObjectSource is null");
        }

        if (entity == null) {
            throw new NebulaException("entity is null");
        }

        if (outputsToWrite == null) {
            throw new NebulaException("outputs is null");
        }

        if (outputsToWrite.contains(null)) {
            throw new NebulaException("outputs contains null");
        }

        if (formatterToUse == null) {
            throw new NebulaException("formatter is null");
        }

        if (generationConstraints == null) {
            throw new NebulaException("generationConstraints is null");
        }

        if (generationConstraints.contains(null)) {
            throw new NebulaException("generationConstraints contains null");
        }
    }

    public void generate() {
        openOutputs();
        generateHeader();
        generateObjects();
        generateFooter();
        closeOutputs();
    }

    private void closeOutputs() {
        outputsToWrite.forEach(Output::close);
    }

    private void openOutputs() {
        outputsToWrite.forEach(Output::open);
    }

    private void generateFooter() {
        writeToOutputs(null, formatterToUse.formatFooter(entity));
    }

    private void generateObjects() {
        AcceptationResult acceptationResult = AcceptationResult.ACCEPT;
        while (generatedObjectSource.hasNext()
                && !AcceptationResult.STOP_GENERATION.equals(acceptationResult)) {
            GeneratedObject generatedObject = generatedObjectSource.next();
            acceptationResult = getAcceptationResult(generatedObject);
            if (AcceptationResult.ACCEPT.equals(acceptationResult)) {
                writeToOutputs(generatedObject, formatterToUse.formatGeneratedObject(generatedObject));
                writeToOutputs(null, System.lineSeparator());
            }
        }
    }

    private void generateHeader() {
        writeToOutputs(null, formatterToUse.formatHeader(entity));
        writeToOutputs(null, System.lineSeparator());
    }

    private void writeToOutputs(GeneratedObject originalObject, String formattedGeneratedObject) {
        for (Output output : outputsToWrite) {
            output.write(new OutputParameter(formattedGeneratedObject, originalObject));
        }
    }

    private AcceptationResult getAcceptationResult(GeneratedObject generatedObject) {
        AcceptationResult acceptable = AcceptationResult.ACCEPT;
        for (GenerationConstraint generationConstraint : generationConstraints) {
            AcceptationResult generatedObjectAcceptationResult = generationConstraint.accept(generatedObject);
            if (AcceptationResult.REJECT.equals(generatedObjectAcceptationResult)) {
                acceptable = AcceptationResult.REJECT;
            }

            if (AcceptationResult.STOP_GENERATION.equals(generatedObjectAcceptationResult)) {
                acceptable = AcceptationResult.STOP_GENERATION;
            }
        }
        return acceptable;
    }
}
