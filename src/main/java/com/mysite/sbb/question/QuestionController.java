package com.mysite.sbb.question;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model){
        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList",questionList);

        return "question_list";
    }
}
