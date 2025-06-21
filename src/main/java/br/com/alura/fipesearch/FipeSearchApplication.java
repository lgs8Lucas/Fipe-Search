package br.com.alura.fipesearch;

import br.com.alura.fipesearch.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeSearchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FipeSearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main();
        main.showMenu();
    }

}
