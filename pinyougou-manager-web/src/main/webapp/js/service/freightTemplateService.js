//服务层
app.service('freightTemplateService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../freightTemplate/findAll.do');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../freightTemplate/findPage.do?pageNum='+page+'&pageSize='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../freightTemplate/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../freightTemplate/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../freightTemplate/update.do',entity );
	}
	//删除
	this.del=function(ids){
		return $http.get('../freightTemplate/delete.do?ids='+ids);
	}
	//搜索
	this.searchPage=function(page,rows,searchEntity){
		return $http.post('../freightTemplate/searchPage.do?pageNum='+page+"&pageSize="+rows, searchEntity);
	}    	
});
