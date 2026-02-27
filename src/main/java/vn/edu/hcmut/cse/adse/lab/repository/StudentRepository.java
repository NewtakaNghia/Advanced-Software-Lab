package vn.edu.hcmut.cse.adse.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.cse.adse.lab.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // No need to implement
}
