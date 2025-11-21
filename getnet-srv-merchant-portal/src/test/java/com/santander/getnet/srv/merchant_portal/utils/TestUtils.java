package com.santander.getnet.srv.merchant_portal.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.getnet.srv.merchant_portal.dto.MetadataDTO;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {

  private TestUtils() {
  }

  public static <T> T readObjectFromResources(String filename, JsonMapper objectMapper,
      Class<T> type) throws IOException {
    final var resourceContent = TestUtils.class.getClassLoader().getResourceAsStream(filename).readAllBytes();

    return objectMapper.readValue(resourceContent, type);
  }

  public static String readStringFromResources(String filename) {
    try {
      final var resourceUri = TestUtils.class.getClassLoader().getResource(filename).toURI();
      return new String(Files.readAllBytes(Paths.get(resourceUri)));
    } catch (Exception e) {
      throw new RuntimeException(e.getCause());
    }
  }

  public static String toJson(Object obj, ObjectMapper objectMapper) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e.getCause());
    }
  }

  public static <T> T fromJson(String json, Class<T> jsonClass, ObjectMapper objectMapper) {
    try {
      return objectMapper.readValue(json, jsonClass);
    } catch (Exception e) {
      throw new RuntimeException(e.getCause());
    }
  }

  public static MetadataDTO fakeApiMetadataDTO(String personCode, String personType, String order) {
    return MetadataDTO.builder()
        .personCode(personCode)
        .personType(personType)
        .order(order)
        .build();
  }
}
