package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class ErrorDTO { // опиясание для 400 ошибок (ответов)
    int status;
    String error;
    Object message;
//    "path": : "string"
}
