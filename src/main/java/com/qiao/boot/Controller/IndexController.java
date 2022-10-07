package com.qiao.boot.Controller;

import com.qiao.boot.FileUtils.FileUtils;
import com.qiao.boot.Pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 乔世伟
 * @Project_Nmae: spring-boot-web1
 * @Description:
 * @date 2022/10/4 19:29
 */
@Slf4j
@Controller
public class IndexController {
    @Value("${upload.uploadPath}")
    private String uploadPath;

    @GetMapping({"/", "/login"})
    public String toLogin() {

        return "login";
    }

    @GetMapping("/table")
    public String totable(Model model) {
        String[] allFile = FileUtils.getAllFile(new File(uploadPath));
//        log.info("取到的文件名={}",allFile.length);
        List<String> webFileNames = new ArrayList<>();

        for (String s : allFile) {
            if (s != null){
                String webFileName = s.substring(s.lastIndexOf("\\")+1);
//                log.info("截取后文件名是={}",webFileName);
                webFileNames.add(webFileName);
            }
        }
        model.addAttribute("fileNames",webFileNames);
        return "files";
    }


    @PostMapping({"/", "/login"})
    public String login(String username, String password, HttpSession session, Model model) {
        if ((!username.isEmpty()) && "12345".equals(password)) {
//            model.addAttribute("loginUser", new User(username, password));
            session.setAttribute("loginUser",new User(username,password));
            session.setAttribute("errorMsg","");
            return "redirect:/main.html";
        } else {
            session.setAttribute("errorMsg", "账号或者密码错误");
            model.addAttribute("userName", username);
            return "login";
        }
    }
    @GetMapping ("/main.html")
    public String goIndex(){

        return  "index";
    }


}
