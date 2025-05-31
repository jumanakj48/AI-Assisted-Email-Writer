package com.email.writer.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailWriterController {

    private final EmailWriterService emailWriterService;

    @PostMapping("/generate")
    public ResponseEntity<String> writeEmail (@RequestBody EmailRequest emailRequest) throws JsonProcessingException {
        String response = emailWriterService.generateEmail(emailRequest);
        return ResponseEntity.ok(response);
    }
}
