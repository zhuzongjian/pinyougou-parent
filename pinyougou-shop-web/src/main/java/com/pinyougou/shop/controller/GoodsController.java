package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojogroup.Goods;
import com.pinyougou.sellergoods.service.GoodsService;
import entity.ResponseResult;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference
	private GoodsService goodsService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbGoods> findAll(){			
		return goodsService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo  findPage(Integer pageNum,Integer pageSize){			
		return goodsService.findPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param goods
	 * @return
	 */
	@RequestMapping("/add")
	public ResponseResult add(@RequestBody Goods goods){
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		goods.getGoods().setSellerId(sellerId);
		try {
			goodsService.add(goods);
			return new ResponseResult(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public ResponseResult update(@RequestBody TbGoods goods){
		try {
			goodsService.update(goods);
			return new ResponseResult(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbGoods findOne(Long id){
		return goodsService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResponseResult delete(Long [] ids){
		try {
			goodsService.delete(ids);
			return new ResponseResult(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param 
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping("/searchPage")
	public PageInfo searchPage(@RequestBody TbGoods goods, Integer pageNum, Integer pageSize  ){
		return goodsService.findPage(goods, pageNum, pageSize);		
	}
	
}
