
package com.spdp.service;

import java.util.List;
import java.util.Map;
import com.spdp.bean.UlbBean;
import com.spdp.bean.UrbanWardBean;


import com.spdp.bean.UrbanWardBean;


/**
 * @author rajat.mohanty
 *
 */


public interface DashBoardService {

	Map<String, Long> getCountforDistrict();
	List<UlbBean> getDashBoardUlbData(Long ulbTypeId);
	List<UrbanWardBean> getDashBoardUlbWardData();

}
