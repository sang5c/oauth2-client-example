package com.example.googleoauth.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OAuthController {

    private static final Logger log = LoggerFactory.getLogger(OAuthController.class);

    private final OAuthService oAuthService;

    public OAuthController(OAuthService oAuthService) {this.oAuthService = oAuthService;}

    @GetMapping("/")
    public String homeHTML() {
        return "home";
    }

    @ResponseBody
    @GetMapping("/example")
    public ResponseEntity<String> redirect(@RequestParam String code) throws JsonProcessingException {
        return ResponseEntity.ok(oAuthService.googleCalendarList(code));
    }

}
