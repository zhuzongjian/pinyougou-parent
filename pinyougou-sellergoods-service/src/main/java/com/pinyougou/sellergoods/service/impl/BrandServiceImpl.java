package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }

    /*//这是pageHelper 4.0.0之前的做法
     * @Override public PageResult findPage(Integer pageNum,Integer pageSize) {
     * PageHelper.startPage(pageNum,pageSize); Page<TbBrand> page=(Page<TbBrand>)
     * brandMapper.selectByExample(null); return new
     * PageResult(page.getTotal(),page); }
     */
    @Override
    public List<TbBrand> findPage1(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(null);
    }

    @Override
    public PageInfo findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<TbBrand>(brandMapper.selectByExample(null));
    }

    @Override
    public void add(TbBrand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delBrand(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageInfo searchPage(TbBrand brand, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (brand.getName() != null && brand.getName().length() > 0) {
            criteria.andNameLike("%" + brand.getName() + "%");
        }
        if (brand.getFirstChar() != null && brand.getFirstChar().length() > 0) {
            criteria.andFirstCharLike("%" + brand.getFirstChar() + "%");
        }
        return new PageInfo<TbBrand>(brandMapper.selectByExample(example));
    }
    /**
     * 查询所有的品牌[{"id": 1, "text": "联想"}, {"id": 3, "text": "三星"}]
     * @return 返回select2下拉列表格式的数据形式
     */
    @Override
    public List<Map> select2BrandList(){
        return brandMapper.select2BrandList();
    }
}
