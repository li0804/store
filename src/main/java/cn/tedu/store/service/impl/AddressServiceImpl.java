package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AccessDeniedExcption;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

/**
 * 处理收货地址数据的业务层实现类
 */
@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public void addnew(Address address, String username) throws InsertException {
		// 根据参数address中的uid执行查询数量
		Integer uid = address.getUid();
		Integer count = countByUid(uid);
		// 判断收货地址数量是否为0
		// 是：is_default > 1
		// 否：is_default > 0
		Integer isDefault = count == 0 ? 1 : 0;
		// 将is_default的值封装到参数address中
		address.setIsDefault(isDefault);
		
		// 获取省、市、区的名称
		String district = getDistrict(
				address.getProvince(),
				address.getCity(),
				address.getArea());
		address.setDistrict(district);

		// 4项日志数据
		Date now = new Date();
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);

		// 执行增加
		insert(address);
	}
	
	@Override
	public List<Address> getByUid(Integer uid) {
		return findByUid(uid);
	}
	
	@Override
	@Transactional
	public void setDefault(Integer aid, Integer uid, String username) throws AddressNotFoundException, UpdateException, AccessDeniedExcption {
		// 根据参数aid查询数据
		Address result = findByAid(aid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：AddressNotFoundException
			throw new AddressNotFoundException(
				"设置默认收货地址失败！尝试访问的数据不存在！");
		}
		
		// 判断查询结果中的uid和参数uid是否不一致（需要检查持久层的查询功能是否查询了uid）
		if (result.getUid() != uid) {
			// 是：AccessDeniedException
			throw new AccessDeniedExcption(
				"设置默认收货地址失败！访问被拒绝！");
		}
		
		// 根据参数uid将全部地址设置为非默认
		updateNonDefault(uid);

		// 创建当前时间对象
		Date now = new Date();
		// 根据aid将指定地址设置为默认
		updateDefault(aid, username, now);
	}
	
	@Override
	@Transactional
	public void delete(Integer aid, Integer uid, String username)
			throws AddressNotFoundException, AccessDeniedExcption, UpdateException, DeleteException {
		// 根据参数aid查询即将删除的数据：findByAid(aid)
		Address result = findByAid(aid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：AddressNotFoundException
			throw new AddressNotFoundException(
				"删除收货地址失败！尝试访问的数据不存在！");
		}
		
		// 判断查询结果中的uid和参数uid是否不一致（需要检查持久层的查询功能是否查询了uid）
		if (result.getUid() != uid) {
			// 是：AccessDeniedException
			throw new AccessDeniedExcption(
				"删除收货地址失败！访问被拒绝！");
		}

		// 执行删除：deleteByAid(aid)
		deleteByAid(aid);

		// 判断查询结果的isDefault是否为0：删除的不是默认收货地址，后续不需要补充操作，直接结束
		if (result.getIsDefault() == 0) {
			return;
		}
		
		// 表示删除了默认收货地址，则统计收货地址数量：countByUid(uid)
		Integer count = countByUid(uid);
		// 判断数量是否为0，是：刚才删除的是最后一条数据，后面没有数据了，则不需要补充操作
		if (count == 0) {
			return;
		}
		
		// 找出最后修改的收货地址：findLastModified(uid)
		Address lastModified = findLastModified(uid);
		// 获取该收货地址数据的aid：Integer lastModifiedAid = xx.getAid()
		Integer lastModifiedAid = lastModified.getAid();
		// 创建当前时间对象
		Date now = new Date();
		// 将该收货地址设置为默认：updateDefault(lastModifiedAid, username, now)
		updateDefault(lastModifiedAid, username, now);
	}
	
	// 持久层中的方法，在业务层中，都应该有一个与之对应的方法
	// 而在业务层中的这类方法，应该是私有的
	// 如果对应的是增/删/改方法，持久层方法的返回值是Integer，而业务层的应该是void，并且，会判断调用持久层方法的返回值，如果不是期望值，则抛出异常
	// 如果对应的是查询方法，则直接调用持久层对象来完成查询
	// 在业务层中的业务方法（公有的方法）中，只会调用自身的私有方法，决不直接调用持久层对象的方法
	
	/**
	 * 增加收货地址数据
	 * @param address 收货地址数据
	 */
	private void insert(Address address) {
		Integer rows = addressMapper.insert(address);
		if (rows != 1) {
			throw new InsertException(
				"增加收货地址失败！插入数据时出现未知错误！");
		}
	}
	
	/**
	 * 删除收货地址数据
	 * @param aid 收货地址数据的id
	 */
	private void deleteByAid(Integer aid) {
		Integer rows = addressMapper.deleteByAid(aid);
		if (rows != 1) {
			throw new DeleteException(
				"删除收货地址失败！删除数据时出现未知错误！");
		}
	}
	
	/**
	 * 将指定的收货地址设置为默认
	 * @param aid 即将被设置为默认的收货地址数据的id
	 * @param modifiedUser 修改执行人
	 * @param modifiedTime 修改时间
	 */
	private void updateDefault(Integer aid, 
			String modifiedUser, Date modifiedTime) {
		Integer rows = addressMapper.updateDefault(aid, modifiedUser, modifiedTime);
		if (rows != 1) {
			throw new UpdateException(
				"设置默认收货地址失败！更新数据时出现未知错误！");
		}
	}

	/**
	 * 将某用户的所有收货地址全部设置为非默认
	 * @param uid 用户的id
	 * @return 受影响的行数
	 */
	private void updateNonDefault(Integer uid) {
		Integer rows = addressMapper.updateNonDefault(uid);
		if (rows < 1) {
			throw new UpdateException(
				"设置默认收货地址失败！更新数据时出现未知错误！");
		}
	}
	
	/**
	 * 统计某用户的收货地址的数量
	 * @param uid 用户的id
	 * @return 收货地址的数量
	 */
	private Integer countByUid(Integer uid) {
		if (uid == null) {
			throw new IllegalArgumentException();
		}
		return addressMapper.countByUid(uid);
	}
	
	/**
	 * 根据用户id查询该用户的收货地址数据列表
	 * @param uid 用户id
	 * @return 该用户的收货地址数据列表
	 */
	private List<Address> findByUid(Integer uid) {
		return addressMapper.findByUid(uid);
	}
	
	/**
	 * 根据收货地址id查询收货地址数据
	 * @param aid 收货地址id
	 * @return 匹配的收货地址数据，如果没有匹配的数据，则返回null
	 */
	private Address findByAid(Integer aid) {
		return addressMapper.findByAid(aid);
	}
	
	/**
	 * 查询某用户的最后修改的1条收货地址数据
	 * @param uid 用户的id
	 * @return 最后修改的1条收货地址数据
	 */
	private Address findLastModified(Integer uid) {
		return addressMapper.findLastModified(uid);
	}
	
	@Autowired
	private IDistrictService districtService;
	
	/**
	 * 根据省、市、区的代号获取地址名称
	 * @param provinceCode 省的代号
	 * @param cityCode 市的代号
	 * @param areaCode 区的代号
	 * @return 地址名称
	 */
	private String getDistrict(String provinceCode, String cityCode, String areaCode) {
		StringBuffer result = new StringBuffer();
		
		District province = districtService.getByCode(provinceCode);
		District city = districtService.getByCode(cityCode);
		District area = districtService.getByCode(areaCode);
		
		if (province != null) {
			result.append(province.getName());
		}
		if (city != null) {
			result.append(city.getName());
		}
		if (area != null) {
			result.append(area.getName());
		}
		
		return result.toString();
	}

}





