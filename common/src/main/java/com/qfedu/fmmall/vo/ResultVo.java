package com.qfedu.fmmall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "响应的VO对象",description = "封装接口返回前端数据")
public class ResultVo {
    //
    @ApiModelProperty(value = "响应状态码",dataType = "int")
    private int code;
    @ApiModelProperty(value = "响应信息")
    private String msg;
    @ApiModelProperty(value = "响应数据")
    private Object data;
}
