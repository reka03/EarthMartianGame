package hu.tamasireka03.martiangame.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @NotNull
    private Long userid;

    @NotNull
    private String username;

    @NotNull
    private String martianName;

    private int chocolateCount;
}