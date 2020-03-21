package com.codeif.article.controller.backstage;

import com.codeif.annotation.OptLog;
import com.codeif.article.service.backstage.CategoryService;
import com.codeif.constant.CommonConst;
import com.codeif.pojo.QueryVO;
import com.codeif.pojo.article.Category;
import com.codeif.utils.JsonData;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "文章分类管理")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "查询全部数据", notes = "id")
    @GetMapping
    public JsonData<QueryResults<Category>> findCategoryByCondition(Category category, QueryVO queryVO) {
        QueryResults<Category> result = categoryService.findCategoryByCondition(category, queryVO);
        return JsonData.success(result);
    }

    @ApiOperation(value = "根据ID查询", notes = "id")
    @GetMapping(value = "/{id}")
    public JsonData<Category> findCategoryByPrimaryKey(@PathVariable String id) {
        Category result = categoryService.findCategoryById(id);
        return JsonData.success(result);
    }


    @ApiOperation(value = "添加一条新的分类", notes = "id")
    @PostMapping
    @OptLog(operationType = CommonConst.ADD, operationName = "添加文章分类")
    public JsonData<Void> insertCategory(@RequestBody @Valid Category category) {
        categoryService.saveOrUpdate(category);
        return JsonData.success();
    }

    @PutMapping
    @OptLog(operationType = CommonConst.MODIFY, operationName = "修改文章分类")
    @ApiOperation(value = "修改文章分类", notes = "id")
    public JsonData<Void> updateByCategorySelective(@RequestBody @Valid Category category) {
        categoryService.saveOrUpdate(category);
        return JsonData.success();
    }

    @DeleteMapping
//    @OptLog(operationType = CommonConst.DELETE, operationName = "删除文章分类")
    @ApiOperation(value = "删除文章分类", notes = "id")
    public JsonData<Void> deleteCategoryByIds(@RequestBody List<String> categoryIds) {
        categoryService.deleteCategoryByIds(categoryIds);
        return JsonData.success();
    }

}
