package com.pinyougou.manager.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.ItemCatService;


import entity.ResponseResult;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbItemCat> findAll(){			
		return itemCatService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo  findPage(Integer pageNum,Integer pageSize){			
		return itemCatService.findPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/add")
	public ResponseResult add(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.add(itemCat);
			return new ResponseResult(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/update")
	public ResponseResult update(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.update(itemCat);
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
	public TbItemCat findOne(Long id){
		return itemCatService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResponseResult delete(Long [] ids){
		try {
			itemCatService.delete(ids);
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
	public PageInfo searchPage(@RequestBody TbItemCat itemCat, Integer pageNum, Integer pageSize  ){
		return itemCatService.findPage(itemCat, pageNum, pageSize);		
	}

	@RequestMapping("/findByParentId")
	public List findByParentId(Long parentId){
		return itemCatService.findByParentId(parentId);
	}

}
