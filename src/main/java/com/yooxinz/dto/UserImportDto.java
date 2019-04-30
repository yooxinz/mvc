package com.yooxinz.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-04-30
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserImportDto extends BaseRowModel {

    /** 姓名 */
    @ExcelProperty(index = 0)
    private String name;

    /** 生日 */
    @ExcelProperty(index = 1)
    private String birthday;

}
