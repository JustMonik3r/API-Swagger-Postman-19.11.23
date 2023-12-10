package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @SpyBean
    private FacultyServiceImpl facultyServiceImpl;
    @SpyBean
    private StudentServiceImpl studentServiceImpl;
    @SpyBean
    private AvatarServiceImpl avatarServiceImpl;
    @InjectMocks
    private StudentController studentController;


    @Test
    public void ShouldAddAStudent() throws Exception {

        //Входные данные
        final String name = "Адам";
        final Integer age = 1;
        final String name1 = "Ева";
        final Integer age1 = 2;
        final Long id = 1L;

        //Ожидаемый результат
        JSONObject objectStudent = new JSONObject();
        objectStudent.put("name", name);
        objectStudent.put("age", age);

        JSONObject objectStudent2 = new JSONObject();
        objectStudent2.put("name", name1);
        objectStudent2.put("age", age1);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(objectStudent.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void ShouldFindAStudent() throws Exception {

        //Входные данные
        final String name = "test1";
        final Integer age = 1;
        final Long id = 1L;

        //Ожидаемый результат
        JSONObject objectStudent = new JSONObject();
        objectStudent.put("name", name);
        objectStudent.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);


        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .content(objectStudent.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
    @Test
    public void ShouldUpdateAStudent() throws Exception {

        //Входные данные
        final String name = "test1";
        final Integer age = 1;
        final String name1 = "test2";
        final Integer age1 = 2;
        final Long id = 1L;

        //Ожидаемый результат
        JSONObject objectStudent2 = new JSONObject();
        objectStudent2.put("name", name1);
        objectStudent2.put("age", age1);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/1")
                        .content(objectStudent2.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.age").value(age1));
    }
    @Test
    public void ShouldDeleteAStudent() throws Exception {

        //Входные данные
        final String name = "test1";
        final Integer age = 1;
        final String name1 = "test2";
        final Integer age1 = 2;
        final Long id = 1L;

        //Ожидаемый результат
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
}
