package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop { // 이것 또한 spring bean에 등록해주어야 함

    @Around("execution(* hello.hellospring..*(..))")
    // @Around = 타겟팅 할 수 있음
    // 여기선 hello.hellospring 패키지 아래 모두에 적용하겠다라고 타겟팅함
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+ joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
