package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class getApiController {

    @GetMapping(path = "/hello")
    public String Hello() {
        return "get Hello";
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET)//get
    public String hi() {
        return "hi";
    }

    //    @GetMapping("/path-variable/{name}")
//    //변수에 문자열 이름을 받아서
//    public String pathVariable(@PathVariable String name){
//        //이름을 뭘로 받았는지 콘솔에 찍고
//        System.out.println("PathVariable :" + name);
//        //이름을 리턴해줌
//        return name;
//    }
    //2방법 위에와 같음
    @GetMapping("/path-variable/{name}")
    //{name} = @PathVariable(name="name")을 String pathName에 넣어줘서 밑에 sysout에 찍고 리턴해줌
    public String pathVariable(@PathVariable(name = "name") String pathName) {
        //이름을 뭘로 받았는지 콘솔에 찍고
        System.out.println("PathVariable :" + pathName);
        //이름을 리턴해줌
        return pathName;
    }

    //quaryParameter : 검색을 할때 여러가지 매개변수의 인자를 뜻함,?부터시작 &(엔드연산자)를 기준으로 짤라보면
    //& a = b a는 키, b는 벨류
    //?key = value & key2 = value2~~~~~
    //http:localhost:8282/api/get/query-param?user=kst&email=dlkst@naver.com$age=37

    //1번방법
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();

        //key,value 잘들어오는지 확인,foreach가 반환하는것은 entry()
        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + "=" + entry.getValue() + "\n");
        });

        //map자체를 리턴하면 값 자체가 없음 그래서 stringbuilder에 담아서 반환
        //toString()으로 다시 값을 문자열화 해서 리턴
        return sb.toString();
    }

    //2번방법
    @GetMapping("query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age

            ){

        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name +" "+ email +" "+age;
    }
    //3번방법(현업에서 제일 많이씀 : dto를 만들어서 파라미터값을 따로 관리해서 레이어간 전송
    @GetMapping("query-param03")
    //객체를 생성해서 쿼리파라미터를 바로 맴핑해주는 방법
    //스프링부트 원리 : 어노테이션 대신,객체가 들어오면 쿼리파라미터에 들어오는 주소들(?뒤에있는것들)을 부트에서 판단해서
    //key(변수)에 해당하는 이름들을 해당 객체에 들어가서 변수와 이름 매칭을 해줌(dto)
    public String queryParam03(UserRequest userRequest){

        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();

    }


}
