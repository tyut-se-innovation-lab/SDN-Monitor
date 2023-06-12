package team.sdn.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sdn.model.service.AttackService;

@RestController
@RequestMapping("/attack")
public class OutputParam {
    @Autowired
    private AttackService service;
    @PostMapping("/predict")
    public String sendParam(){
        return "";
    }
}
