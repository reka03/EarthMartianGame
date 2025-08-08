package hu.tamasireka03.martiangame.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @NonNull
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String martianName;
    private int chocolate;
}