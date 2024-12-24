package org.example.service;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class MustacheService {

    private final MustacheFactory mustacheFactory;

    public MustacheService() {
        this.mustacheFactory = new DefaultMustacheFactory();
    }

    // Method to render Mustache template
    public String renderTemplate(String templateName, Map<String, Object> context) throws IOException {
        ClassPathResource resource = new ClassPathResource("/templates/" + templateName);
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);

        Mustache mustache = mustacheFactory.compile(reader, templateName);
        StringWriter writer = new StringWriter();
        mustache.execute(writer, context);
        return writer.toString();
    }
}
