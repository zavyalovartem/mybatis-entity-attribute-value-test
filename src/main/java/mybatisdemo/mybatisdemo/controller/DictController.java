package mybatisdemo.mybatisdemo.controller;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import mybatisdemo.mybatisdemo.dto.DictDto;
import mybatisdemo.mybatisdemo.entity.Attribute;
import mybatisdemo.mybatisdemo.entity.AttributeValue;
import mybatisdemo.mybatisdemo.entity.Dict;
import mybatisdemo.mybatisdemo.mapper.AttributeMapper;
import mybatisdemo.mybatisdemo.mapper.AttributeValueMapper;
import mybatisdemo.mybatisdemo.mapper.DictMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DictController {

    private final DictMapper dictMapper;
    private final AttributeMapper attributeMapper;
    private final AttributeValueMapper attributeValueMapper;
    private final SqlSessionFactory sqlSessionFactory;

    @PostMapping("/createDictionary")
    @Transactional
    public ResponseEntity<Dict> createDictionary(@RequestBody DictDto dictDto){
        Dict dict = Dict.builder()
                .id(UUID.randomUUID())
                .build();
        dictMapper.createNewDict(dict);

        List<Attribute> attributes = new ArrayList<>();
        List<AttributeValue> attributeValues = new ArrayList<>();
        dictDto.getAttributes().forEach(attributeDto -> {
            Attribute attribute = Attribute.builder()
                    .id(UUID.randomUUID())
                    .name(attributeDto.getName())
                    .build();
            AttributeValue attributeValue = AttributeValue.builder()
                    .id(UUID.randomUUID())
                    .value(attributeDto.getValue())
                    .attribute(attribute)
                    .owner(dict)
                    .build();
            attributes.add(attribute);
            attributeValues.add(attributeValue);
        });

        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            AttributeMapper batchInsertAttributeMapper = session.getMapper(AttributeMapper.class);
            AttributeValueMapper batchInsertAttributeValueMapper = session.getMapper(AttributeValueMapper.class);
            attributes.forEach(batchInsertAttributeMapper::createNewAttribute);
            attributeValues.forEach(batchInsertAttributeValueMapper::createNewAttributeValue);
            session.flushStatements();
        }

        return ResponseEntity.ok(dict);
    }

    @GetMapping("/getDictionary")
    public ResponseEntity<Dict> getDictionary(@RequestParam("dictId") UUID dictId){
        return ResponseEntity.ok(dictMapper.getDictById(dictId));
    }
}
