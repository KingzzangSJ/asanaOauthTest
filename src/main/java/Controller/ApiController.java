package Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ApiController {

    @RequestMapping(value = "/")
    public void  main(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello");
    }

    @RequestMapping(value = "/code")
    public void  getCode() {

    }
}
