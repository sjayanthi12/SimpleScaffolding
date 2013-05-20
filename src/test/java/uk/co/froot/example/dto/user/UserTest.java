package uk.co.froot.example.dto.user;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.URL;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

    @Test
    public void verifySerialization() throws IOException {

      // Arrange
      URL fixtureUrl = Resources.getResource("fixtures/user/test-user-1.json");
      String expected = Resources.toString(fixtureUrl, Charsets.UTF_8);

      // Ensure we have a root name
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
      objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
      objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

      // Act
      User testObject = objectMapper.readValue(expected,User.class);
      String result = objectMapper.writeValueAsString(testObject);

      // Assert
      assertThat(testObject.getId()).isEqualTo(1);
      assertThat(result).isEqualTo(expected);

  }


}
