package com.example.desafio.service.received.password.mail.body.build.html;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BuildBodyInHtml{
    public String executeBuild(String token){
        log.debug("✅initializing body building in html");
        return "<html>\n" +
                "  <head> \n" +
                "  </head> \n" +
                "  <body style=\"background-color:red\">\n" +
                "     <h1 style=\"color:white;text-align:center\">Token Recover Password</h1> \n" +
                "     <div style=\"border:2px solid black;background-color:black;outline:2px solid white\"> \n" +
                "     <h1 style=\"text-align:center;color:white;\">"+token+"</h1> \n" +
                "     </div> \n" +
                "     </body> \n" +
                "     </html>";
    }
}
