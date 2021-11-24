package Software.Engineering.Gruppe.Model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    static final DateTimeFormatter LOCALDATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        try {
            gen.writeString(value.format(LOCALDATETIME_FORMATTER));
        } catch (DateTimeParseException e) {
            System.err.println(e);
        }
    }
}
