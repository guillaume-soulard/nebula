package com.nebula.generationrule.rest;

import com.nebula.Model;
import com.nebula.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.formatter.FormatterBuilder;
import com.nebula.formatter.NebulaFormatters;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.misc.IOUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.nebula.core.types.NebulaTypes.dateTime;
import static com.nebula.core.types.NebulaTypes.string;
import static com.nebula.core.generators.NebulaGenerators.random;
import static org.assertj.core.api.Assertions.assertThat;

public class RestGenerationRuleIT {

    private static RestGenerationRule rest;

    @BeforeClass
    public static void setUp() throws Exception {
        Model model = new ModelBuilder().withSeed("users").withDateFormat("dd/MM/yyyy").build();

        Entity entity = model.newEntity("user");
        entity.addProperty("firstName", random(), string().withPattern("[A-Aa-z]{10,25}"));
        entity.addProperty("lastName", random(), string().withPattern("[A-Aa-z]{10,25}"));
        entity.addProperty("dayOfBirth", random(), dateTime().range().withMin(new DateTime(1940, 1, 1, 0, 0)).withMax(new DateTime(2017, 12, 31, 0, 0)));
        model.addEntity(entity);
        Map<String, FormatterBuilder> formatterMap = new HashMap<>();
        formatterMap.put(ContentType.APPLICATION_JSON.getMimeType(), NebulaFormatters.json());
        formatterMap.put(ContentType.TEXT_PLAIN.getMimeType(), NebulaFormatters.csv());
        rest = new RestGenerationRule(model, formatterMap, ContentType.APPLICATION_JSON.getMimeType(), "localhost", 8080);

        Thread thread = new Thread(() -> rest.generate());

        thread.start();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        rest.stop();
    }

    @Test
    public void execute_should_return_first_item_in_resource() throws Exception {

        // GIVEN
        HttpClient client = HttpClientBuilder.create().build();

        // WHEN
        HttpResponse response = client.execute(new HttpHost("localhost", 8080), new HttpGet("/user/0"));

        // THEN
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(getContent(response)).isEqualTo("{firstName:\"ApAAkAAeAAAAAt\",lastName:\"rAAAAjtkAA\",dayOfBirth:06/01/1998,_id:0}");
    }

    @Test
    public void execute_should_return_result_when_negative_index_is_passed() throws Exception {

        // GIVEN
        HttpClient client = HttpClientBuilder.create().build();

        // WHEN
        HttpResponse response = client.execute(new HttpHost("localhost", 8080), new HttpGet("/user/-1"));

        // THEN
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(getContent(response)).isEqualTo("{firstName:\"AAkAAAsqrAhA\",lastName:\"lAfAqAqudAc\",dayOfBirth:31/01/1959,_id:-1}");
    }

    @Test
    public void execute_should_return_first_10_items() throws Exception {

        // GIVEN
        HttpClient client = HttpClientBuilder.create().build();

        // WHEN
        HttpResponse response = client.execute(new HttpHost("localhost", 8080), new HttpGet("/user"));

        // THEN
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(getContent(response)).isEqualTo("[{firstName:\"ApAAkAAeAAAAAt\",lastName:\"rAAAAjtkAA\",dayOfBirth:06/01/1998,_id:0},{firstName:\"AAgcAAAmsAy\",lastName:\"yppAAafqAAAmAwAgduAAAxx\",dayOfBirth:18/12/1945,_id:1},{firstName:\"AAAlymAafAA\",lastName:\"edAzgAAgAAhc\",dayOfBirth:06/10/1978,_id:2},{firstName:\"AArzqAxAAA\",lastName:\"kAmzcAcAAAu\",dayOfBirth:28/04/1985,_id:3},{firstName:\"AAeAAiAAAAgAj\",lastName:\"qdAAvAAcAAyA\",dayOfBirth:03/04/1946,_id:4},{firstName:\"AdnAdjuAAA\",lastName:\"yAAAlAAaAA\",dayOfBirth:15/03/1972,_id:5},{firstName:\"ArAsAjAAAA\",lastName:\"eekAAAnolAAAAg\",dayOfBirth:13/09/2011,_id:6},{firstName:\"AAwcAAhrAxAvdy\",lastName:\"kAtgAAAwAA\",dayOfBirth:24/08/1959,_id:7},{firstName:\"AglAvAAhAcyot\",lastName:\"qAgAAxkkAA\",dayOfBirth:31/07/1998,_id:8},{firstName:\"AAuApAfnAsA\",lastName:\"yAAAAAsiuAr\",dayOfBirth:20/02/2005,_id:9}]");
    }

    @Test
    public void execute_should_return_first_2_items() throws Exception {

        // GIVEN
        HttpClient client = HttpClientBuilder.create().build();

        // WHEN
        HttpResponse response = client.execute(new HttpHost("localhost", 8080), new HttpGet("/user?count=2"));

        // THEN
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(getContent(response)).isEqualTo("[{firstName:\"ApAAkAAeAAAAAt\",lastName:\"rAAAAjtkAA\",dayOfBirth:06/01/1998,_id:0},{firstName:\"AAgcAAAmsAy\",lastName:\"yppAAafqAAAmAwAgduAAAxx\",dayOfBirth:18/12/1945,_id:1}]");
    }

    @Test
    public void execute_should_return_first_10_items_skipping_the_2_first() throws Exception {

        // GIVEN
        HttpClient client = HttpClientBuilder.create().build();

        // WHEN
        HttpResponse response = client.execute(new HttpHost("localhost", 8080), new HttpGet("/user?skip=2"));

        // THEN
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(getContent(response)).isEqualTo("[{firstName:\"AAAlymAafAA\",lastName:\"edAzgAAgAAhc\",dayOfBirth:06/10/1978,_id:2},{firstName:\"AArzqAxAAA\",lastName:\"kAmzcAcAAAu\",dayOfBirth:28/04/1985,_id:3},{firstName:\"AAeAAiAAAAgAj\",lastName:\"qdAAvAAcAAyA\",dayOfBirth:03/04/1946,_id:4},{firstName:\"AdnAdjuAAA\",lastName:\"yAAAlAAaAA\",dayOfBirth:15/03/1972,_id:5},{firstName:\"ArAsAjAAAA\",lastName:\"eekAAAnolAAAAg\",dayOfBirth:13/09/2011,_id:6},{firstName:\"AAwcAAhrAxAvdy\",lastName:\"kAtgAAAwAA\",dayOfBirth:24/08/1959,_id:7},{firstName:\"AglAvAAhAcyot\",lastName:\"qAgAAxkkAA\",dayOfBirth:31/07/1998,_id:8},{firstName:\"AAuApAfnAsA\",lastName:\"yAAAAAsiuAr\",dayOfBirth:20/02/2005,_id:9},{firstName:\"AAAuiaAdAup\",lastName:\"eArAduAygAvtjAAA\",dayOfBirth:29/04/1979,_id:10},{firstName:\"AAAiaAAAjl\",lastName:\"ksAnywAeyAgAA\",dayOfBirth:19/11/1985,_id:11}]");
    }

    @Test
    public void execute_should_return_items_at_index_0_5_7() throws Exception {

        // GIVEN
        HttpClient client = HttpClientBuilder.create().build();

        // WHEN
        HttpResponse response = client.execute(new HttpHost("localhost", 8080), new HttpGet("/user?indices=0,5,7"));

        // THEN
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(getContent(response)).isEqualTo("[{firstName:\"ApAAkAAeAAAAAt\",lastName:\"rAAAAjtkAA\",dayOfBirth:06/01/1998,_id:0},{firstName:\"AdnAdjuAAA\",lastName:\"yAAAlAAaAA\",dayOfBirth:15/03/1972,_id:5},{firstName:\"AAwcAAhrAxAvdy\",lastName:\"kAtgAAAwAA\",dayOfBirth:24/08/1959,_id:7}]");
    }

    @Test
    public void execute_should_return_not_found_when_resource_not_exists() throws Exception {

        // GIVEN
        HttpClient client = HttpClientBuilder.create().build();

        // WHEN
        HttpResponse response = client.execute(new HttpHost("localhost", 8080), new HttpGet("/unexisting/0"));

        // THEN
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(404);
        assertThat(getContent(response)).isEqualTo("{error:\"Not found\",detail:\"The resource 'unexisting' is not found in current model\"}");
    }

    @Test
    public void execute_should_return_entity_as_csv_format() throws Exception {

        // GIVEN
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("/user/0");
        request.addHeader(HttpHeaders.ACCEPT, ContentType.TEXT_PLAIN.getMimeType());

        // WHEN
        HttpResponse response = client.execute(new HttpHost("localhost", 8080), request);

        // THEN
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(getContent(response)).isEqualTo("ApAAkAAeAAAAAt,rAAAAjtkAA,06/01/1998,0");
    }

    private String getContent(HttpResponse response) throws IOException {
        return new String(IOUtils.readFully(response.getEntity().getContent(), -1, false));
    }
}
