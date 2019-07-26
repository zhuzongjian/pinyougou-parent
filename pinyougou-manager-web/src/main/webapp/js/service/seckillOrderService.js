//服务层
app.service('seckillOrderService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../seckillOrder/findAll.do');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../seckillOrder/findPage.do?pageNum='+page+'&pageSize='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../seckillOrder/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../seckillOrder/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../seckillOrder/update.do',entity );
	}
	//删除
	this.del=function(ids){
		return $http.get('../seckillOrder/delete.do?ids='+ids);
	}
	//搜索
	this.searchPage=function(page,rows,searchEntity){
		return $http.post('../seckillOrder/searchPage.do?pageNum='+page+"&pageSize="+rows, searchEntity);
	}    	
});
