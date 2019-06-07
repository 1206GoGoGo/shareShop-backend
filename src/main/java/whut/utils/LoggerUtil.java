package whut.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
	//记录日志
	private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
	//private static final Logger logger = Logger.getLogger(UrlServiceImpl.class);
	
	public void simple() {
		logger.error("输出日志：{}","{}中的内容");
		//logger.error("输出日志：",e );
		//logger.error("输出日志：{}",e.toString() );
		logger.info("输出日志：{}成功记录日志信息" );
		//logger.error("输出日志--\\{}debug：{}","成功记录日志信息"+jedis );
    }
}
