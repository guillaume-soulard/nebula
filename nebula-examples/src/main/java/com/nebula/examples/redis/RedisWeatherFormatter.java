package com.nebula.examples.redis;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.formatter.AbstractFormatter;
import com.nebula.core.formatter.ValueFormatter;

import java.util.ArrayList;

public class RedisWeatherFormatter extends AbstractFormatter {

    public RedisWeatherFormatter() {
        super(new ValueFormatter("dd/MM/yyyy HH:mm", ' ', ' '), new ArrayList<>());
    }

    @Override
    public String formatHeader(Entity entity) {
        return "redis.call('FLUSHALL') redis.call('SELECT', '0')";
    }

    @Override
    public String formatGeneratedObject(GeneratedObject generatedObject) {
        return "return redis.call('HMSET', '2017:temperature', '" +
                valueFormatter.formatValue(generatedObject.getGeneratedPropertyValue("during").getObject()) + "', '" +
                generatedObject.getGeneratedPropertyValue("temperature") + "')";
    }

    @Override
    public String formatFooter(Entity entity) {
        return "return 0";
    }
}
