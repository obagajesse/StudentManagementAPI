package com.obaga.StudentApp.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        studentRepository.findStudentByEmail(student.getEmail());
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email unavailable");
        }
       studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId);
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with id" + studentId + "does not exist");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
   public void updateStudent(Long studentId,String name,String email){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id" + studentId + "does not exist"));

        if(name != null &&
        name.length() > 0 &&
        !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null &&
        email.length() > 0 &&
        !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email unavailable");
            }

            student.setEmail(email);
        }
   }
}