package com.pinyougou.manager.controller;
import java.util.List;
import java.util.Map;

import com.pinyougou.pojogroup.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.sellergoods.service.SpecificationService;


import entity.ResponseResult;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

	@Reference
	private SpecificationService specificationService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbSpecification> findAll(){			
		return specificationService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageInfo  findPage(Integer pageNum,Integer pageSize){			
		return specificationService.findPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param specification
	 * @return
	 */
	@RequestMapping("/add")
	public ResponseResult add(@RequestBody Specification specification){
		try {
			specificationService.add(specification);
			return new ResponseResult(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param specification
	 * @return
	 */
	@RequestMapping("/update")
	public ResponseResult update(@RequestBody Specification specification){
		try {
			specificationService.update(specification);
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
	public Specification findOne(Long id){
		return specificationService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResponseResult delete(Long [] ids){
		try {
			specificationService.delete(ids);
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
	public PageInfo searchPage(@RequestBody TbSpecification specification, Integer pageNum, Integer pageSize  ){
		return specificationService.findPage(specification, pageNum, pageSize);		
	}

	@RequestMapping("/select2SpecificationList")
	public List<Map> select2SpecificationList(){
		return specificationService.select2SpecificationList();
	}
}
