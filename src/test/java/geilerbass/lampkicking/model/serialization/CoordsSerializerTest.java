package geilerbass.lampkicking.model.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.serialization.CoordsSerializer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordsSerializerTest {

    @Test
    public void serialize() throws JsonProcessingException {
        final Coords coords = new Coords(1, 2);
        final ObjectMapper mapper = new ObjectMapper();

        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Coords.class, new CoordsSerializer());
        mapper.registerModule(simpleModule);

        String jsonString = mapper.writeValueAsString(coords);

        assertEquals("[1, 2]", jsonString);
    }
}