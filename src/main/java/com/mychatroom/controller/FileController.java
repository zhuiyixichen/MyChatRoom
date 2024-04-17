package com.mychatroom.controller;

import com.mychatroom.config.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashSet;

/**
 * 文件下载与上传
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @PostMapping("/upload")
    public Result<String> fileUpload(@RequestBody  MultipartFile multipartFile){
        log.info("文件上传");
        //获取文件名称
        String file = multipartFile.getOriginalFilename();

        //文件上传路径
        String uploadPath = "E:\\MyChatRoom\\images\\";
        //检测上传目录是否存在
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()){
            uploadFile.mkdirs();
        }

        //添加可上传文件类型
        HashSet<String> set = new HashSet<>();
        set.add("png");
        set.add("jpg");
        set.add("webp");
        set.add("jpeg");
        set.add("gif");

        //遍历文件名，取出后缀名
        String[] fileArray = file.split("\\.");
//        if (file != null) {
//            fileArray = file.split(".");
//
//        }else{
//            fileArray = new String[1];
//        }
        String fileType = fileArray[fileArray.length - 1];
        String fileName = fileArray[0];

        if (!(set.contains(fileType))){
            return Result.error("暂时无法上传此类型：" + fileType);
        }

        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        long currentTimeMillis = System.currentTimeMillis();
        String currentTime = sd.format(currentTimeMillis);

        fileName = fileName + currentTime;

        String uploadFilePath = uploadPath + fileName + "." + fileType;

        //上传文件
        try {
            File dest = new File(uploadFilePath);
            multipartFile.transferTo(dest);
            log.info("上传成功 " + uploadFilePath);
            return Result.success(uploadFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }
    }

    @GetMapping("/download")
    public String FileDownload(String path){
        String image = "data:image/jpeg;base64,";
        if (path == null || path == ""){
            return "";
        }
        try {
            File imageFile = new File(path);
            byte[] bytes = Files.readAllBytes(imageFile.toPath());
            return image + Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return "";
        }
    }
}
