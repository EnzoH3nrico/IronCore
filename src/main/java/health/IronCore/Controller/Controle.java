package health.IronCore.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ironCore")
public class Controle {

    @GetMapping("/oi")
    public String oi(){
        return "oi";
    }
}
