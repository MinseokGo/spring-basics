package chap07.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ExeTimeAspect {
    //chap07 패키지나 그 하위 패키지에 속한 빈 객체의 public 메서드에
    //@Around가 붙은 measure() 메서드를 적용
    @Pointcut("execution(public * chap07.*.*(*))")
    private void publicTarget() {}

    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();
            //메서드 이름과 파라미터를 합쳐서 메서드 시그니처라 함
            Signature sig = joinPoint.getSignature();

            System.out.printf("%s.%s(%s) 실행시간 : %d ns\n",
                                joinPoint.getTarget().getClass().getSimpleName(),
                                sig.getName(), Arrays.toString(joinPoint.getArgs()),
                                finish-start);
        }
    }

}