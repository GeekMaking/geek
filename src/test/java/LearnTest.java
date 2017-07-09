import com.geek.mapper.LearnMapper;
import com.geek.model.Learn;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LearnTest {

    ApplicationContext applicationContext;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    LearnMapper learnMapper;

    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
        sqlSession = sqlSessionFactory.openSession();
        learnMapper = sqlSession.getMapper(LearnMapper.class);
    }

    @Test
    public void addLearn(){

        Learn learn = new Learn();
        learn.setLearnId("1");
        learn.setLearnName("Java从入门到精通");
        learn.setLearnType("书籍");
        learn.setLearnDesc("牛逼");
        learn.setImagePath("A001");

        learnMapper.addLearn(learn);
    }

}
