package geilerbass.lampkicking.model.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HooverRequestDeserializer extends StdDeserializer<HooverRequest> {
    public HooverRequestDeserializer(Class<?> vc) {
        super(vc);
    }

    public HooverRequestDeserializer() {
        this(null);
    }

    @Override
    public HooverRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String instructions = jsonNode.get("instructions").asText();
        ArrayNode roomSizeNode = (ArrayNode) jsonNode.get("roomSize");
        ArrayNode coordsNode = (ArrayNode) jsonNode.get("coords");
        Coords roomSize = deserialiseCoords(roomSizeNode);
        Coords coords = deserialiseCoords(coordsNode);
        List<Coords> patchesList = deserializePatches(jsonNode);
        return new HooverRequest(roomSize, coords, patchesList.toArray(new Coords[0]), instructions);
    }

    private List<Coords> deserializePatches(JsonNode jsonNode) {
        ArrayNode patchesNode = (ArrayNode) jsonNode.get("patches");
        List<Coords> patchesList = new ArrayList<>();
        Iterator<JsonNode> elements = patchesNode.elements();
        while (elements.hasNext()) {
            ArrayNode next = (ArrayNode)elements.next();
            patchesList.add(deserialiseCoords(next));
        }
        return patchesList;
    }

    private Coords deserialiseCoords(ArrayNode roomSizeNode) {
        return new Coords(roomSizeNode.get(0).asInt(), roomSizeNode.get(1).asInt());
    }
}
