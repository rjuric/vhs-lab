package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.CreateVhsDTO;
import com.rjuric.vhs_lab.dtos.UpdateVhsDTO;
import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.services.VhsService;
import com.rjuric.vhs_lab.util.errors.VhsNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class VhsControllerTests {

    @Mock
    private VhsService vhsService;

    @InjectMocks
    private VhsController sut;

    private Vhs expected;

    private final CreateVhsDTO createVhsDTO = CreateVhsDTO.builder()
            .name("Godzilla")
            .description("A giant lizard is awoken in Japan due to radiation. This one is friendly.")
            .build();

    private final UpdateVhsDTO updateVhsDto = UpdateVhsDTO.builder()
            .id(1L)
            .name("Godzilla")
            .description("A giant lizard is awoken in Japan due to radiation. This one is friendly.")
            .build();

    @Before
    public void init() {
        expected = Vhs
                .builder()
                .name("Godzilla")
                .description("A giant lizard is awoken in Japan due to radiation. This one is friendly.")
                .build();
    }

    // GET ALL

    @Test
    public void it_getAll_calls_service() throws Exception {
        when(vhsService.getAll()).thenReturn(List.of(expected));

        List<Vhs> result = sut.getAll();
        assert result.contains(expected);
        assert result.size() == 1;
    }

    // GET BY ID

    @Test
    public void it_getById_returns_vhs() throws Exception {
        when(vhsService.getById(anyLong())).thenReturn(expected);

        Vhs result = sut.getById(1L);
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void it_getById_throws_VhsNotFoundException() throws Exception {
        when(vhsService.getById(anyLong())).thenThrow(new VhsNotFoundException("message"));

        Assertions.assertThrows(VhsNotFoundException.class, () -> sut.getById(1L));
    }

    // CREATE

    @Test
    public void it_create_returns_saved_vhs() throws Exception {
        when(vhsService.create(any(String.class), any(String.class))).thenReturn(expected);

        Vhs result = sut.create(createVhsDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, expected);
    }

    // UPDATE

    @Test
    public void it_update_returns_updated_values() {
        Vhs newVhs = Vhs.builder()
                .name("Godzilla 2")
                .description("Godzilla is back again and updated this time")
                .build();
        newVhs.setId(1L);

        when(vhsService.update(any(Long.class), any(String.class), any(String.class))).thenReturn(newVhs);

        Vhs result = sut.update(updateVhsDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, newVhs);
    }

    @Test
    public void it_delete_calls_repository() {
        ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(vhsService).delete(valueCapture.capture());

        sut.delete(1);

        Assertions.assertEquals(valueCapture.getValue(), 1L);
        Assertions.assertEquals(valueCapture.getAllValues().size(), 1);
    }
}