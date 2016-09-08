package cn.edu.hpu.mybatis.first;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.edu.hpu.mybatis.PO.User;

public class MyBatisfirst {

    private SqlSession sqlSession = null;

    @Test
    public void findUserById() {
        String resource = "SqlMapConfig.xml";

        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(inputStream);

            sqlSession = sqlSessionFactory.openSession();
            User user = sqlSession.selectOne("test.findUserById", 1);
            System.out.println(user.getUsername());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void findUserByName() {
        String resource = "SqlMapConfig.xml";

        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(inputStream);

            sqlSession = sqlSessionFactory.openSession();
            List<User> users = sqlSession.selectList("test.findUserByUsername",
                    "张三");
            for (User user : users) {
                System.out.println(user.getId() + ":" + user.getUsername());
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

}
