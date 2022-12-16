package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.autoconfig.template.OssTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aliyun")
public class OssController {

    @Autowired
    private OssTemplate ossTemplate;

    /**
     * 图片上传
     * @param photoFile
     * @return
     */
    @PostMapping("/oss/upload")
    public CommonResult<List<String>> upload(MultipartFile[] photoFile){
        List<String> pathList = new ArrayList<>();  //图片存储的路径

        if (photoFile!=null && photoFile.length>0){
            //1.实现图片上传
            for (MultipartFile file : photoFile) {
                try{
                    String uploadPath = ossTemplate.upload(file.getOriginalFilename(), file.getInputStream());
                    pathList.add(uploadPath);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return CommonResult.success(pathList);
    }
}
