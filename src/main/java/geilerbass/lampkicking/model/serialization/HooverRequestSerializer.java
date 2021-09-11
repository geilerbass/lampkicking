package geilerbass.lampkicking.model.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverRequest;

import java.io.IOException;
import java.util.Arrays;

public class HooverRequestSerializer extends StdSerializer<HooverRequest> {

    protected HooverRequestSerializer(Class<HooverRequest> t) {
        super(t);
    }

    public HooverRequestSerializer() {
        this(null);
    }

    @Override
    public void serialize(HooverRequest hooverRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("roomSize");
        jsonGenerator.writeArray(new int[]{hooverRequest.getRoomSize().getX(), hooverRequest.getRoomSize().getY()}, 0, 2);
        jsonGenerator.writeFieldName("coords");
        jsonGenerator.writeArray(new int[]{hooverRequest.getCoords().getX(), hooverRequest.getCoords().getY()}, 0, 2);
        jsonGenerator.writeFieldName("patches");
        jsonGenerator.writeStartArray();
        for (final Coords patch : hooverRequest.getPatches()) {
            jsonGenerator.writeArray(new int[]{patch.getX(), patch.getY()}, 0, 2);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeStringField("instructions", hooverRequest.getInstructions());
        jsonGenerator.writeEndObject();
    }
}
