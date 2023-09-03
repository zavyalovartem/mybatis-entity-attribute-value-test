package mybatisdemo.mybatisdemo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class DictDto {
    String name;
    List<AttributeDto> attributes;
}
