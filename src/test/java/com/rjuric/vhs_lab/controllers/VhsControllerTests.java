package com.rjuric.vhs_lab.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjuric.vhs_lab.config.JwtAuthenticationFilter;
import com.rjuric.vhs_lab.dtos.CreateVhsDTO;
import com.rjuric.vhs_lab.dtos.UpdateVhsDTO;
import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.services.JwtServiceImpl;
import com.rjuric.vhs_lab.services.VhsService;
import com.rjuric.vhs_lab.util.errors.VhsNotFoundException;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(VhsController.class)
@Import(JwtServiceImpl.class)
public class VhsControllerTests {
    @MockBean
    VhsService vhsService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private CreateVhsDTO createVhsDTO;
    private Vhs expectedVhs;

    private UpdateVhsDTO updateVhsDTO;

    @Before
    public void init() {
        createVhsDTO = CreateVhsDTO
                .builder()
                .name("Godzilla")
                .description("A giant lizard wreaks havoc on New York City.")
                .build();

        updateVhsDTO = UpdateVhsDTO
                .builder()
                .id(1L)
                .name("Godzilla 2")
                .description("A giant lizard wreaks havoc on New York City, again.")
                .build();

        expectedVhs = Vhs
                .builder()
                .name(createVhsDTO.getName())
                .description(createVhsDTO.getDescription())
                .build();
        expectedVhs.setId(1L);

        when(vhsService.create(createVhsDTO.getName(), createVhsDTO.getDescription())).thenReturn(expectedVhs);
        when(vhsService.update(updateVhsDTO.getId(), updateVhsDTO.getName(), updateVhsDTO.getDescription())).thenReturn(expectedVhs);
    }

    // GET ALL

    @Test
    public void it_getAll_should_return_all() throws Exception {
        when(vhsService.getAll()).thenReturn(new ArrayList<Vhs>());

        mockMvc.perform(get("/vhs").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // GET BY ID

    @Test
    public void it_getById_should_return_vhs() throws Exception {
        Vhs expected = Vhs.builder()
                .name("Godzilla")
                .description("A giant lizard wreaks havoc on New York City.")
                .build();
        expected.setId(1L);
        when(vhsService.getById(1)).thenReturn(expected);

        mockMvc.perform(get("/vhs/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(expected.getId().intValue())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(expected.getName())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(expected.getDescription())));
    }

    @Test
    public void it_getById_should_throw_404() throws Exception {
        when(vhsService.getById(1)).thenThrow(new VhsNotFoundException("vhs.notFound"));

        mockMvc.perform(get("/vhs/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", CoreMatchers.is("vhs not found")))
                .andExpect(jsonPath("$.status", CoreMatchers.is(404)));
    }

    // CREATE
    
    @Test
    public void it_create_create_should_return_created_vhs() throws Exception {
        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createVhsDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(expectedVhs.getName())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(expectedVhs.getDescription())));
    }

    @Test
    public void it_create_should_throw_400_when_name_null() throws Exception {
        createVhsDTO.setName(null);

        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    @Test
    public void it_create_should_throw_400_when_name_empty_string() throws Exception {
        createVhsDTO.setName("");

        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    @Test
    public void it_create_should_throw_400_when_description_null() throws Exception {
        createVhsDTO.setDescription(null);

        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    @Test
    public void it_create_should_throw_400_when_description_under_20() throws Exception {
        String tooShort = "A".repeat(19);
        createVhsDTO.setDescription(tooShort);

        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    @Test
    public void it_create_should_throw_400_when_description_over_120() throws Exception {
        String tooLong = "A".repeat(121);
        createVhsDTO.setDescription(tooLong);

        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));

    }

    @Test
    public void it_create_should_throw_400_when_name_and_description_invalid() throws Exception {
        createVhsDTO.setDescription(null);
        createVhsDTO.setName(null);

        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    // UPDATE

    @Test
    public void it_update_should_return_new_vhs() throws Exception {
        expectedVhs.setName(updateVhsDTO.getName());
        expectedVhs.setDescription(updateVhsDTO.getDescription());

        mockMvc.perform(put("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateVhsDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(updateVhsDTO.getId().intValue())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(updateVhsDTO.getName())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(updateVhsDTO.getDescription())));
    }

    @Test
    public void it_update_should_throw_400_when_name_null() throws Exception {
        updateVhsDTO.setName(null);

        mockMvc.perform(put("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    @Test
    public void it_update_should_throw_400_when_name_empty_string() throws Exception {
        updateVhsDTO.setName("");

        mockMvc.perform(put("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    @Test
    public void it_update_should_throw_400_when_description_null() throws Exception {
        updateVhsDTO.setDescription(null);

        mockMvc.perform(put("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    @Test
    public void it_update_should_throw_400_when_description_under_20() throws Exception {
        String tooShort = "A".repeat(19);
        updateVhsDTO.setDescription(tooShort);

        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    @Test
    public void it_update_should_throw_400_when_description_over_120() throws Exception {
        String tooLong = "A".repeat(121);
        updateVhsDTO.setDescription(tooLong);

        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));

    }

    @Test
    public void it_update_should_throw_400_when_name_and_description_invalid() throws Exception {
        updateVhsDTO.setDescription(null);
        updateVhsDTO.setName(null);

        mockMvc.perform(post("/vhs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateVhsDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("validation failed")));
    }

    // DELETE

    @Test
    public void it_delete_should_return_204() throws Exception {
        ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(vhsService).delete(valueCapture.capture());

        mockMvc.perform(delete("/vhs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assert valueCapture.getValue().equals(1L);
    }
}
