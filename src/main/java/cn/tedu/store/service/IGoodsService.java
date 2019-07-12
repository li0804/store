package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {

	/**
	 * 获取热销的前4项商品的数据列表
	 * @return 热销的前4项商品的数据列表
	 */
	List<Goods> getHotList();
}
