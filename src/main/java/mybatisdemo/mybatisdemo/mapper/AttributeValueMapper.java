package mybatisdemo.mybatisdemo.mapper;

import mybatisdemo.mybatisdemo.entity.Attribute;
import mybatisdemo.mybatisdemo.entity.AttributeValue;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AttributeValueMapper {
    @Insert("insert into dicts.attribute_value (id, value, owner_id, attribute_id) values (#{id}, #{value}, #{owner.id}, #{attribute.id})")
    void createNewAttributeValue(AttributeValue attributeValue);

    @Select("select * from dicts.attribute_value where id = #{id}")
    AttributeValue getAttributeValueById(UUID id);

    @Select("select * from dicts.attribute_value where owner_id=#{dictId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "value", column = "value"),
            @Result(property = "owner", column = "owner_id", one = @One(select = "mybatisdemo.mybatisdemo.mapper.DictMapper.getDictById")),
            @Result(property = "attribute", column = "attribute_id", one = @One(select = "mybatisdemo.mybatisdemo.mapper.AttributeMapper.getAttributeById")),
    })
    List<AttributeValue> selectAllAttributeValuesForDict(UUID dictId);
}
