package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;

/**
 * 处理省/市/区数据的业务层接口
 */
public interface IDistrictService {

	/**
	 * 根据父级代号获取全国所有省/某省所有市/某市所有区的列表
	 * @param parent 父级代号
	 * @return 全国所有省/某省所有市/某市所有区的列表
	 */
	List<District> getByParent(String parent);
	
	/**
	 * 根据代号获取省/市/区的详情
	 * @param code 省/市/区的代号
	 * @return 省/市/区的详情
	 */
	District getByCode(String code);
	
}
