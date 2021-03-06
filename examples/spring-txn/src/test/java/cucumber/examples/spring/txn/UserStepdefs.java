package cucumber.examples.spring.txn;

import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class UserStepdefs {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    private User user;

    public void thereIsAuser() {
        user = new User();
        user.setUsername("testuser");
        userRepository.save(user);
    }

    @Given("^a User has posted the following messages:$")
    public void a_User_has_posted_the_following_messages(List<Message> messages) throws Throwable {
        thereIsAuser();
        for (Message m : messages) {
            m.setAuthor(user);
            messageRepository.save(m);
        }
    }
}
