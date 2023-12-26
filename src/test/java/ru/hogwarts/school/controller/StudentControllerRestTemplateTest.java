package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerRestTemplateTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateAStudent() {
        //входные данные
        Student testStudent = new Student("Гриффиндорец", 20);

        //ожидаемый результат
        Student expectedStudent = new Student("Гриффиндорец", 20);

        //начало теста
        Student actualStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", testStudent, Student.class);
        expectedStudent.setId(actualStudent.getId());
        assertNotNull(actualStudent);
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void shouldGetAStudent() {
        //входные данные
        Student testStudent = new Student("Пуффендуец", 18);

        //ожидаемый результат
        Student expectedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", testStudent, Student.class);
        long id = expectedStudent.getId();

        //начало теста
        Student actualStudent = this.restTemplate.getForObject("http://localhost:" + port + "/student?id=" + id, Student.class);
        assertNotNull(actualStudent);
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void shouldUpdateAStudent() {
        //входные данные
        Student testStudent = new Student("Пуффендуец", 18);

        //ожидаемый результат
        Student addedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", testStudent, Student.class);
        long id = addedStudent.getId();

        Student expectedStudent = new Student("Гриффиндорец", 19);
        expectedStudent.setId(id);

        //начало теста
        this.restTemplate.put("http://localhost:" + port + "/student", expectedStudent);
        Student actualStudent = this.restTemplate.getForObject("http://localhost:" + port + "/student?id=" + id, Student.class);
        assertNotNull(actualStudent);
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void shouldDeleteAStudent() {
        //входные данные
        Student testStudent = new Student("Когтевранец", 17);

        //ожидаемый результат
        Student expectedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", testStudent, Student.class);
        long id = expectedStudent.getId();

        //начало теста
        Student actualStudent = this.restTemplate.getForObject("http://localhost:" + port + "/student?id=" + id, Student.class);
        assertNotNull(actualStudent);
        assertEquals(expectedStudent, actualStudent);
        this.restTemplate.delete("http://localhost:" + port + "/student?id=" + id);
        Student expelledStudent = this.restTemplate.getForObject("http://localhost:" + port + "/student?id=" + id, Student.class);
        assertNull(expelledStudent.getId());
        assertNull(expelledStudent.getName());
    }
}
