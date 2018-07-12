package data;

import data.services.FileStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Resource
    FileStorage fileStorage;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileStorage.deleteAll();
        fileStorage.init();
    }
}

