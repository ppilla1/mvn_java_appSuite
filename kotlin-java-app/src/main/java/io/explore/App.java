package io.explore;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class App 
{
    public static void main( String[] args )
    {
        JavaService javaService = new JavaService();
        KotlinService kotlinService = new KotlinService();

        String language = "java";

        switch (language) {
            case "kotlin" :
                log.info(kotlinService.sayHello());
                break;
            case "java" :
                log.info(javaService.sayHello());
                break;
            default: break;
        }
    }
}
