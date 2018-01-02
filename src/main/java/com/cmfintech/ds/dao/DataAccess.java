package com.cmfintech.ds.dao;

import com.cmfintech.ds.annotation.Component;

/**   
  * @Title: DataAccess.java
  * @Description:数据访问
  * @Company  电子科技大学自动化研究所
  * @author  杜松   
  * @date 2017年12月19日 下午8:57:42
  * @version V1.0   
  * 
*/
@Component(id = "dataAccess")
public class DataAccess implements DataAccessInterface{
 
	/**
	 * 
	  *@Description:模拟数据查询
	  *@return
	  *@author  杜松   
	  *@date 2017年12月20日 上午10:02:26
	 */
    public String queryFromTableA() {
        return "query result";
    }
}