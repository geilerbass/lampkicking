package geilerbass.lampkicking.model.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverRequest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HooverRequestDeserializerTest {

    @Test
    public void hooverRequestDeserializesCorrectly() throws JsonProcessingException {
        final HooverRequest hooverRequest = new HooverRequest(
                new Coords(5, 5),
                new Coords(1, 2),
                new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                "NNESEESWNWW");
        final ObjectMapper mapper = new ObjectMapper();

        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(HooverRequest.class, new HooverRequestDeserializer());
        mapper.registerModule(simpleModule);

        String json = "{\"roomSize\":[5,5],\"coords\":[1,2]," +
                "\"patches\":[[1,0],[2,2],[2,3]]," +
                "\"instructions\":\"NNESEESWNWW\"}";
        HooverRequest actual = mapper.readValue(json, HooverRequest.class);

        assertEquals(hooverRequest, actual);
    }
}