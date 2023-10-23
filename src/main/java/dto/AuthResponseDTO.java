package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class AuthResponseDTO { // описание объекта ответа при авторизации (200 ответ)
    String token;
}
