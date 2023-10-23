package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class AuthRequestDTO { //нарисовали структуру объекта изходя из документации в Swagger
    String username;
    String password;
}
