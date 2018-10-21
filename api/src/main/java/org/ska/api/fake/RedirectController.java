package org.ska.api.fake;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 13/09/18.
 */
@Controller
public class RedirectController {

    @RequestMapping("/ui/")
    public String welcome(Map<String, Object> model) {
        return "index.html";
    }
}
