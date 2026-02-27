package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// @Controller đánh dấu lớp này trả về View chứ không phải Data như @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import vn.edu.hcmut.cse.adse.lab.entity.Student;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    // GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if(keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }

        model.addAttribute("dsSinhVien", students);

        return "students";
    }

    // GET http://localhost:8080/students/{id}
    @GetMapping("/{id}")
    public String getStudentById(@PathVariable String id, Model model) {
        // Lấy thông tin của một sinh viên và chuyển hướng sang 
        // trang thông tin độc nhất của người đấy.
        Student student = service.getById(id);

        model.addAttribute("student", student);

        return "student-detail";
    }
    
    // GET http://localhost:8080/students/create
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-create";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") Student student) {
        service.save(student);
        return "redirect:/students";
    }

    // GET http://localhost:8080/students/edit/{id}
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        // Gửi đối tượng chứa dữ liệu cũ xuống form
        model.addAttribute("student", student);
        return "student-edit"; // Trả về file student-edit.html
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute("student") Student student) {
        student.setId(id);
        service.save(student); // JPA thấy ID đã có -> thực hiện lệnh UPDATE
        return "redirect:/students"; // Điều hướng về trang danh sách
    }

    // Xử lý xóa sinh viên
    @PostMapping("delete/{id}") 
    public String deleteStudent(@PathVariable String id, @ModelAttribute("student") Student student) {
        service.deleteById(id);
        return "redirect:/students";
    }
}
