package com.wokecustoms.prompt.image.post;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    @Value("${midjourney.bearer-token}")
    private static String BEARER_TOKEN;

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity<String> get() {
        return new ResponseEntity<>(BEARER_TOKEN, HttpStatus.OK);
    }

    @PostMapping("/generate-post")
    public ResponseEntity<String> generatePost(@RequestBody String prompt) {
        Midjourney midjourney = new Midjourney();
        midjourney.setPrompt(prompt);

        var response = postService.generatePost(midjourney);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
