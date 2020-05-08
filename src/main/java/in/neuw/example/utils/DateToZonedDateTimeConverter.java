package in.neuw.example.utils;

import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Karanbir Singh on 05/07/2020
 */
public class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(Date source) {
        return source == null ? null :
                ZonedDateTime.ofInstant(source.toInstant(), ZoneId.of("Asia/Kolkata"));
    }
}