package cn.jeeweb.modules.test.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.test.entity.SingleTableEntity;
import cn.jeeweb.modules.test.service.ISingleTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 单表测试
 * @Description: 单表测试
 * @author jeeweb
 * @date 2017-06-26 16:50:57
 * @version V1.0   
 *
 */
@Transactional
@Service("singleTableService")
public class SingleTableServiceImpl  extends CommonServiceImpl<SingleTableEntity> implements  ISingleTableService {

}
