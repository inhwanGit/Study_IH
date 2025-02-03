package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hello_spring..*(..))")
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        System.out.println("Start : " + joinPoint.toShortString());
        try {
            return joinPoint.proceed();
        }finally {
            long finishTime = System.currentTimeMillis();
            long resultTime = finishTime - startTime;
            System.out.println("end : " + joinPoint.toShortString()+ " " + resultTime + "ms");
        }


    }
}
