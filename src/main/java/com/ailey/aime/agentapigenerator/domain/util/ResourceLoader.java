package com.ailey.aime.agentapigenerator.domain.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResourceLoader {
    public static String loadTemplate() {
        try {
            InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream("templates/AgentApi.sol.template");
            if (input == null) {
                throw new FileNotFoundException("template not found in resources");
            }

            return new String(input.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> loadSnippetNames() {
        List<String> result = new ArrayList<>();

        try {
            InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream("snippets.xml");
            if (input == null) {
                throw new FileNotFoundException("snippets.xml not found in resources");
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(input);

            NodeList snippetNodes = doc.getElementsByTagName("snippet");
            for (int i = 0; i < snippetNodes.getLength(); i++) {
                Element snippet = (Element) snippetNodes.item(i);
                String name = snippet.getAttribute("name");
                result.add(name);
            }

            return result;
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static List<Tuple._2<String, String>> loadSelectedSnippets(List<String> names) {
        Set<String> targetNames = new HashSet<>(names);

        try {
            List<Tuple._2<String, String>> result = new ArrayList<>();

            InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream("snippets.xml");
            if (input == null) {
                throw new FileNotFoundException("snippets.xml not found in resources");
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(input);

            NodeList snippetNodes = doc.getElementsByTagName("snippet");
            for (int i = 0; i < snippetNodes.getLength(); i++) {
                Element snippet = (Element) snippetNodes.item(i);
                String name = snippet.getAttribute("name");
                if (targetNames.contains(name)) {
                    String content = snippet.getTextContent().trim();
                    result.add(new Tuple._2<>(name, content));
                } else {
                    return new ArrayList<>(); // Fail if any are missing
                }
            }

            return result;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
