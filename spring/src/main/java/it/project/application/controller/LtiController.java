package it.project.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LtiController {

    @PostMapping("/lti-launch")
    public ModelAndView handleLtiLaunch(HttpServletRequest request) {
        // TODO: OAuth
        // TODO: LTI Parameters

        return new ModelAndView("ltiView");
    }
}
