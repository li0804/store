package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.IGoodsService;
import cn.tedu.store.util.ResponseResult;

/**
 * 处理商品数据的控制器
 */
@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController {

	@Autowired
	private IGoodsService goodsService;
	
	@GetMapping("hot")
	public ResponseResult<List<Goods>> getHostList() {
	    // 调用业务层对象查询数据，并获取返回的结果
		List<Goods> data = goodsService.getHotList();
	    // 返回“成功”和以上查询到的结果
		return new ResponseResult<>(SUCCESS, data);
	}
}








