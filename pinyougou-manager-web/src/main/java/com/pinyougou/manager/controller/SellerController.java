package com.pinyougou.manager.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;


import entity.ResponseResult;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference
	private SellerService sellerService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbSeller> findAll(){			
		return sellerService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo  findPage(Integer pageNum,Integer pageSize){			
		return sellerService.findPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param seller
	 * @return
	 */
	@RequestMapping("/add")
	public ResponseResult add(@RequestBody TbSeller seller){
		try {
			sellerService.add(seller);
			return new ResponseResult(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param seller
	 * @return
	 */
	@RequestMapping("/update")
	public ResponseResult update(@RequestBody TbSeller seller){
		try {
			sellerService.update(seller);
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
	public TbSeller findOne(String id){
		return sellerService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResponseResult delete(String [] ids){
		try {
			sellerService.delete(ids);
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
	public PageInfo searchPage(@RequestBody TbSeller seller, Integer pageNum, Integer pageSize  ){
		return sellerService.findPage(seller, pageNum, pageSize);		
	}

	/**
	 * 更新商家状态
	 * @param sellerId
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public ResponseResult updateStatus(String sellerId,String status){
		try {
			sellerService.updateStatus(sellerId,status);
			return new ResponseResult(true, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "更新失败");
		}
	}
	
}
