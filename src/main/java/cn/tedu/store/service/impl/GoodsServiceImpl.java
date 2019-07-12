package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;

@Service
public class GoodsServiceImpl implements IGoodsService {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public List<Goods> getHotList() {
		return findHotList();
	}
	
	/**
	 * 获取热销的前4项商品的数据列表
	 * @return 热销的前4项商品的数据列表
	 */
	private List<Goods> findHotList() {
		return goodsMapper.findHotList();
	}


	

}







