package com.hasanjahidul.docManagement.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateConversionUtil {

    public static String convertToISO8601UTC(String inputDate) {
        try {
            // Parse the input date to a LocalDate
            LocalDate localDate = LocalDate.parse(inputDate, DateTimeFormatter.ISO_DATE);

            // Convert LocalDate to ZonedDateTime with UTC time zone and time set to 00:00:00
            ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, LocalTime.MIN, ZoneId.of("UTC"));

            // Format the ZonedDateTime to ISO 8601 string in UTC with milliseconds
            return zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
        } catch (Exception e) {
            // Handle any parsing or formatting exceptions here
            System.out.println("Exception in DateConversionUtil.convertToISO8601UTC-> " + e.getMessage());
            return "";
        }
    }

    public static String currentISO8601UTC() {
        // Get the current time in UTC
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));

        // Format the current time to ISO 8601 string in UTC with milliseconds
        return zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
    }
    public static LocalDateTime convertToLocalDateTime(String inputDate) {
        try {
            // Parse the input date to Instant
            Instant instant = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(inputDate));

            // Convert Instant to LocalDateTime
            return LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
        } catch (Exception e) {
            // Handle any parsing or formatting exceptions here
            System.out.println("Exception in DateConversionUtil.convertToISO8601UTC-> " + e.getMessage());
            return null;
        }
    }
}

