package whut.aop;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import whut.utils.ResponseData;


@Component
//@Aspect
public class ExceptionHandle {
	
	public ExceptionHandle() {}
	
    //@Pointcut("execution(* whut.controller.*(..))")
	//完成dao后再开启全部controller的异常捕获，并加入日志
	
    @Pointcut("execution(* whut.service.MemberInfoService.*(..))")
    public void declearJoinPointExpression(){}
    /**
     * 环绕通知(需要携带类型为ProceedingJoinPoint类型的参数)
     * 环绕通知方法一定要有ProceedingJoinPoint类型的参数传入，然后执行对应的proceed()方法，环绕才能实现。
     * 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即目标方法的返回值
     * @param joinPoint
     */
    @Around(value="declearJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint point){
        
    	ResponseData responseData = null;
        String methodName = point.getSignature().getName();
        try {
            //前置通知
            System.out.println("----------------------The method "+ methodName+" start. param<"+ Arrays.asList(point.getArgs())+">");
            //执行目标方法
            responseData = (ResponseData) point.proceed();
            //返回通知
            System.out.println("-----------------------The method "+ methodName+" end. result<"+ responseData+">");
        } catch (Throwable e) {
        	
        	responseData = new ResponseData(500,"请求异常",null);
            //异常通知
            System.out.println("this method "+methodName+" end.ex message<"+e+">");
            //throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("The method "+ methodName+" end.");
        return responseData;
    }

}
