package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Goods;

/**
 * 处理商品数据的持久层接口
 */
public interface GoodsMapper {

	/**
	 * 获取热销的前4项商品的数据列表
	 * @return 热销的前4项商品的数据列表
	 */
	List<Goods> findHotList();
}



