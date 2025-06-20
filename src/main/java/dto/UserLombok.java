package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter

public class UserLombok {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
