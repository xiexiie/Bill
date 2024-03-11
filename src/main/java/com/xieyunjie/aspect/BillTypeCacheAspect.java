package com.xieyunjie.aspect;

import com.xieyunjie.sys.domain.Billtype;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect//声明切面
@Component
@EnableAspectJAutoProxy//开启基于切面的注解
public class BillTypeCacheAspect {

	/**
	 *缓存对象
	 */
	private Map<String,Object> cache=new HashMap<>();

	/**
	 * 设置缓存前缀
	 */
	private static final String BILL_TYPE_CACHE_PERFIX="billtype";

	@Pointcut("execution(* com.xieyunjie.sys.service.impl.BilltypeServiceImpl.getById(..))")
	public void pc(){

	}

	/**
	 * 对于切点进行环绕通知
	 */
	@Around(value = "pc()")
	public Object cacheBillType(ProceedingJoinPoint point) throws Throwable{
		//得到目标方法的参数
		Object[] args = point.getArgs();
		//取出ID
		Integer typeId = (Integer) args[0];
		//从缓存里面取出对象
		Object obj = cache.get(BILL_TYPE_CACHE_PERFIX + typeId);
		//缓存里面有数据
		if(null!=obj){
			System.out.println("缓存里面有数据");
			return obj;
		}else{
			System.err.println("缓存里面没有数据，执行对数据库的查找");
			//说明缓存里面没有数据 放行执行目标方法 查询数据
			Billtype res = (Billtype) point.proceed();
			//放入缓存
			cache.put(BILL_TYPE_CACHE_PERFIX+res.getId(),res);
			return res;
		}
	}
}
