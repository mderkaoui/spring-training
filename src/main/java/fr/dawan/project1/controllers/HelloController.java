package fr.dawan.project1.controllers;

import fr.dawan.project1.Project1Application;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Controleur de base pour apprendre le mapping de requêtes et de paramètre
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private static Logger myLogger = LoggerFactory.getLogger(HelloController.class);

    private static Logger myLogger2 = LoggerFactory.getLogger("accessLogger");

    //@RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "text/plain")
    @GetMapping(produces = { "text/plain" })
    public String hello(HttpServletRequest request){

        myLogger.info("call hello from "+ request.getRemoteAddr());
        return "Hello from web service";
    }

    @GetMapping(value = "/greeting2", produces = { "text/plain" })
    public String hello2(){
        return "Hello2 from web service";
    }



}
