package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.repository.VhsRepository;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class VhsServiceTests {

    @Mock
    private VhsRepository vhsRepository;

    @InjectMocks
    private VhsServiceImpl sut;

    private Vhs expected;

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
    public void it_getAll_calls_repository() throws Exception {
        when(vhsRepository.findAll()).thenReturn(List.of(expected));

        List<Vhs> result = sut.getAll();
        assert result.contains(expected);
        assert result.size() == 1;
    }

    // GET BY ID

    @Test
    public void it_getById_returns_vhs() throws Exception {
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.ofNullable(expected));

        Vhs result = sut.getById(1L);
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void it_getById_throws_VhsNotFoundException() throws Exception {
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(VhsNotFoundException.class, () -> sut.getById(1L));
    }

    // CREATE

    @Test
    public void it_create_returns_saved_vhs() throws Exception {
        when(vhsRepository.save(any(Vhs.class))).thenReturn(expected);

        Vhs result = sut.create(expected.getName(), expected.getDescription());

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
        when(vhsRepository.save(any(Vhs.class))).thenReturn(newVhs);

        Vhs result = sut.update(2L, newVhs.getName(), newVhs.getDescription());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, newVhs);
    }

    @Test
    public void it_delete_calls_repository() {
        ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(vhsRepository).deleteById(valueCapture.capture());

        sut.delete(1);

        Assertions.assertEquals(valueCapture.getValue(), 1L);
        Assertions.assertEquals(valueCapture.getAllValues().size(), 1);
    }
}
