package com.wokecustoms.prompt.image.post;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MidjourneyClient midjourneyClient;

    public String generatePost(Midjourney midjourney) {
        var response = midjourneyClient.postPromptImage(midjourney);
        //GPT-4 client
        return response;
    }
}
