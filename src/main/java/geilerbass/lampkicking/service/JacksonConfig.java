package geilerbass.lampkicking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import geilerbass.lampkicking.model.HooverReport;
import geilerbass.lampkicking.model.HooverRequest;
import geilerbass.lampkicking.model.serialization.HooverReportSerializer;
import geilerbass.lampkicking.model.serialization.HooverRequestDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(HooverReport.class, new HooverReportSerializer());
        simpleModule.addDeserializer(HooverRequest.class, new HooverRequestDeserializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
