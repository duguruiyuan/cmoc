package com.xuequ.cmoc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
// 该注解标明该类为切面类
public class LoggerAspect {
	
	private static Logger logger = Logger.getLogger(LoggerAspect.class);
	
	// 该注解标明要作用到哪个类以及哪个目标方法上，以及什么时候触发,我们这里只用@Around(目标方法运行前后都运行该方法)

	// "execution(* com.service.*.*(..))"表示com.service包下的所有方法的所有类

	@Around(value = "execution(* com.xuecheng.channel.facadeImpl.*Impl.*(..)) &&args(object,..)",argNames = "object")
	public Object aspect(ProceedingJoinPoint jp,Object object) throws Throwable {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();  
		// 记录方法开始日志（这里为打印到控制台）
		logger.info(((MethodSignature) jp.getSignature()).getMethod().getName()
				+ " start-----");
		// 获得目标方法的参数
		Object[] params = jp.getArgs();
		// 将参数拼成一个字符串
		StringBuffer msg = new StringBuffer();
		msg.append(((MethodSignature) jp.getSignature()).getMethod().getName() +" params：");
		for (Object o : params) {
			msg.append(JSON.toJSONString(o) + ",");
		}
		// 将方法的参数记录到日志中（这里为打印到控制台）
		logger.info(msg.toString());
		Object rtn = jp.proceed(params);
		// 记录方法结束日志，以及方法的返回结果
		logger.info(((MethodSignature) jp.getSignature()).getMethod().getName()
				+ " end-----return:" + JSON.toJSONString(rtn));
		return rtn;
	}
}