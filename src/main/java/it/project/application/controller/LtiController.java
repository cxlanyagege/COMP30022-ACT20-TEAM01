/**
 * Class Name: LtiController
 * Description: Controller for handling Lti launch
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/11/14
 */

package it.project.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.project.application.service.IPositionService;
import it.project.application.service.IStaffService;
import it.project.application.service.IStudentService;
import it.project.application.service.ISubjectService;
import it.project.application.pojo.Student;
import it.project.application.pojo.Staff;
import it.project.application.pojo.Subject;
import it.project.application.pojo.Position;
import it.project.application.util.JwtUtil;
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

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IPositionService positionService;

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

            // LMS user role assignment
            String userRole = request.getParameter("roles");
            if (userRole.contains("Instructor")) {
                // Administrator or subject coordinator

                // Store user info from lti post into jwt token
                Integer id = Integer.valueOf(request.getParameter("custom_canvas_user_id"));
                String name = request.getParameter("lis_person_name_full");
                String email = request.getParameter("custom_canvas_user_login_id");
                if (staffService.getById(id) == null){
                    staffService.save(new Staff(id, name, email, true, true));
                }

                // Store subject course information
                Integer subjectId = Integer.valueOf(request.getParameter("custom_canvas_course_id"));
                String subjectName = request.getParameter("context_label");
                if (subjectService.getById(subjectId) == null){
                    subjectService.save(new Subject(subjectId, subjectName, true, true, true, true, true));
                }

                // Store staff relations and permissions
                if (!positionService.checkExistence(id, subjectId)) {
                    positionService.save(new Position(id, subjectId, "coordinator", true, true, true, true, true));
                }

                // Generate jwt token
                String jwt = jwtUtil.generateToken(id, name, email, subjectId, subjectName);

                // Launch StuRequestHub dashboard
                try {
                    response.sendRedirect("/#/stafflogin?jwt=" + jwt);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Redirect failed";
                }
                return "Signature matched, authorized complete";

            } else if (userRole.contains("TeachingAssistant")) {
                // Tutor or assistant

                // Store user info from lti post into jwt token
                Integer id = Integer.valueOf(request.getParameter("custom_canvas_user_id"));
                String name = request.getParameter("lis_person_name_full");
                String email = request.getParameter("custom_canvas_user_login_id");
                if (staffService.getById(id) == null){
                    staffService.save(new Staff(id, name, email, true, true));
                }

                // Store subject course information
                Integer subjectId = Integer.valueOf(request.getParameter("custom_canvas_course_id"));
                String subjectName = request.getParameter("context_label");
                if (subjectService.getById(subjectId) == null){
                    subjectService.save(new Subject(subjectId, subjectName, true, true, true, true, true));
                }

                // Store staff relations and permissions
                if (!positionService.checkExistence(id, subjectId)) {
                    positionService.save(new Position(id, subjectId, "tutor", true, true, true, true, true));
                }

                // Generate jwt token
                String jwt = jwtUtil.generateToken(id, name, email, subjectId, subjectName);

                // Launch StuRequestHub dashboard
                try {
                    response.sendRedirect("/#/tutorlogin?jwt=" + jwt);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Redirect failed";
                }
                return "Signature matched, authorized complete";

            } else if (userRole.contains("Learner")){
                // Student

                // Store user info from lti post into jwt token
                Integer id = Integer.valueOf(request.getParameter("custom_canvas_user_id"));
                String name = request.getParameter("lis_person_name_full");
                String email = request.getParameter("custom_canvas_user_login_id");
                if (studentService.getById(id) == null){
                    studentService.save(new Student(id, name, email, true, true, true));
                }

                // Store subject course information
                Integer subjectId = Integer.valueOf(request.getParameter("custom_canvas_course_id"));
                String subjectName = request.getParameter("context_label");
                if (subjectService.getById(subjectId) == null){
                    subjectService.save(new Subject(subjectId, subjectName, true, true, true, true, true));
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
                // Observer

            }

            // Role assignment failed
            return "Signature matched, but role assignment failed";

        } else {
            return "Local signature: " + calculatedSignature + "\n" + 
                   "Remote signature: " + incomingSignature + "\n" +
                   "Signature not matched";
        }

    }
}
