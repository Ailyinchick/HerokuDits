package incubator.dao;

import incubator.model.Question;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class QuestionRepository implements DaoRepos<Question>{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    /**
     * Method returns rate of correct answers for question
     * @param x
     * @return
     */

    public double questionDifficulty(int x) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/managementsystem?verifyServerCertificate=false&useSSL=true", "root", "123qwe");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select questiondifficulty("+x+")");
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (ClassNotFoundException ex1) {
            System.out.println("Class not found EX " + ex1.getMessage());
        } catch (SQLException ex2) {
            System.out.println("SQL EX " + ex2.getMessage());
        }
        return 1;
    }

}
