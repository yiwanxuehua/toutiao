package com.nowcoder.controller;

import com.nowcoder.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping(path = {"/","/index"})
    @ResponseBody
    public String index(){
        return "hello newCoder";
    }

    @RequestMapping(value = {"/profiles/{GroupId}/{UserId}"})
    @ResponseBody
    public String profiles(@PathVariable("GroupId") String GroupId, //解析地址
                           @PathVariable("UserId")int UserId,
                           @RequestParam(value = "type",defaultValue="1")int type, //解析参数
                           @RequestParam(value = "key",defaultValue="newCoder")String key){
        return String.format("GroupId[%s],UserId[%d],Type[%d],Key[%s]",GroupId,UserId,type,key);//返回参数
    }

    @RequestMapping(value = {"/vm"}) //指定模板输出；
    public String temp(Model model){
        model.addAttribute("value1","vv1"); //通过vm中使用$!{value1}来显示,必须加！号；
        List<String> colors = Arrays.asList(new String[]{"RED", "GREEN", "BLUE"});//通过foreach end进行取值；
        Map<String, String> map = new HashMap<String, String>();//通过keySet进行取值；
        for (int i = 0; i < 4; ++i) {
            map.put(String.valueOf(i), String.valueOf(i * i));
        }
        model.addAttribute("colors", colors);
        model.addAttribute("map", map);
        model.addAttribute("user",new User("wang"));
        return "temp";
    }
}
