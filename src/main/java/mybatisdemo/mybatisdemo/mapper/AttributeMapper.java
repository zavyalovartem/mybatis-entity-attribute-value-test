package mybatisdemo.mybatisdemo.mapper;

import mybatisdemo.mybatisdemo.entity.Attribute;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AttributeMapper {
    @Insert("insert into dicts.attributes (id, name) values (#{id}, #{name})")
    void createNewAttribute(Attribute attributeValue);

    @Select("select * from dicts.attributes where id = #{id}")
    Attribute getAttributeById(UUID id);
}
