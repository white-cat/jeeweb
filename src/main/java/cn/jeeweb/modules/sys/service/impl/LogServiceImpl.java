package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.Log;
import cn.jeeweb.modules.sys.service.ILogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("logService")
public class LogServiceImpl extends CommonServiceImpl<Log> implements ILogService {

}
