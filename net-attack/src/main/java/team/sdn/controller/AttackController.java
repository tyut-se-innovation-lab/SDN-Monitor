package team.sdn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sdn.service.impl.AttackService;

@RestController
@RequestMapping("/attack")
public class AttackController {
    @Autowired
    private AttackService service;

    @PostMapping("/classify")
    public String showClass() {
        return "";
    }

    @PostMapping("/classDetail")
    public String showClassDetail() {
        return "";
    }

    @PostMapping("/netAttack")
    public String getAttackOfNetwork() {
        return "";
    }
}
