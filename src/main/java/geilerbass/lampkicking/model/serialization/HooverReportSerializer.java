package geilerbass.lampkicking.model.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import geilerbass.lampkicking.model.HooverReport;

import java.io.IOException;

public class HooverReportSerializer extends StdSerializer<HooverReport> {

    public HooverReportSerializer() {
        this(null);
    }

    public HooverReportSerializer(Class<HooverReport> t) {
        super(t);
    }

    @Override
    public void serialize(HooverReport hooverReport, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("coords");
        jsonGenerator.writeArray(new int[]{hooverReport.getCoords().getX(), hooverReport.getCoords().getY()}, 0, 2);
        jsonGenerator.writeNumberField("patches", hooverReport.getPatches());
        jsonGenerator.writeEndObject();
    }
}
