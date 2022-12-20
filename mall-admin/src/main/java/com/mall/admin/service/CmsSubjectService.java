package com.mall.admin.service;

import com.mall.CommonPage;
import com.mall.api.admin.CmsSubjectApi;
import com.mall.pojo.CmsSubject;
import com.mall.pojo.SmsHomeRecommendProduct;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsSubjectService {
    @DubboReference
    private CmsSubjectApi cmsSubjectApi;
    public CommonPage<CmsSubject> list(String keyword, Integer pageSize, Integer pageNum) {
    //查询数据到分页的records中返回到service
        List<CmsSubject> pageInfoList = cmsSubjectApi.list(keyword, pageSize, pageNum);
        Integer total = cmsSubjectApi.finTotal(keyword);
        CommonPage<CmsSubject> result = new CommonPage<>();
        //封装5个中的两个
        result.setPageSize(pageSize);
        result.setPageNum(pageNum);
        result.setList(pageInfoList);
        result.setTotal(total);
        Integer totalPage = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        result.setTotalPage(totalPage);
        return result;
    }

    public List<CmsSubject> listAll() {
        return  cmsSubjectApi.listAll();
    }
}
