package info.qiao.common;

import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardResponse {

    private StatusResponse status;
    private String message;
    private JsonElement data;

    public StandardResponse(StatusResponse status) {
        this(status, null, null);
    }

    public StandardResponse(StatusResponse status, String message) {
        this(status, message, null);
    }

    public StandardResponse(StatusResponse status, JsonElement data) {
        this(status, null, data);
    }
}
