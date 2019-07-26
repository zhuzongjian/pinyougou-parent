package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        System.out.println("========================");
        return brandService.findAll();
    }
    /*
     * //分页插件4.0.0做法
     *
     * @RequestMapping("/findPage") public PageResult findPage(Integer
     * pageNum,Integer pageSize) { return brandService.findPage(pageNum,pageSize); }
     */

    // 分页插件5.1.2做法 数据只有一个list,错误写法
    @RequestMapping("/findPage1")
    public PageInfo findPage1(Integer pageNum, Integer pageSize) {
        List<TbBrand> tbBrandList = brandService.findPage1(pageNum, pageSize);
        return new PageInfo<>(tbBrandList);
    }
    // 分页插件5.1.2 service层包装数据ok
    @RequestMapping("/findPage")
    public PageInfo findPage(Integer pageNum, Integer pageSize) {
        return brandService.findPage(pageNum, pageSize);
    }

    @RequestMapping("/add")
    public ResponseResult add(@RequestBody TbBrand brand){
        try {
            brandService.add(brand);
            return new ResponseResult(true, "新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false, "新增失败");
        }
    }

    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }
    @RequestMapping("/update")
    public ResponseResult update(@RequestBody TbBrand brand){
        try {
            brandService.update(brand);
            return new ResponseResult(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false, "修改失败");
        }
    }
    @RequestMapping("/delBrand")
    public ResponseResult delBrand(Long[] ids){
        try {
            brandService.delBrand(ids);
            return new ResponseResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false, "删除失败");
        }
    }
    @RequestMapping("/searchPage")
    public PageInfo searchPage(@RequestBody TbBrand brand, Integer pageNum, Integer pageSize) {
        if (brand == null) {
            return brandService.findPage(pageNum, pageSize);
        }
        return brandService.searchPage(brand, pageNum, pageSize);
    }

    /**
     * 查询所有的品牌[{"id": 1, "text": "联想"}, {"id": 3, "text": "三星"}]
     * @return 返回select2下拉列表格式的数据形式
     */
    @RequestMapping("/select2BrandList")
    public List<Map> select2BrandList(){
        return brandService.select2BrandList();
    }
}
