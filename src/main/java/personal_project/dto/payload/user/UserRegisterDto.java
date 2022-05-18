package personal_project.dto.payload.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterDto {

    private String username;
    private String password;
    private String name;
    private List<String> permission;

}
