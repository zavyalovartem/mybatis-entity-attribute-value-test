package mybatisdemo.mybatisdemo;

import mybatisdemo.mybatisdemo.entity.Attribute;
import mybatisdemo.mybatisdemo.entity.AttributeValue;
import mybatisdemo.mybatisdemo.entity.Dict;
import mybatisdemo.mybatisdemo.mapper.AttributeMapper;
import mybatisdemo.mybatisdemo.mapper.AttributeValueMapper;
import mybatisdemo.mybatisdemo.mapper.DictMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class MyBatisDemoApplication  {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisDemoApplication.class, args);

    }
}
