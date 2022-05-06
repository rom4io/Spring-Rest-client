package ru.salyakhov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.salyakhov.configuration.RestConfig;
import ru.salyakhov.controller.ApiController;
import ru.salyakhov.entity.User;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
         final User userPost = new User(3L, "James", "Brown", (byte) 3);
         final User userPut = new User(3L, "Thomas", "Shelby", (byte) 3);

        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(RestConfig.class);

        ApiController apiController = context.getBean("apiController", ApiController.class);


        String result = "";
        String sessionId = apiController.getSeesionId();

        System.out.println(sessionId);

        String resultPost = apiController.userPost(sessionId,userPost);
        System.out.println(resultPost);
        result += resultPost;

        String resultPut = apiController.userPut(sessionId, userPut);

        System.out.println(resultPut);
        result += resultPut;

        String resultDelete = apiController.userDelete(sessionId);

        System.out.println(resultDelete);
        result += resultDelete;

        System.out.println(result);

    }
}
