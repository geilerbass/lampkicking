package geilerbass.lampkicking.model.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import geilerbass.lampkicking.model.Coords;

import java.io.IOException;

public class CoordsSerializer extends StdSerializer<Coords> {
    public CoordsSerializer(Class<Coords> t) {
        super(t);
    }

    public CoordsSerializer() {
        this(null);
    }

    @Override
    public void serialize(Coords coords, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArray(new int[]{coords.getX(), coords.getY()}, 0, 2);
        jsonGenerator.writeEndObject();
    }
}
