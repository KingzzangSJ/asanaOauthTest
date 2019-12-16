package com.example.demo;

import com.asana.Client;
import com.asana.OAuthApp;
import com.asana.models.Project;
import com.asana.models.Task;
import com.asana.models.User;
import com.asana.models.Workspace;
import com.asana.requests.CollectionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class ApiController2 {
    private static final java.util.UUID UUID = new UUID(-1L,-2L);
    private final String CLIENT_ID = "1153927168756986";
    private  final String CLIENT_SECRET= "c287786b8f4d48d039450a00fff14b91";
    private  final String REDIRECT_URI =  "http://localhost:8080/home";
    private  final  String state = UUID.randomUUID().toString();
    @GetMapping(value = "/s")
    public String test(HttpServletRequest request) throws IOException {
        OAuthApp app = new OAuthApp(
                CLIENT_ID,
                CLIENT_SECRET,
                REDIRECT_URI
        );
        String url = app.getAuthorizationUrl(state);
        boolean auth = app.isAuthorized();
        return "redirect:" + url;
    }

    @GetMapping(value = "/home")
    public void doo(HttpServletRequest request) throws  IOException{
        String token = "";
        OAuthApp app = new OAuthApp(
                CLIENT_ID,
                CLIENT_SECRET,
                REDIRECT_URI
        );
        log.info("state:" + request.getParameter("state"));
        if (request.getParameter("state").equals(state)) {
            token = app.fetchToken(request.getParameter("code"));
            boolean auth =  app.isAuthorized();
        } else {
            log.info("else");
        }
        app = new OAuthApp(
                CLIENT_ID,
                CLIENT_SECRET,
                REDIRECT_URI,
                token
        );
        Client client = Client.oauth(app);
        User me = client.users.me().execute();
        Workspace personalProjects = null;
        List<Project> projects = client.projects.findAll().execute();

        for (Workspace workspace : client.workspaces.findAll()) {
            if (workspace.name.equals("Personal Projects")) {
                personalProjects = workspace;
                break;
            }
        }


        log.info(me.name + ":" + me.gid);

    }
}
