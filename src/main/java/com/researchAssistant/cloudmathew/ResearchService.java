package com.researchAssistant.cloudmathew;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

@Service
public class ResearchService {
    @Value("${gemini.api.url}")
    private String geminiApiurl;

    @Value("${gemini.api.key}")
    private  String geminiApiKey;

    private final  WebClient webClient;
    private final ObjectMapper objectMapper;

    public ResearchService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    public String processContent(ResearchRequest request) {
        //BUILD THE PROMPT
        String prompt = buildPrompt(request);
        // AND QUERY THE AI ie GEMINI MODEL
        Map<String, Object> requestBody = Map.of(

                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                            Map.of("text",prompt)
                })
                }
        );
        String response = webClient.post()
                .uri(geminiApiurl+geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //PARSE THE RESPONSE
        // AND RETURN THE RESPONSE
        return extractTextFromResponse(response);

    }

    private String extractTextFromResponse(String response) {
        try {
   GeminiResponse geminiResponse = objectMapper.readValue(response, GeminiResponse.class);
        if (geminiResponse.getCandidates()!=null && !geminiResponse.getCandidates().isEmpty() ){
            GeminiResponse.Candidate firstCandidate =geminiResponse.getCandidates().get(0);
            if(firstCandidate.getContent()!=null &&
                    firstCandidate.getContent().getParts() != null &&
            !firstCandidate.getContent().getParts().isEmpty()){
               return firstCandidate.getContent().getParts().get(0).getText();
            }
        }
        return "no content found";

        }catch (Exception e){
            return "error Parsing:" + e.getMessage();
        }
    }


    private  String buildPrompt(ResearchRequest request){
        StringBuilder prompt= new StringBuilder();
        switch (request.getOperation()){
            case "summarize":
                prompt.append("provide a clear and concise summary of the following text in few bullet points:\n\n");
                 break;

            case "suggest":
                prompt.append("suggest topics in point form based on the provided text:\n\n");
                break;
            default:
                throw new IllegalArgumentException("UNKNOWN REQUEST "+request.getOperation());
        }
        prompt.append(request.getContent());
        return prompt.toString();
    }
}
