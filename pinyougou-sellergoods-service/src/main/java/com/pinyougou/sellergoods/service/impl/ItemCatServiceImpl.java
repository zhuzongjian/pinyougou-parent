package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.pojo.TbItemCatExample.Criteria;
import com.pinyougou.sellergoods.service.ItemCatService;



/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbItemCat> findAll() {
		return itemCatMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageInfo findPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		//Page<TbItemCat> page=   (Page<TbItemCat>) itemCatMapper.selectByExample(null);
		//return new PageResult(page.getTotal(), page.getResult());
		return new PageInfo<TbItemCat>(itemCatMapper.selectByExample(null));
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbItemCat itemCat) {
		itemCatMapper.insert(itemCat);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbItemCat itemCat){
		itemCatMapper.updateByPrimaryKey(itemCat);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbItemCat findOne(Long id){
		return itemCatMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		
		for(Long id:ids){
			TbItemCatExample example=new TbItemCatExample();
			example.createCriteria().andParentIdEqualTo(id);
			List<TbItemCat> list = itemCatMapper.selectByExample(example);
			if(list==null || list.size()== 0) {
			itemCatMapper.deleteByPrimaryKey(id);
			}
		}		
	}
	
	/**
	 * 查询+分页
	 */
	@Override
	public PageInfo findPage(TbItemCat itemCat, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		
		if(itemCat!=null){			
						if(itemCat.getName()!=null && itemCat.getName().length()>0){
				criteria.andNameLike("%"+itemCat.getName()+"%");
			}
	
		}
		
		//Page<TbItemCat> page= (Page<TbItemCat>)itemCatMapper.selectByExample(example);		
		//return new PageResult(page.getTotal(), page.getResult());
		return new PageInfo<TbItemCat>(itemCatMapper.selectByExample(example));
	}

    @Override
    public List findByParentId(Long parentId) {
		TbItemCatExample example=new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		return itemCatMapper.selectByExample(example);
    }
}
