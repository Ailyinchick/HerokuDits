package incubator.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @GetMapping(value = "/chooseTest")
    public String ChooseTest() {
        return "User/UserChoose";
    }

    @GetMapping(value = "/personalStatistic")
    public String personalStatisticPage() {
        return "User/personalStatistic";
    }

}
