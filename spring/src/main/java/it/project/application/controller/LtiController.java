/**
 * Class Name: LtiController
 * Description: Controller for handling lti requests
 * 
 * Author: He Shen
 * Date: 2023/8/16
 * 
 * Note: Lti is still under construction
 */

package it.project.application.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import it.project.application.service.LtiService;
// import jakarta.servlet.http.HttpServletRequest;

// @RestController
// @RequestMapping("/lti")
// public class LtiController {

//     @Autowired
//     private LtiService ltiService;

//     @PostMapping("/launch")
//     public ResponseEntity<?> ltiLaunch(HttpServletRequest request) {
//         if (ltiService.verifyLtiRequest(request)) {
//         // Success
//         return new ResponseEntity<>("LTI launch successful", HttpStatus.OK);
//         } else {
//         // Failed
//         return new ResponseEntity<>("Invalid LTI launch request", HttpStatus.UNAUTHORIZED);
//         }
//     }
  
//     // Other Lti related coms
// }
