package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVo;

public interface ProductCommentsService {
    public ResultVo listCommentsByProductId(String productId,int start,int limit);
}
