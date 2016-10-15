package com.github.sadstool.springdurationconverter.duration;

import com.github.sadstool.springdurationconverter.duration.error.SimpleDurationFormatException;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SimpleDuration {
    private static final Map<String, TimeUnit> UNITS = new HashMap<String, TimeUnit>() {{
        put("ms", TimeUnit.MILLISECONDS);
        put("s", TimeUnit.SECONDS);
        put("m", TimeUnit.MINUTES);
        put("h", TimeUnit.HOURS);
        put("d", TimeUnit.DAYS);
    }};

    private static final String VALUE_GROUP_NAME = "value";
    private static final String UNIT_GROUP_NAME = "unit";
    private static final String PARSE_PATTERN_FORMAT = "(?<%s>[0-9]+)(?<%s>ms|s|m|h|d)";
    private static final String PARSE_REGEX = String.format(PARSE_PATTERN_FORMAT, VALUE_GROUP_NAME, UNIT_GROUP_NAME);
    private static final Pattern PARSE_PATTERN = Pattern.compile(PARSE_REGEX);

    private final long value;
    private final TimeUnit unit;

    public SimpleDuration(long value, TimeUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public SimpleDuration(long value) {
        this(value, TimeUnit.MILLISECONDS);
    }

    public Long toMillis() {
        return this.unit.toMillis(this.value);
    }

    public Long toSeconds() {
        return this.unit.toSeconds(this.value);
    }

    public Long toMinutes() {
        return this.unit.toMinutes(this.value);
    }

    public static SimpleDuration valueOf(String string) throws NumberFormatException {
        if (!StringUtils.hasText(string)) {
            throw new SimpleDurationFormatException("Null or empty string cannot be converted to duration.");
        }

        Matcher matches = PARSE_PATTERN.matcher(string.trim());
        if (!matches.matches()) {
            throw new SimpleDurationFormatException("Invalid duration format: " + string);
        }

        Long value = Long.valueOf(matches.group(VALUE_GROUP_NAME));
        String unit = matches.group(UNIT_GROUP_NAME);

        return new SimpleDuration(value, UNITS.get(unit));
    }
}
