package com.pinyougou.sellergoods.service;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbBrand;

import java.util.List;
import java.util.Map;


public interface BrandService {
	public List<TbBrand> findAll();
	
	//4.0.0做法
	//public PageResult findPage(Integer pageNum,Integer pageSize);
	
	//5.1.2做法分页查询
	public List<TbBrand> findPage1(Integer pageNum,Integer pageSize);

	/**
	 * 分页查询新版本
	 * @param pageNum 当前页
	 * @param pageSize 当前页的数据量
	 * @return 分页对象包含总数
	 */
    PageInfo findPage(Integer pageNum, Integer pageSize);

    void add(TbBrand brand);

    /**
     *
     * @param id brand id
     * @return brand
     */
    TbBrand findOne(Long id);

    /**
     *
     * @param brand 需要修改的brand
     *
     */
    void update(TbBrand brand);

    /**
     *
     * @param ids 需要删除的brandId数组
     */
    void delBrand(Long[] ids);

    /**
     *条件查询
     * @param brand 条件查询条件
     * @param pageNum 当前页
     * @param pageSize 每页数据量
     * @return 查询的分页
     */
    PageInfo searchPage(TbBrand brand, Integer pageNum, Integer pageSize);

    /**
     * 查询所有的品牌[{"id": 1, "text": "联想"}, {"id": 3, "text": "三星"}]
     * @return 返回select2下拉列表格式的数据形式
     */
    List<Map> select2BrandList();
}
