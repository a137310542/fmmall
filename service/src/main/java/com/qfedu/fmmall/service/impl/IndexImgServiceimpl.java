package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.IndexImgMapper;
import com.qfedu.fmmall.entity.IndexImg;
import com.qfedu.fmmall.service.IndexImgService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
@Service
public class IndexImgServiceimpl implements IndexImgService {
    @Resource
    private IndexImgMapper indexImgMapper;
    @Override
    public ResultVo listIndexImgs() {
        List<IndexImg> indexImgs = indexImgMapper.listIndexImgs();
        if(indexImgs.size()==0){
            return  new ResultVo(ResStatus.no,"fail",indexImgs);
        }else {
            return  new ResultVo(ResStatus.ok,"successful",indexImgs);
        }


    }
}
