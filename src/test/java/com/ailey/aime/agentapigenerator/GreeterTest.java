package com.ailey.aime.agentapigenerator;

import com.ailey.aime.agentapigenerator.domain.service.AgentApiGeneratorService;
import com.ailey.aime.agentapigenerator.domain.util.ResourceLoader;
import org.junit.jupiter.api.Test;

import java.util.List;

class GreeterTest {
    
    @Test
    void hello() {
        String result = Greeter.hello();
        System.out.println(result);
    }

    @Test
    void testSnippetLoad() {
        AgentApiGeneratorService service = new AgentApiGeneratorService();
        
        String template = ResourceLoader.loadTemplate();
        //System.out.println(template);
        
        List<String> snippetNames = ResourceLoader.loadSnippetNames();
        System.out.println(String.join(", ", snippetNames));
        
        String finalCode = service.createAgentApiContract(List.of("call_test", "call_test2"));
        if (finalCode == null)
            System.out.println ("empty");
        else
            System.out.println (finalCode);
    }
}
