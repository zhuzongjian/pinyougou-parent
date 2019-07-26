 //控制层 
app.controller('specificationController' ,function($scope,$controller   ,specificationService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		specificationService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页 假的,不需要
	$scope.findPage=function(page,rows){
		specificationService.findPage(page,rows).success(
			function(response){
				$scope.list=response.list;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		specificationService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.specification.id!=null){//如果有ID
			serviceObject=specificationService.update( $scope.entity ); //修改  
		}else{
			serviceObject=specificationService.add( $scope.entity  );//增加 
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
			specificationService.del( $scope.ids ).success(
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
		specificationService.searchPage(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.list;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	//$scope.entity = {specification: {}, specificationOptionList: []};
	//{specification:{},specificationOptionList:[]}
	//增加一行
	$scope.addTableRow=function () {
		$scope.entity.specificationOptionList.push({})
	};
	//删除新增框的一行
	$scope.deleteTableRow=function (index) {
		$scope.entity.specificationOptionList.splice(index, 1);
	};
    
});	
