package org.ska.api.fake;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 13/09/18.
 */
@Controller
public class RedirectController {

    @GetMapping("/ui")
    public RedirectView redirectUI(
            RedirectAttributes attributes) {

        return new RedirectView("index.html");
    }

    @GetMapping("/")
    public RedirectView redirectIndex(
            RedirectAttributes attributes) {

        return new RedirectView("index.html");
    }
}
