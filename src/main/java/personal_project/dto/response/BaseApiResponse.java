package personal_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseApiResponse {
    private String message;
    private int status;
    private Object data;

    public BaseApiResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public BaseApiResponse(int i, String service_already_exist, Object data) {
    }
}
