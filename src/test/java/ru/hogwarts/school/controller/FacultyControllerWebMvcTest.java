package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.service.StudentServiceImpl;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentServiceImpl studentServiceImpl;
    @SpyBean
    private FacultyServiceImpl facultyServiceImpl;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void FacultyCreateTest () throws Exception {
        final String name = "test1";
        final String color = "test1";
        final Long id = 1L;


        JSONObject objectFaculty = new JSONObject();
        objectFaculty.put("name", name);
        objectFaculty.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(objectFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void FacultyFindTest() throws Exception {
        final String name = "test1";
        final String color = "test1";
        final Long id = 1L;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }
    @Test
    public void FacultyEditTest() throws Exception {
        final String name = "test1";
        final String color = "test1";
        final String name1 = "test2";
        final String color1 = "test2";
        final Long id = 1L;

        JSONObject objectFaculty2 = new JSONObject();
        objectFaculty2.put("name", name1);
        objectFaculty2.put("color", color1);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/1")
                        .content(objectFaculty2.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));
    }
    @Test
    public void FacultyDeleteTest() throws Exception {
        final String name = "test1";
        final String color = "test1";
        final Long id = 1L;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }
    @Test
    void shouldGetStudentsFromCertainFaculty() throws Exception {
        //Подготовка входных данных
        Faculty testFaculty = new Faculty();

        //Подготовка ожидаемого результата
        Student student1 = new Student("Ибрагим", 36);
        Student student2 = new Student("Абдулла", 48);

        List<Student> students = Arrays.asList(student1, student2);

        when(studentServiceImpl.findStudentsFromFaculty(testFaculty)).thenReturn(students);

        //Начало теста

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty/students-faculty")
                                .param("faculty", String.valueOf(testFaculty)))
                .andExpect(status().isOk())
                .andReturn();

        List<Student> actualStudents = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Student>>() {
        });
        assertEquals(students, actualStudents);
    }
}
