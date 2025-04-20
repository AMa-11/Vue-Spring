package task.crypto.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/me")
    public User getUserData(@PathVariable int id) {
        return new User(id);
    }

    @GetMapping("/me/balance")
    public String getBalance(){
        return "10000";
    }

    @GetMapping("/me/transactions")
    public String getTransactionHistory(){
        return "10000";
    }

}
