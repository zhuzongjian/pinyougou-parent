 //控制层 
app.controller('itemCatController' ,function($scope,$controller   ,itemCatService,typeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		itemCatService.findPage(page,rows).success(
			function(response){
				$scope.list=response.list;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	

	//查询实体  修改
	$scope.findOne=function(id){	
		console.log($scope.entity.type)
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;//给基本属性赋值
				//$scope.entity.type.id=$scope.entity.typeId;
				//通过查询回来的typeid获取单个type中text  {id:xxx,text:xxx}			
				//修改返回值entity的type={id:xxx,text:xxx}
			}
		);
	}

	
	//保存 
	$scope.save=function(){	
		//$scope.entity.typeId=$scope.entity.type.id;//前台ng-model双向绑定过了
		var serviceObject;//服务层对象  	
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			$scope.entity.parentId=$scope.parentList[$scope.parentList.length-1].id;//从父标签的集合中取出id作为新建项的父标签
			serviceObject=itemCatService.add( $scope.entity  );//增加 
			//console.log($scope.entity);
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	//$scope.reloadList();//重新加载
		        	$scope.findByParentId($scope.entity.parentId);
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	/*$scope.save=function(){	
		$scope.entity.typeId=$scope.entity.type.id;
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			$scope.entity.parentId=$scope.parentList[$scope.parentList.length-1].id;//从父标签的集合中取出id作为新建项的父标签
			serviceObject=itemCatService.add( $scope.entity  );//增加 
			//console.log($scope.entity);
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	//$scope.reloadList();//重新加载
		        	$scope.findByParentId($scope.entity.parentId);
				}else{
					alert(response.message);
				}
			}		
		);				
	}*/

	
	 
	//批量删除 
	$scope.del=function(){			
		//获取选中的复选框		
		if (confirm("您确定删除吗?")) {		
			itemCatService.del( $scope.ids ).success(
				function(response){
					if (response.success) {
                        $scope.ids = [];
                        //$scope.reloadList();
                        $scope.findByParentId($scope.entity.parentId);
                    } else {
                        alert(response.message)
                    }						
				}		
			);	
		}			
	}
	
	
	
	//搜索  此处用不着
	/*$scope.searchPage=function(page,rows){			
		itemCatService.searchPage(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.list;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}*/
	//内置功能给NO.1使用   初始化加载
	$scope.entity={};
	$scope.findByParentId=function (id) {
		itemCatService.findByParentId(id).success(function (data) {
			$scope.list=data;
			if ($scope.list.length>0) {
				$scope.entity.parentId=$scope.list[0].parentId;
				//console.log($scope.entity.parentId);
			}
		})
		
	}
	
	//存放面包屑的父类的entity集合
	$scope.parentList=[{id:0,name:'顶级分类列表',parentId:0}];
	//1.单击查询下级功能
	$scope.selectList=function (p_entity) {//p_entity上一个父节点
		alert($scope.parentList.includes(p_entity))
		$scope.parentList.push(p_entity);
		$scope.findByParentId(p_entity.id);
	}
	//2.单击面包屑导航删除集合中子导航
	$scope.removeList=function (index, entity) {
/*		alert($scope.parentList.length-index)
		alert(index)*/
		alert($scope.parentList.includes(entity))
		$scope.parentList.splice(index+1,$scope.parentList.length-index-1);
		//$scope.parentList.splice($scope.parentList.indexOf(entity),1);
		$scope.findByParentId(entity.id);
	}
	//3.结合1,2
	$scope.MFSelectList=function (entity) {
		if ($scope.parentList.includes(entity)) {
			//单击面包屑就会删除该entity以及之后的数据
			var index = $scope.parentList.indexOf(entity);
			$scope.parentList.splice(index+1,$scope.parentList.length-1-index);
		}else {
			$scope.parentList.push(entity);//查询下级就会保存到面包屑中
		}
		$scope.findByParentId(entity.id);	
	}
	
	//select2下拉列表展示  初始化加载固定的只加载一次
	$scope.options={data:[]};//[]-->{id:xxx,text:xxx}
	$scope.select2type_template=function () {
		typeTemplateService.select2type_template().success(function (response) {
			$scope.options.data=response;
		})
	};
	
	/*$scope.selectList=function (p_entity) {
	if ($scope.grade == 1) {
		$scope.entity_1 = null;
		$scope.entity_2 = null;
	}
	if ($scope.grade == 2) {
		$scope.entity_1 = p_entity;
		$scope.entity_2 = null;
	}
	if ($scope.grade == 3) {
		$scope.entity_2 = p_entity;
	}
	$scope.findByParentId(p_entity.id);
}*/
});
