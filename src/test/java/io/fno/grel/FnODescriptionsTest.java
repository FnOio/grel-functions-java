package io.fno.grel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FnODescriptionsTest {

    private static final Pattern MAPPED = Pattern.compile("fno:function\\s+grel:(\\w+)");
    private static final Pattern DESCRIBED = Pattern.compile("(?m)^grel:(\\w+)\\s+a\\s+fno:Function\\b");

    @Test
    public void everyMappedFunctionIsDescribedOnce() throws IOException {
        List<String> mapped = matches(MAPPED, resource("/grel_java_mapping.ttl"));
        List<String> described = matches(DESCRIBED, resource("/functions_grel.ttl"));

        assertEquals(mapped.size(), new TreeSet<>(mapped).size(), "a function is mapped twice");
        assertEquals(described.size(), new TreeSet<>(described).size(), "a function is described twice");
        assertEquals(new TreeSet<>(mapped), new TreeSet<>(described));
    }

    private static String resource(String name) throws IOException {
        try (InputStream in = FnODescriptionsTest.class.getResourceAsStream(name)) {
            assertNotNull(in, name + " is not on the classpath");
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private static List<String> matches(Pattern pattern, String text) {
        List<String> names = new ArrayList<>();
        Matcher m = pattern.matcher(text);
        while (m.find()) {
            names.add(m.group(1));
        }
        return names;
    }
}
