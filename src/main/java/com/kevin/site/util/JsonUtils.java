package com.kevin.site.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Set;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class JsonUtils {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static String toJsonString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Error converting object to JSON string", e);
    }
  }

  public static List<Long> parseJsonToList(String json) {
    try {
      return objectMapper.readValue(json, new TypeReference<List<Long>>() {});
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Error parsing JSON to List<Long>", e);
    }
  }

  public static Set<Long> parseJsonToSet(String json) {
    try {
      return objectMapper.readValue(json, new TypeReference<Set<Long>>() {});
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Error parsing JSON to Set<Long>", e);
    }
  }
}
