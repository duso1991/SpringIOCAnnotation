package com.cmfintech.ds.service;

import com.cmfintech.ds.annotation.Autowire;
import com.cmfintech.ds.annotation.Component;
import com.cmfintech.ds.dao.DataAccessInterface;

/**   
  * @Title: BusinessObject.java
  * @Description:
  * @Company  电子科技大学自动化研究所
  * @author  杜松   
  * @date 2017年12月19日 下午8:58:29
  * @version V1.0   
*/
@Component(id="businessObject")
public class BusinessObject {
 
    @Autowire(id="dataAccess")
    private DataAccessInterface dai;
    
    public void print() {
        System.out.println(dai.queryFromTableA());
    }
    
    public void setDai(DataAccessInterface dai) {
        this.dai = dai;
    }
}