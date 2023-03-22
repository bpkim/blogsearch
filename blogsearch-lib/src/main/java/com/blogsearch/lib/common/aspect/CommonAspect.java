package com.blogsearch.lib.common.aspect;

import com.blogsearch.lib.searchword.service.SearchwordService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class CommonAspect {

    @Autowired
    private SearchwordService searchwordService;

    /**
     * 검색어 기록
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.blogsearch.lib.common.annotation.SearchLog)")
    public Object logSearchword(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        log.debug("AOP Start logSearchword {} ", begin);
        String query = getParameterValue(pjp, "query");
        log.debug("query parameter value {}" , query);
        Object retVal = pjp.proceed(); // 메소드 호출

        try {
            searchwordService.saveSearchword(query);
        } catch (Exception e){
            log.error("logSearchword saveSearchword error, msg : {}", e.getMessage());
        }
        log.debug("AOP End logSearchWord {} ", System.currentTimeMillis() - begin);


        return retVal;
    }

    /**
     * 특정 변수명의 값 구하기
     * @param joinPoint
     * @param parameterName
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private String getParameterValue(final ProceedingJoinPoint joinPoint, final String parameterName) throws NoSuchFieldException, IllegalAccessException {
        final String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        final Object[] arguments = joinPoint.getArgs();
        String result = null;

        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(parameterName)) {
                result = (String) arguments[i];
            }
        }

        if(result == null) {
            for(Object objArgument : arguments) {
                Field parameterField = objArgument.getClass().getDeclaredField(parameterName);
                parameterField.setAccessible(true);
                return (String)parameterField.get(objArgument);
            }
        }

        return null;
    }
}
