package info.qiao.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusResponse {
    SUCCESS("Success"),
    ERROR("Error");

    private String status;
}
