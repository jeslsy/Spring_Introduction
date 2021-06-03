package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // html이 아닌 문자 그대로 내려줌
    // 페이지 소스 보면 html 없이 name만 그대로 있음
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        // 1번. http 응답에 넣어서 바로 템플릿 고고(2번으로)
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    // 2번. 그런데 객체가 온다?
    // 객체를 반환하면서 ResponseBody하면 json이 default
    public Hello helloApi(@RequestParam("name") String name){
        // ctrl + shift + enter 하면 자동 완성 바로 해줌
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // localhost:8080/hello-api?name=spring!!! 하면 {"name" : "spring!!!"}으로 나옴(json 방식) ㄹㄱㄴ
   }

    // static -> HelloController.Hello 이런식으로 쓸 수 있음
    static class Hello{
        private String name;

        // 이 겟셋(get,set)이 자바 빈 규약
        // property 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
