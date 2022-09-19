package com.qfedu.fmmall.dao;

import com.qfdu.fmmall.general.GeneralDao;
import com.qfedu.fmmall.entity.IndexImg;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IndexImgMapper extends GeneralDao<IndexImg> {
    public List<IndexImg> listIndexImgs();
}