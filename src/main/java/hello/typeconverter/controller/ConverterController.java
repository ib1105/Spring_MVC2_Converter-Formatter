package hello.typeconverter.controller;
import hello.typeconverter.type.IpPort;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ConverterController {
    @GetMapping("/converter-view")
    public String converterView(Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));
        return "converter-view";
    }

    @GetMapping("/converter/edit")
    public String converterForm(Model model) {
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        log.info("ipPort0 = {}",ipPort);
        log.info("1");
        Form form = new Form(ipPort);
        log.info("2");
        model.addAttribute("form", form);
        log.info("3");
        return "converter-form";
    }

    @PostMapping("/converter/edit")
    public String converterEdit(@ModelAttribute Form form, Model model) {
        IpPort ipPort = form.getIpPort();
        log.info("bbb");
        model.addAttribute("ipPort", ipPort);
        log.info("ccccc");
        return "converter-view";
    }

    @Data
    static class Form {
        private IpPort ipPort;
        public Form(IpPort ipPort) {
            log.info("ipPort1 = {}", ipPort);
            this.ipPort = ipPort;
            log.info("ipPort2 = {}", ipPort);
        }
    }
}