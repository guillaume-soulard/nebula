package com.nebula.core.types.script;

import com.nebula.core.Model;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;

public class ScriptTypeObjectHelper {

    private final Model model;

    public ScriptTypeObjectHelper(Model model) {
        this.model = model;
    }

    public ScriptObjectProxy number(long number) {

        return new ScriptObjectProxy(BigDecimal.valueOf(number));
    }

    public ScriptObjectProxy number(double number) {

        return new ScriptObjectProxy(BigDecimal.valueOf(number));
    }

    public ScriptObjectProxy date(String date) {

        return date(date, model.getDateFormat());
    }

    public ScriptObjectProxy date(String date, String format) {

        return new ScriptObjectProxy(DateTime.parse(date, DateTimeFormat.forPattern(format)));
    }

    public ScriptObjectProxy boolTrue() {

        return new ScriptObjectProxy(true);
    }

    public ScriptObjectProxy boolFalse() {

        return new ScriptObjectProxy(false);
    }

    public ScriptObjectProxy string(String string) {

        return new ScriptObjectProxy(string);
    }

    public ScriptObjectProxy nil() {

        return new ScriptObjectProxy(null);
    }
}
