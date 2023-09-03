package mybatisdemo.mybatisdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.UUID;

//@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AttributeValue {
    UUID id;
    Attribute attribute;
    String value;
    //TODO resolve circular reference if possible?
    @JsonIgnore
    Dict owner;
}
