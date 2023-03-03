package com.example.dreamgames;

/*import com.example.dreamgames.Model.User;
import com.example.dreamgames.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(JpaTestConfig.class)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSave_thenReturnUser() {
        // given
        User user = new User();
        user.setLevel(1);
        user.setCoins(5000);

        // when
        User savedUser = userRepository.save(user);
        entityManager.flush();

        // then
        User foundUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(foundUser.getLevel(), user.getLevel());
        assertEquals(foundUser.getCoins(), user.getCoins());
    }
}

 */
