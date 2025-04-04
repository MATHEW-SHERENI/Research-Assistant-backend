package com.researchAssistant.cloudmathew;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/research")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ResearchController {

    private final ResearchService researchService;

    @PostMapping("/process")
    ResponseEntity<String> processContent(@RequestBody ResearchRequest  request){
        String result = researchService.processContent(request);
        return ResponseEntity.ok(result);
    }
}
