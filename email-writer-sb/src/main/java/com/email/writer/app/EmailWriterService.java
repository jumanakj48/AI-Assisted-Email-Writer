package com.email.writer.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class EmailWriterService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiURL;

    @Value("${gemini.api.key}")
    private String geminiAPIkey;

    public EmailWriterService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateEmail(EmailRequest emailRequest) throws JsonProcessingException {
        //build Prompt
        String prompt = buildPrompt(emailRequest);

        //Frame the Request Body
        Map<String, Object> body = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                })
            }
        );

        //Generate
        String response = webClient.post()
                .uri(geminiURL + geminiAPIkey)
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //return
        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) throws JsonProcessingException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            return rootNode
                    .path("candidates").get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text")
                    .asText();

        }
        catch (Exception e){
            return "Error processing request: " + e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a Professional Reply Mail to the following email. Please don't generate a subject line\n");
        if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone\n");
        }
        prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
