import com.geek.mapper.UserMapper;
import com.geek.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserTest {
    ApplicationContext applicationContext;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    UserMapper userMapper;

    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void findAllUser(){
        List<User> users = userMapper.getAllUser();
        System.out.println(users);
    }
    @Test
    public void modifyUser(){
        User user = new User();
        user.setUserName("sa");
        user.setPassword("1");
        int i = userMapper.updateUser(user);
        System.out.println(i);
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setUserName("sc");
        user.setPassword("3");
        int i = userMapper.insertUser(user);
        System.out.println(i);
    }

    @Test
    public void deleteUser(){
        String userName = "sc";
        int i = userMapper.deleteUser(userName);
        System.out.println(i);
    }
}
