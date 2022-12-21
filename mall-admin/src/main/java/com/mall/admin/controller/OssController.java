package com.mall.admin.controller;

import com.mall.CommonResult;
import com.mall.autoconfig.template.OssTemplate;
import com.mall.exception.ConsumerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public CommonResult<Map<String,String>> upload(MultipartFile[] photoFile){
        Map map = new HashMap();  //图片存储的路径
        if (photoFile!=null && photoFile.length>0){
            //1.实现图片上传
            for (MultipartFile file : photoFile) {
                try{
                    String url = ossTemplate.upload(file.getOriginalFilename(), file.getInputStream());
                    map.put("url",url);
                }catch (IOException e){
                   throw new ConsumerException("上传失败");
                }
            }
        }
        return CommonResult.success(map);
    }
}
