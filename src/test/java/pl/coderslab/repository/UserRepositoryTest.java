package pl.coderslab.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.entity.Bet;
import pl.coderslab.entity.BetSLip;
import pl.coderslab.entity.User;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername() {
        //given
        User user = new User();

        user.setUsername("Ulisses");
        user.setEmail("test@test");
        entityManager.persist(user);


        //when
        User result = userRepository.findByUsername("Ulisses");

        //then
        assertEquals(result.getUsername(), user.getUsername());
    }

    @Test
    public void findFirstByEmail() {
        //given
        User user = new User();

        user.setUsername("Ulisses");
        user.setEmail("test@test");
        entityManager.persist(user);


        //when
        User result = userRepository.findFirstByEmail("test@test");

        //then
        assertEquals(result.getEmail(), user.getEmail());
    }
}