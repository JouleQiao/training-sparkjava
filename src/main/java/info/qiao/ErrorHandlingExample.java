package info.qiao;

import static spark.Spark.*;
 
public class ErrorHandlingExample {
    public static void main(String[] args) {
        get("/error", (request, response) -> {
            throw new RuntimeException("Something went wrong");
        });
 
        // 全局异常处理
        exception(RuntimeException.class, (exception, request, response) -> {
            response.status(500);
            response.body("Internal Server Error: " + exception.getMessage());
        });
    }
}