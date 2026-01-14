package info.qiao;

import com.google.gson.Gson;
import info.qiao.common.StandardResponse;
import info.qiao.common.StatusResponse;
import info.qiao.user.User;
import info.qiao.user.UserService;
import info.qiao.user.UserServiceMapImpl;

import static spark.Spark.*;

public class SparkRestExample {
    public static void main(String[] args) {
        
        UserService userService = new UserServiceMapImpl();

        threadPool(8);
        // SporkJava 默认端口是 4567，可以使用 port(端口号) 更改。
        // 必须在定义 filter 和 router 之前调用。
        port(8080);
        post("/users", (request, response) -> {
            response.type("application/json");
            User user = new Gson().fromJson(request.body(), User.class);
            userService.addUser(user);

            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/users", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson()
                            .toJsonTree(userService.getUsers())));
        });

        get("/users/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson()
                            .toJsonTree(userService.getUser(request.params(":id")))));
        });

        put("/users/:id", (request, response) -> {
            response.type("application/json");
            User toEdit = new Gson().fromJson(request.body(), User.class);
            toEdit.setId(request.params(":id"));
            User editedUser = userService.editUser(toEdit);

            if (editedUser != null) {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.SUCCESS, new Gson()
                                .toJsonTree(editedUser)));
            } else {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR, new Gson()
                                .toJson("User not found or error in edit")));
            }
        });

        delete("/users/:id", (request, response) -> {
            response.type("application/json");
            userService.deleteUser(request.params(":id"));
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, "user deleted"));
        });

        options("/users/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,
                            (userService.userExist(
                                    request.params(":id"))) ? "User exists" : "User does not exists"));
        });

    }
}
