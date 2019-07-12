package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.AccessDeniedExcption;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

/**
 * 处理收货地址数据的业务层接口
 */
public interface IAddressService {

	/**
	 * 增加新的收货地址数据
	 * @param address 收货地址数据
	 * @param username 当前登录的用户的用户名
	 * @throws InsertException
	 */
	void addnew(Address address, 
		String username) 
			throws InsertException;
	
	/**
	 * 根据用户id查询该用户的收货地址数据列表
	 * @param uid 用户id
	 * @return 该用户的收货地址数据列表
	 */
	List<Address> getByUid(Integer uid);
	
	/**
	 * 将指定的收货地址设置为默认
	 * @param aid 收货地址的数据id
	 * @param uid 当前登录的用户的id
	 * @param username 当前登录的用户的用户名
	 * @throws AddressNotFoundException 
	 * @throws UpdateException
	 */
	void setDefault(
		Integer aid, Integer uid, String username) 
			throws AddressNotFoundException, 
				UpdateException, AccessDeniedExcption;
	
	/**
	 * 删除收货地址数据
	 * @param aid 将要删除的收货地址数据的id
	 * @param uid 当前登录的用户的id
	 * @param username 当前登录的用户的用户名
	 * @throws AddressNotFoundException
	 * @throws AccessDeniedExcption
	 * @throws UpdateException
	 * @throws DeleteException
	 */
	void delete(Integer aid, Integer uid, String username) 
		throws AddressNotFoundException, AccessDeniedExcption,
			UpdateException, DeleteException;
	
}
