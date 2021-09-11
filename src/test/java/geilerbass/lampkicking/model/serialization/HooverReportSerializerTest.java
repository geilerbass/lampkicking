package geilerbass.lampkicking.model.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverReport;
import geilerbass.lampkicking.model.serialization.HooverReportSerializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HooverReportSerializerTest {

    @Test
    public void hooverReportSerializesCorrectly() throws JsonProcessingException {
        final HooverReport hooverReport = new HooverReport(new Coords(1, 2), 1);
        final ObjectMapper mapper = new ObjectMapper();

        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(HooverReport.class, new HooverReportSerializer());
        mapper.registerModule(simpleModule);

        String jsonString = mapper.writeValueAsString(hooverReport);

        assertEquals("{\"coords\":[1,2],\"patches\":1}", jsonString);
    }
}