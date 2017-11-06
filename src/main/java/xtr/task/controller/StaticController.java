package xtr.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by root on 06.11.2017.
 */
@Controller
public class StaticController {
    @GetMapping("/")
    public String root() {
        return "/index.html";
    }
}
