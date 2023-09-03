package mybatisdemo.mybatisdemo.entity;

import lombok.*;

import java.util.UUID;

//@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Attribute {
    UUID id;
    String name;
}
