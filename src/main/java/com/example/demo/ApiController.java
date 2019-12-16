package com.example.demo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.ExportException;

@Controller
@Slf4j
public class ApiController {

    private final String CLIENT_ID = "1153927168756986";
    private  final String CLIENT_SECRET= "c287786b8f4d48d039450a00fff14b91";
    private  final String REDIRECT_URI =  "http://localhost:8080/home";
    @GetMapping(value = "/")
    public String  main(Model model) throws IOException {
//        response.getWriter().print("hello");
        model.addAttribute("CLIENT_ID", CLIENT_ID);
        model.addAttribute("REDIRECT_URI", REDIRECT_URI);
        return "main";
    }

//    @RequestMapping(value = "/home")
    public String  home(HttpServletRequest request, Model model) throws Exception {
        String code = request.getParameter("code");
        model.addAttribute("CLIENT_ID", CLIENT_ID);
        model.addAttribute("CLIENT_SECRET", CLIENT_SECRET);
        model.addAttribute("REDIRECT_URI", REDIRECT_URI);
        model.addAttribute("CODE", code);
        log.info("code: " + code);

        return "home";
    }
}
