app.service("brandService", function ($http) {
    //读取列表数据到表单
    this.findAll = function () {
        return $http.get("../brand/findPage.do");
    };
    //分页
    this.findPage = function (pageNum, pageSize) {
        return $http.get("../brand/findPage.do?pageNum=" + pageNum + "&pageSize=" + pageSize);
    };
    //查询实体
    this.findOne = function (id) {
        return $http.get("../brand/findOne.do?id=" + id);
    };
    //修改
    this.update = function (entity) {
        return $http.post("../brand/update.do", entity);
    };
    //增加
    this.add = function (entity) {
        return $http.post("../brand/add.do", entity);
    };
    // 删除
    this.del = function (ids) {
        return $http.get("../brand/delBrand.do?ids=" + ids);
    };
    // 搜索
    this.searchPage = function (pageNum, pageSize, searchEntity) {
        return $http.post("../brand/searchPage.do?pageNum=" + pageNum + "&pageSize=" + pageSize, searchEntity);
    };
    this.select2BrandList=function () {
        return $http.get("../brand/select2BrandList.do");
    };
});