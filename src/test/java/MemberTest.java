import com.geek.mapper.MemberMapper;
import com.geek.model.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MemberTest {

    ApplicationContext applicationContext;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    MemberMapper memberMapper;

    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
        sqlSession = sqlSessionFactory.openSession();
        memberMapper = sqlSession.getMapper(MemberMapper.class);
    }

    @Test
    public void findMember(){
        Member member = memberMapper.findMember("2015211087");
        System.out.println(member);
    }

    @Test
    public void findMemberImages(){
        String[] memberId = {"2015211087","2015211085"};
        List<String> images = memberMapper.findImagePaths(memberId);
        System.out.println(images.get(0) + images.get(1));
    }

}
