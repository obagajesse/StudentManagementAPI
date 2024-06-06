package com.obaga.StudentApp.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student john = new Student(
                    "John",
                    "JohnJuma@gmail.com",
                    LocalDate.of(2000, Month.JANUARY,6)
            );

            Student tim = new Student(
                    "Tim",
                    "TimothyMaina@yahoo.com",
                    LocalDate.of(2003,Month.JULY,21)

            );

            Student june = new Student(
                    "June",
                    "JuneKariuki@hotmail.com",
                    LocalDate.of(2005,Month.DECEMBER,7)

            );

            repository.saveAll(
                    List.of(john,tim,june)
            );
        };
    }
}
