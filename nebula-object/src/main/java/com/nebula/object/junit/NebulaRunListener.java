package com.nebula.object.junit;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.lang.annotation.Annotation;
import java.util.Collection;

public class NebulaRunListener extends RunListener {

    @Override
    public void testStarted(Description description) throws Exception {
        Collection<Annotation> annotations = description.getAnnotations();
    }

    @Override
    public void testFinished(Description description) throws Exception {
        super.testFinished(description);
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
    }
}
