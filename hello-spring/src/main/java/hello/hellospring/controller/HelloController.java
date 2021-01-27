package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HelloController {
    @GetMapping("hello") //web app에서 /hello로 들어오면 해당 메소드 호출
    public String hello(Model model){
        model.addAttribute("data", "hello!!");

        return "hello"; //resources/templates/hello.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);

        return "hello-template";
    }
    
    @GetMapping("hello-string")
    @ResponseBody //http의 body부분에 리턴의 값을 데이터로 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "안녕하세요 " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);

        return hello; //객체가 리턴되면 JSON, String이 리턴되면 String을 클라이언트에게 전송
    }

    static class Hello{
        private String name;
        
        public void setName(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }
}
