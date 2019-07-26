//控制层
app.controller('typeTemplateController', function ($scope, $controller, typeTemplateService, brandService,specificationService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        typeTemplateService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        typeTemplateService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体 给entity赋值对应java对象TbTypeTemplate
    $scope.findOne = function (id) {
        typeTemplateService.findOne(id).success(
            function (response) {
                //这一步可以给基本属性赋值比如id, name,
                $scope.entity = response;
                // 字符串对象要装换成真正的对象就是去掉字符串{}前后的双引号
                $scope.entity.brandIds= JSON.parse(response.brandIds);//[{id:xxx,text:xxx}]
                $scope.entity.specIds = JSON.parse(response.specIds);
                $scope.entity.customAttributeItems = JSON.parse(response.brandIds);
                // $scope.entity = JSON.parse(response);这样解析不了为什么呢?
            }
        );
    };

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = typeTemplateService.update($scope.entity); //修改
        } else {
            serviceObject = typeTemplateService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    }


    //批量删除
    $scope.del = function () {
        //获取选中的复选框
        if (confirm("您确定删除吗?")) {
            typeTemplateService.del($scope.ids).success(
                function (response) {
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


    //搜索+分页  页面不直接使用,搜索使用reloadList()方法
    $scope.searchPage = function (page, rows) {
        typeTemplateService.searchPage(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }
    //品牌列表key为data是select2要求必须的
    $scope.brandList = {data:[]};

    $scope.select2BrandList = function () {
        brandService.select2BrandList().success(function (response) {
            $scope.brandList.data = response;
        })
    };
    //规格下拉列表
    $scope.specificationList={data: []};
    //select2下拉列表功能
    $scope.select2SpecificationList=function () {
        specificationService.select2SpecificationList().success(function (response) {
            $scope.specificationList.data = response;
        })
    };

    $scope.entity={customAttributeItems:[]};
    //新建或者修改删除表格
    $scope.addTableRow=function () {
        $scope.entity.customAttributeItems.push({})
    }
  //新建或者修改删除表格
    $scope.delTableRow=function (index) {
        $scope.entity.customAttributeItems.splice(index,1)
    }
});	
