package com.mall.admin.service;

import com.mall.CommonPage;
import com.mall.api.admin.SmsHomeRecommendSubjectApi;
import com.mall.pojo.SmsHomeRecommendProduct;
import com.mall.pojo.SmsHomeRecommendSubject;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsHomeRecommendSubjectService {
    @DubboReference
    private SmsHomeRecommendSubjectApi smsHomeRecommendSubjectApi;

    public CommonPage<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        //查询数据到分页的records中返回到service
        List<SmsHomeRecommendSubject> pageInfoList = smsHomeRecommendSubjectApi.list(subjectName, recommendStatus, pageSize, pageNum);
        Integer total = smsHomeRecommendSubjectApi.finTotal(subjectName,recommendStatus);
        CommonPage<SmsHomeRecommendSubject> result = new CommonPage<>();
        //封装5个中的两个
        result.setPageSize(pageSize);
        result.setPageNum(pageNum);
        result.setList(pageInfoList);
        result.setTotal(total);
        Integer totalPage = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        result.setTotalPage(totalPage);
        return result;
    }

    /**
     *删除
     * */
    public int delete(List<Long> ids) {
        if (ids!=null){
            int count = smsHomeRecommendSubjectApi.deleteIds(ids);
            return count;
        }
        return -1;
    }

    public int updateCommend(List<Long> ids, Integer recommendStatus) {
        int count = smsHomeRecommendSubjectApi.updateCommend(ids,recommendStatus);
        return count;
    }

    //修改推荐排序
    public void updateSort(Integer id, Integer sort) {
        smsHomeRecommendSubjectApi.updateSort(id,sort);
    }

    //首页添加新品
    public void create(List<SmsHomeRecommendSubject> smsHomeRecommendSubject) {
        smsHomeRecommendSubjectApi.create(smsHomeRecommendSubject);
    }
}
