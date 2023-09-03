package mybatisdemo.mybatisdemo.mapper;

import mybatisdemo.mybatisdemo.entity.Attribute;
import mybatisdemo.mybatisdemo.entity.Dict;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface DictMapper {
    @Insert("insert into dicts.dict (id, parent_id) values (#{id}, #{parent.id})")
    void createNewDict(Dict dict);

    @Select("select * from dicts.dict where id = #{id}")
    @Results(id = "dictResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "parent", column = "parent_id", one = @One(select = "getDictById")),
            @Result(property = "attributeValues", column = "id", many = @Many(select = "mybatisdemo.mybatisdemo.mapper.AttributeValueMapper.selectAllAttributeValuesForDict")),
    })
    Dict getDictById(UUID id);
}
