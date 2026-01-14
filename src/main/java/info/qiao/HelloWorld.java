package info.qiao;

import static spark.Spark.get;

/**
 * @author Qiao Zhijun
 * @since 2026-01-10
 */
public class HelloWorld {
    public static void main(String[] args) {

        // 定义一个 GET 请求的路由
        get("/hello", (req, res) -> "Hello, world");

        // 定义一个 GET 请求的路由
        get("/hello/:name", (req, res) -> {
            return "Hello, " + req.params(":name");
        });
    }
}