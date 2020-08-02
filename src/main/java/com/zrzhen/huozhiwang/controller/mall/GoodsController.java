package com.zrzhen.huozhiwang.controller.mall;

import com.zrzhen.huozhiwang.common.CategoryLevelEnum;
import com.zrzhen.huozhiwang.common.Constants;
import com.zrzhen.huozhiwang.common.ServiceResultEnum;
import com.zrzhen.huozhiwang.controller.vo.GoodsDetailVO;
import com.zrzhen.huozhiwang.controller.vo.SearchPageCategoryVO;
import com.zrzhen.huozhiwang.controller.vo.SearchPageShowVO;
import com.zrzhen.huozhiwang.dao.GoodsCategoryMapper;
import com.zrzhen.huozhiwang.dao.GoodsMapper;
import com.zrzhen.huozhiwang.entity.GoodsCategory;
import com.zrzhen.huozhiwang.entity.GoodsInfo;
import com.zrzhen.huozhiwang.service.GoodsService;
import com.zrzhen.huozhiwang.service.SearchService;
import com.zrzhen.huozhiwang.util.BeanUtil;
import com.zrzhen.huozhiwang.util.PageUtil;
import com.zrzhen.huozhiwang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author: 慧燕
 * @date: 2020/7/31 15:56
 * @copyright yanlongyun2020
 */
@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    SearchService searchService;

   /**
   *点击商品图片，跳转到商品详情页，
   * @param: [goodsId, request]
   * @createDate: 2020/7/31 16:00
   * @return: java.lang.String
   */
    @GetMapping("goods/detail/{goodsId}")
    public String goodDetail(@PathVariable("goodsId") Long goodsId, HttpServletRequest request){
//        判断ID大小
        if(goodsId<1){
            return "error/error_5xx";
        }
//        判断是否存在该商品
        GoodsInfo goodsInfo = goodsService.getGoodDetail(goodsId);
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        BeanUtil.copyProperties(goodsInfo,goodsDetailVO);
        request.setAttribute("goodsDetail",goodsDetailVO);
        return "mall/detail";
    }
    /**
    *查找商品分为两种方法：1.搜索框中search?keyword=%E6%89%8B%E6%9C%BA
     * 2.从分类中搜索http://127.0.0.1:28089/search?keyword=&goodsCategoryId=45&orderBy=new
    * @param: [goodsCategoryId, request]
    * @createDate: 2020/7/31 22:54
    * @return: java.lang.String
    */
    @GetMapping({"/search", "/search.html"})
    public String searchPage(@RequestParam Map<String,Object> params, HttpServletRequest request){
        /*可以看到链接可能传递过来的信息较多
        * 1.只有goodsCategoryId的情况
        * 2.只有keyword
        * 3.有三个：因为点击了orderBy按钮，有三个参数
        * 4.点击了分页按钮多了page：search?keyword=手机&page=2&goodsCategoryId=&orderBy=new
        * 所以：使用一个map来接受，返回的时候返回多个信息
        * */
        /*1.判断有没有page，如果没有的话，则设置一个*/
        if (StringUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        /*2.判断有没有goodsCategoryId,如果有判断是不是第三级别的，如果是则是从分类那里传过来的，需要显示级别*/
        Long categoryId = 0L;
        if(params.containsKey("goodsCategoryId") && !StringUtils.isEmpty(params.get("goodsCategoryId") + "")){
            categoryId = Long.valueOf(params.get("goodsCategoryId") + "");
            GoodsCategory goodsCategory = goodsCategoryMapper.selectCategoryById(categoryId);
            params.put("goodsCategory",goodsCategory);
            if (goodsCategory.getCategoryLevel()== CategoryLevelEnum.LEVEL_THREE.getLevel()) {
                /*查询出二级分类和三级分类的数据包装到一个分类VO里面。*/
                SearchPageCategoryVO searchPageCategory = searchService.getPageCategory(params);
                if(searchPageCategory != null){
                    request.setAttribute("searchPageCategoryVO",searchPageCategory);
                    request.setAttribute("goodsCategoryId",categoryId);
                }
            }
        }
        /*判断有没有keyword*/
        String keyword = "";
        if(params.containsKey("keyword")&&!StringUtil.isEmpty(params.get("keyword")+"")){
            /*这个查询的时候要用到*/
            keyword = params.get("keyword")+"";
        }
        params.put("keyword",keyword);
        request.setAttribute("keyword",keyword);
        /*判断有没有orderby*/
        //封装参数供前端回显
        if (params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy") + "")) {
            request.setAttribute("orderBy", params.get("orderBy") + "");
        }
        /*查询到上架的*/
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        /*封装分页数据用于查询*/
        params.put("limit",Constants.SEARCH_CATEGORY_NUMBER);
        PageUtil pageUtil = new PageUtil(params);

        SearchPageShowVO searchPageShowVO = searchService.getPageShow(pageUtil);
        if(searchPageShowVO != null){
            request.setAttribute("pageResult",searchPageShowVO);
        }else {
            request.setAttribute("pageResult",new SearchPageShowVO());
        }
        return "mall/search";
    }
}
