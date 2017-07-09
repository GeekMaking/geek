import com.geek.mapper.JoinMapper;
import com.geek.model.Join;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JoinTest {

    ApplicationContext applicationContext;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    JoinMapper joinMapper;

    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
        sqlSession = sqlSessionFactory.openSession();
        joinMapper = sqlSession.getMapper(JoinMapper.class);
    }

    @Test
    public void addJoin(){
        Join join = new Join();
        join.setJoinId("2015211087");
        join.setJoinName("袁阳");
        join.setJoinPhone("18875062338");
        join.setJoinEmail("1094280936@qq.com");
        joinMapper.insertJoin(join);
    }

    @Test
    public void findJoin(){
        Join join = joinMapper.findJoin("2015211087");
        System.out.println(join);
    }

    @Test
    public void findAllJoin(){
        List<Join> joins = joinMapper.findAllJoin();
        System.out.println(joins);
    }
    @Test
    public void deleteJoin(){
        int i = joinMapper.deleteJoin("2015211087");
    }
}
