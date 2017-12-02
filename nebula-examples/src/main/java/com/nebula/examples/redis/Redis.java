package com.nebula.examples.redis;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.output.NebulaOutputs;
import com.nebula.core.types.date.DateTimeTypeIntervals;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;

import java.util.Map;

import static com.nebula.core.formatter.NebulaFormatters.custom;
import static com.nebula.core.generationconstraint.NebulaConstraints.amount;
import static com.nebula.core.generationrule.GenerationRules.newOneShootGenerationRule;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.generators.NebulaGenerators.sequence;
import static com.nebula.core.types.NebulaTypes.dateTime;
import static com.nebula.core.types.NebulaTypes.number;
import static java.lang.System.exit;

public class Redis {

    private static final Integer REDIS_PORT = 3000;

    public static void main(String[] args) throws Exception {

        RedisServer redisServer = new RedisServer(REDIS_PORT);
        try {
            startRedisServer(redisServer);
            Jedis jedis = new Jedis("localhost", REDIS_PORT);

            Model model = new ModelBuilder()
                    .withSeed("redis weather time series")
                    .withDateFormat("dd/MM/yyyy")
                    .withNumberDecimalSeparator(' ')
                    .withNumberThousandSeparator(' ')
                    .build();

            Entity weather = model.newEntity("weather");
            weather.addProperty("time", sequence(), dateTime().range()
                    .withInterval(DateTimeTypeIntervals.HOUR)
                    .withMin("01/01/2017")
                    .withMax("01/01/2018"));
            weather.addProperty("temperature", random(), number().range()
                    .withMin("-20")
                    .withMax("40"));
            model.addEntity(weather);

            model.addGenerationRule(newOneShootGenerationRule()
                    .withEntity(weather)
                    .addOutput(NebulaOutputs.custom(new RedisOutput(jedis)))
                    .addGenerationConstraint(amount(8761))
                    .withFormatter(custom(new RedisWeatherFormatter())));

            model.generate();

            printResults(jedis);
        } finally {
            redisServer.stop();
        }

        exit(0);
    }

    private static void printResults(Jedis jedis) {
        jedis.hgetAll("2017:temperature").entrySet().forEach(System.out::println);
    }

    private static void startRedisServer(RedisServer redisServer) {
        if (!redisServer.isActive()) {
            redisServer.start();
        }
    }
}
