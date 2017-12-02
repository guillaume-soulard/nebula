package com.nebula.examples.redis;

import com.nebula.core.output.Output;
import redis.clients.jedis.Jedis;

public class RedisOutput implements Output {

    private Jedis jedis;

    public RedisOutput(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void open() {

    }

    @Override
    public void write(String formattedObject) {
        jedis.eval(formattedObject);
    }

    @Override
    public void close() {

    }
}
