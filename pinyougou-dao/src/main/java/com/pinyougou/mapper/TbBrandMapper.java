package com.pinyougou.mapper;

import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbBrandMapper {
    int countByExample(TbBrandExample example);

    int deleteByExample(TbBrandExample example);

    int deleteByPrimaryKey(Long id);

    /**
     * 存储数据
     * @param record 不区分null的插入
     * @return
     */
    int insert(TbBrand record);

    /**
     *存储数据
     * @param record null的属性不插入
     * @return
     */
    int insertSelective(TbBrand record);

    List<TbBrand> selectByExample(TbBrandExample example);

    TbBrand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbBrand record, @Param("example") TbBrandExample example);

    int updateByExample(@Param("record") TbBrand record, @Param("example") TbBrandExample example);

    int updateByPrimaryKeySelective(TbBrand record);

    int updateByPrimaryKey(TbBrand record);

    /**
     * 查询所有的品牌[{"id": 1, "text": "联想"}, {"id": 3, "text": "三星"}]
     * @return 返回select2下拉列表格式的数据形式
     */
    List<Map> select2BrandList();
}