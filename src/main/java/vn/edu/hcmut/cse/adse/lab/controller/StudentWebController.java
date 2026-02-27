package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// @Controller đánh dấu lớp này trả về View chứ không phải Data như @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import vn.edu.hcmut.cse.adse.lab.entity.Student;

import java.util.List;
@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    // GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(Model model) {
        // 1. Lấy dữ liệu từ service
        List<Student> students = service.getAll();

        // 2.Đóng gói dữ liệu vào "Model" để sang View
        // key dsSinhVien sẽ được sử dụng bên file HTML
        model.addAttribute("dsSinhVien", students);

        // 3. TRả về tên của View (Không cần đuôi .html)
        // Spring tự động tìm file tại src/main/resources/templates/students.html
        return "students";
    }
}
