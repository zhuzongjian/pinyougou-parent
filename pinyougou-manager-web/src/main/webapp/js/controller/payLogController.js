 //控制层 
app.controller('payLogController' ,function($scope,$controller   ,payLogService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		payLogService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		payLogService.findPage(page,rows).success(
			function(response){
				$scope.list=response.list;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		payLogService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=payLogService.update( $scope.entity ); //修改  
		}else{
			serviceObject=payLogService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.del=function(){			
		//获取选中的复选框		
		if (confirm("您确定删除吗?")) {		
			payLogService.del( $scope.ids ).success(
				function(response){
					if (response.success) {
                        $scope.ids = [];
                        $scope.reloadList();
                    } else {
                        alert(response.message)
                    }						
				}		
			);	
		}			
	}
	
	
	
	//搜索
	$scope.searchPage=function(page,rows){			
		payLogService.searchPage(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.list;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
});	
