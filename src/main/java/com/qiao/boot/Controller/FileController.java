package com.qiao.boot.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 乔世伟
 * @Project_Nmae: spring-boot-web1
 * @Description:
 * @date 2022/10/5 18:52
 */
@Slf4j
@Controller
public class FileController {

    @Value("${upload.uploadPath}")
    private String uploadPath;

    @GetMapping("/toUpload")
    public String toForm() {

        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email, @RequestParam("password") String password,
                         @RequestPart("file") MultipartFile file, @RequestPart("files") MultipartFile[] files
    ) throws IOException {

        log.info("email={},password={},单个文件={},多个文件数量={}", email, password, file.getSize(), files.length);

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            log.info("文件名:" + fileName);
            //将目标文件对象上传到指定的文件夹 以指定的文件名
            file.transferTo(new File("E:\\" + fileName));
        }

        if (files.length > 0) {
            for (MultipartFile f : files) {
                if (!file.isEmpty()) {
                    log.info("文件名:" + f.getOriginalFilename());
                    f.transferTo(new File(uploadPath + f.getOriginalFilename()));
                }
            }
        }
        return "index";
    }
    @RequestMapping("/downLoad/{path}")
    public ResponseEntity<byte[]> downLoad(@PathVariable("path") String path, HttpSession session) throws IOException {


        String localPath =uploadPath+ path;
//        log.info("uploadPath={}",localPath);
        InputStream inputStream = new FileInputStream(localPath);
        byte[] bts = new byte[inputStream.available()];
        inputStream.read(bts);
        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename="+localPath.substring(localPath.lastIndexOf("\\")+1));
        HttpStatus statuCode = HttpStatus.OK;

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bts, headers, statuCode);
        inputStream.close();
        return  responseEntity;
    }
}
