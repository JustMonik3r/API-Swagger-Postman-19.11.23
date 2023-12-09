package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(facultyController).isNotNull();
    }

    @Test
    void shouldCreateAFaculty(){
        //входные данные
        Faculty testFaculty = new Faculty("Gryffindor", "red");

        //ожидаемый результат
        Faculty expectedFaculty = new Faculty("Gryffindor", "red");
        expectedFaculty.setId(1L);

        //начало теста
        Faculty actualFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", testFaculty, Faculty.class);
        assertNotNull(actualFaculty);
        assertEquals(expectedFaculty, actualFaculty);

    }

    @Test
    void shouldGetAFaculty() {
        //входные данные
        Faculty testFaculty = new Faculty("Slytherin", "green");

        //ожидаемый результат
        Faculty expectedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", testFaculty, Faculty.class);
        long id = expectedFaculty.getId();

        //начало теста
        Faculty actualFaculty = this.restTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + id, Faculty.class);
        assertNotNull(actualFaculty);
        assertEquals(expectedFaculty, actualFaculty);
    }

    @Test
    void shouldUpdateAFaculty() {
        //входные данные
        Faculty testFaculty = new Faculty("Ravenclaw", "purple");

        //ожидаемый результат
        Faculty addedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", testFaculty, Faculty.class);

        long id = addedFaculty.getId();

        Faculty expectedFaculty = new Faculty("Когтевран", "фиолетовый");
        expectedFaculty.setId(id);

        //начало теста
        this.restTemplate.put("http://localhost:" + port + "/faculty", expectedFaculty, Faculty.class);
        Faculty actualFaculty = this.restTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + id, Faculty.class);
        assertNotNull(actualFaculty);
        assertEquals(expectedFaculty, actualFaculty);
    }

    @Test
    void shouldDeleteAFaculty() {
        //входные данные
        Faculty testFaculty = new Faculty("Gryffindor", "red");

        //ожидаемый результат
        Faculty expectedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", testFaculty, Faculty.class);
        long id = expectedFaculty.getId();

        //начало теста
        Faculty actualFaculty = this.restTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + id, Faculty.class);
        assertNotNull(actualFaculty);
        assertEquals(expectedFaculty, actualFaculty);
        this.restTemplate.delete("http://localhost:" + port + "/faculty?id=" + id);
        Faculty removedFaculty = this.restTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + id, Faculty.class);
        assertNull(removedFaculty.getId());
        assertNull(removedFaculty.getColor());
        assertNull(removedFaculty.getName());
    }
}
