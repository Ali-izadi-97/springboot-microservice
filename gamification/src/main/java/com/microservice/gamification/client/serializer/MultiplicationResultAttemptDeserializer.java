package com.microservice.gamification.client.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.microservice.gamification.client.dto.MultiplicationResultDto;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class MultiplicationResultAttemptDeserializer extends JsonDeserializer<MultiplicationResultDto> {
    @Override
    public MultiplicationResultDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        MultiplicationResultDto dto = new MultiplicationResultDto(
                node.get("user").get("name").asText(),
                node.get("multiplication").get("factorA").asInt(),
                node.get("multiplication").get("factorB").asInt(),
                node.get("result").asInt(),
                node.get("correct").asBoolean()
        );
        return dto;
    }
}
