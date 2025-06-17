package com.bitly.url_shortner.controllers;

import com.bitly.url_shortner.dto.UrlMappingDTO;
import com.bitly.url_shortner.models.User;
import com.bitly.url_shortner.services.UrlMappingService;
import com.bitly.url_shortner.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
public class UrlMappingController {

    private final UrlMappingService urlMappingService;
    private final UserService userService;

    public UrlMappingController(UrlMappingService urlMappingService, UserService userService) {
        this.urlMappingService = urlMappingService;
        this.userService = userService;
    }

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> shortenUrl(@RequestBody Map<String, String> request,
                                                    Principal principal) {
        String originalUrl = request.get("originalUrl");
        User user = userService.findByUsername(principal.getName());

        return ResponseEntity.ok(urlMappingService.createShortUrl(originalUrl, user));
    }

    @GetMapping("myUrls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUrls(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<UrlMappingDTO> urls = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);
    }
}
