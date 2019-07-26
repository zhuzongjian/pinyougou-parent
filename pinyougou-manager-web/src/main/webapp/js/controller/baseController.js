app.controller(
    "baseController",
    function ($scope) {

        //重新加载列表 数据  no.2
        $scope.reloadList = function () {
            $scope.ids = [];//每次刷新更新ids集合数据
            //切换页码
            $scope.searchPage($scope.paginationConf.currentPage,
                $scope.paginationConf.itemsPerPage);
        };
        //分页控件配置  no.1
        $scope.paginationConf = {
            currentPage: 1, //当前页
            totalItems: 10, //工具条显示的总页数
            itemsPerPage: 10,  //每页显示数量
            perPageOptions: [10, 20, 30, 40, 50], //分页选项
            onChange: function () {  //事件
                $scope.reloadList();//重新加载
            }
        };

        //多选的复选框数组
        $scope.ids = [];
        //更新复选框
        $scope.updateSelection = function ($event, id) {
            if ($event.target.checked) {
                $scope.ids.push(id);
            } else {
                var index = $scope.ids.indexOf(id);
                $scope.ids.splice(index, 1);
            }
        };

        $scope.list=[];//页面展示需要的list集合
        $scope.searchEntity = {};//搜索对象

        /**
         *
         * @param jsonStr json格式的字符串;是数组,单个对象直接通过json.parse()就行
         * @param attrName 字符串中的key属性 ,获取value后返回
         */
        $scope.jsonToString=function (jsonStr, attrName) {
            var parse = JSON.parse(jsonStr);
            var value="";//需要返回的值
            for (var i = 0; i < parse.length; i++) {
                if (i > 0) {
                    value += ",";
                }
                    value += parse[i][attrName];
            }
            return value;
        }
    });