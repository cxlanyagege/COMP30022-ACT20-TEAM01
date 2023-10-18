package com.example.it.project.controllers;

import com.example.it.project.pojo.Student;
import com.example.it.project.pojo.Subject;
import com.example.it.project.service.IStudentService;
import com.example.it.project.service.ISubjectService;
import com.example.it.project.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/lti")
public class LTIController {
    @Value("${lti.consumerKey}")
    private String expectedConsumerKey;

    @Value("${lti.sharedSecret}")
    private String sharedSecret;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private JwtUtil jwtUtil;

    private static final String CODED_FORM = "UTF-8";

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
        baseString.append(URLEncoder.encode(request.getRequestURL().toString(), CODED_FORM));
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
            paramString.append(URLEncoder.encode(entry.getKey(), CODED_FORM).replace("+", "%20"));
            paramString.append("=");
            paramString.append(URLEncoder.encode(entry.getValue()[0], CODED_FORM).replace("+", "%20"));
            paramString.append("&");
        }

        // Encode again to reduce redundant signs
        paramString.deleteCharAt(paramString.length() - 1);
        baseString.append(URLEncoder.encode(paramString.toString(), CODED_FORM));

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

            // Store user info from lti post into jwt token
            Long id = Long.valueOf(request.getParameter("custom_canvas_user_id"));
            String name = request.getParameter("lis_person_name_full");
            String email = request.getParameter("lis_person_contact_email_primary");
            if (studentService.getById(id) == null){
                studentService.save(new Student(id, name, email, true, true, true));
            }

            // Store subject course information
            Long subjectId = Long.valueOf(request.getParameter("custom_canvas_course_id"));
            String subjectName = request.getParameter("context_label");
            if (subjectService.getById(subjectId) == null){
                subjectService.save(new Subject(subjectId, subjectName));
            }

            // Generate jwt token
            String jwt = jwtUtil.generateToken(id, name, email, subjectId, subjectName);

            // Launch StuRequestHub dashboard
            try {
                response.sendRedirect("/#/login?jwt=" + jwt);
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
