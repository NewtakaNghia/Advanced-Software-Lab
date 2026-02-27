package vn.edu.hcmut.cse.adse.lab.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;
import vn.edu.hcmut.cse.adse.lab.entity.*;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    public void save(Student student) {
        repository.save(student);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
