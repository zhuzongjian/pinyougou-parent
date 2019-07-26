app.controller(
    "brandController",
    function ($scope, $controller, brandService) {
        $controller("baseController", {$scope: $scope});//继承
        //读取列表数据绑定到表单
        $scope.findAll = function () {
            brandService.findAll().success(
                function (response) {
                    $scope.brandList = response;
                })
        };
        //分页  no.3  使用条件查询以后就不需要这个方法了
        $scope.findPage = function (pageNum, pageSize) {
            brandService.findPage(pageNum, pageSize).success(
                function (data) {
                    $scope.list = data.list;
                    $scope.paginationConf.totalItems = data.total;
                })
        };
        //根据id查询后修改
        $scope.findOne = function (id) {
            brandService.findOne(id).success(function (data) {
                $scope.entity = data;
            })
        };
        //新增与修改
        $scope.save = function () {
            var serviceObject;//服务层对象
            if ($scope.entity.id) {
                serviceObject = brandService.update($scope.entity);
            } else {
                serviceObject = brandService.add($scope.entity);
            }
            serviceObject.success(function (data) {
                if (data.success) {
                    $scope.reloadList();
                } else {
                    alert(data.message)
                }
            });
        };
        //批量删除
        $scope.del = function () {
            if (confirm("您确定删除吗?")) {
                brandService.del($scope.ids).success(function (data) {
                    if (data.success) {
                        $scope.ids = [];
                        $scope.reloadList();
                    } else {
                        alert(data.message)
                    }
                });
            }
        };
        $scope.list = [];//每页的数据
        //搜索
        $scope.searchPage = function (pageNum, pageSize) {
            brandService.searchPage(pageNum, pageSize, $scope.searchEntity).success(function (data) {
                $scope.list = data.list; //定义分页每页的数据
                $scope.paginationConf.totalItems = data.total;
            })
        }
    });
