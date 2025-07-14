package com.n7;

import com.n7.pojo.User;
import com.n7.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class IoTServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IoTServerApplication.class, args);
    }

    
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            User user = new User();
            user.setUsername("ngan");
            user.setPassword(passwordEncoder.encode("123456"));  // Mã hóa mật khẩu ở đây
            user.setEmail("admin@example.com");
            user.setRole(User.Role.ADMIN);
            userRepo.save(user);
            System.out.println("User admin created!");
        };
    }
}
