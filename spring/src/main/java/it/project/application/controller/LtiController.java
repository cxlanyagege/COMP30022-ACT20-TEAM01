/**
 * Class Name: LtiController
 * Description: Controller for handling Lti launch
 * 
 * Author: He Shen
 * Date: 2023/9/21
 */

package it.project.application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping("/lti")
public class LtiController {

    @Value("${lti.consumerKey}")
    private String expectedConsumerKey;

    @Value("${lti.sharedSecret}")
    private String sharedSecret;

    @PostMapping("/launch")
    public String handleLtiLaunch(HttpServletRequest request, HttpServletResponse response) 
        throws UnsupportedEncodingException, 
        NoSuchAlgorithmException, InvalidKeyException {

        // Get consumer key
        String consumerKey = request.getParameter("oauth_consumer_key");

        // Validate consumer Key
        if (!expectedConsumerKey.equals(consumerKey)) {
            return "Invalid Consumer Key";
        }

        // Start generating oauth signature from canvas
        // Generate base string from post
        StringBuilder baseString = new StringBuilder();
        baseString.append("POST&");
        baseString.append(URLEncoder.encode(request.getRequestURL().toString(), "UTF-8"));
        baseString.append("&");

        // Sort and append post items in tree
        Map<String, String[]> sortedParams = new TreeMap<>(request.getParameterMap());
        StringBuilder paramString = new StringBuilder();
        for (Map.Entry<String, String[]> entry : sortedParams.entrySet()) {

            // original signature is excepted
            if (entry.getKey().equals("oauth_signature")) {
                continue;
            }

            // Encode with space and connections between each pair
            paramString.append(URLEncoder.encode(entry.getKey(), "UTF-8").replace("+", "%20"));
            paramString.append("=");
            paramString.append(URLEncoder.encode(entry.getValue()[0], "UTF-8").replace("+", "%20"));
            paramString.append("&");
        }

        // Encode again to reduce redundant signs
        paramString.deleteCharAt(paramString.length() - 1);
        baseString.append(URLEncoder.encode(paramString.toString(), "UTF-8"));

        // Use HmacSHA1 to sign shared secret key
        SecretKeySpec signingKey = new SecretKeySpec((sharedSecret + "&").getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);

        // Merge with previous processed base string
        byte[] rawHmac = mac.doFinal(baseString.toString().getBytes());
        String calculatedSignature = Base64.getEncoder().encodeToString(rawHmac);

        // Compare with original signature from canvas
        String incomingSignature = request.getParameter("oauth_signature");
        if (calculatedSignature.equals(incomingSignature)) {
            try {
                response.sendRedirect("/index.html");
            } catch (IOException e) {
                e.printStackTrace();
                return "Redirect failed";
            }
            return "Signature matched, authorized complete";
        } else {
            return "Local signature: " + calculatedSignature + "\n" + 
                   "Remote signature: " + incomingSignature + "\n" +
                   "Signature not matched";
        }

    }
}
