package cn.com.springday3jdbc.component.convert;


import cn.com.springday3jdbc.pojo.SexEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converts;

/**
 * @author yuanmengfan
 * @date 2022/4/6 10:17 下午
 * @description
 */
public class SexConverts implements AttributeConverter<SexEnum,Integer> {


    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer id) {
        return SexEnum.getEnumById(id);
    }
}
