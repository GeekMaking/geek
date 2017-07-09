import com.geek.mapper.SuggestionMapper;
import com.geek.model.Suggestion;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SuggestionTest {

    ApplicationContext applicationContext;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    SuggestionMapper suggestionMapper;

    {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
        sqlSession = sqlSessionFactory.openSession();
        suggestionMapper = sqlSession.getMapper(SuggestionMapper.class);
    }

    @Test
    public void addSuggestion(){
        Suggestion suggestion = new Suggestion();
        suggestion.setPhone("18875062338");
        suggestion.setTime(new Date());
        suggestion.setView("棒棒糖");
        int i = suggestionMapper.insertSuggestion(suggestion);
        System.out.println(i);
    }

    @Test
    public void deleteSuggestion() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2017-02-09 20:26:53");
        System.out.println(date);
        System.out.println(simpleDateFormat.parse("2017-02-09 20:26:53"));
        int i = suggestionMapper.deleteSuggestion(simpleDateFormat.parse("2017-02-06 18:14:42"));
        System.out.println(i);
    }

    @Test
    public void findAllSuggestion(){
        List<Suggestion> suggestions = suggestionMapper.findAllSuggestion();
        System.out.println(suggestions.size());
    }

}
