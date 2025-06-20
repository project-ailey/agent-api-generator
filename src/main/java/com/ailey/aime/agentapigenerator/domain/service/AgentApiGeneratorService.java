package com.ailey.aime.agentapigenerator.domain.service;

import com.ailey.aime.agentapigenerator.domain.util.ResourceLoader;
import com.ailey.aime.agentapigenerator.domain.util.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentApiGeneratorService {
    
    public String createAgentApiContract(List<String> snippetNameList) {
        if (snippetNameList.isEmpty())
            return null;
        
        String template = ResourceLoader.loadTemplate();
        List<Tuple._2<String, String>> snippets = ResourceLoader.loadSelectedSnippets(snippetNameList);
        
        if (snippets.isEmpty())
            return null;
        
        String combineSnippets = "";
        for (Tuple._2<String, String> s : snippets) {
            combineSnippets = combineSnippets.concat(s._2() + "\n\n\t");
        }
        
        String finalCode = template.replace("{{CUSTOM_BLOCK}}", combineSnippets);

        //System.out.println(finalCode);
        
        return finalCode;
    }
}
