package one.digitalinnovation.personapi.controller;


import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Phone;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @Test
    public void givenPersonDTOWhenCreatePerson() {
        PersonDTO personDTO = createFakeDTO();
        when(personService.createPerson(personDTO))
                .thenReturn(MessageResponseDTO.builder().message("Person created with success")
                        .build()
                );
        MessageResponseDTO messageResponseDTO = personController.createPerson(personDTO);
        assertEquals("Person created with success", messageResponseDTO.getMessage());
    }
    @Test
    public void shouldListOfPerson() {
        List<PhoneDTO> phones = new ArrayList<>();

        PhoneDTO phoneDTO = PhoneDTO.builder()
                .id(1L)
                .number("11983375610")
                .build();
        phones.add(phoneDTO);

    when(personService.listAll()).thenReturn(
            List.of(PersonDTO.builder()
                                .Id(1L)
                                .firstName("Test")
                                .lastName("created")
                                .cpf("12345678901")
                                .birthDate("11-10-2001")
                                .phones(phones)
                                .build()
            )
    );

        List<PersonDTO> people = personController.listAll();

        assertEquals(1, people.size());
        assertEquals(1L, people.get(0).getId());
        assertEquals("Test", people.get(0).getFirstName());
        assertEquals("created", people.get(0).getLastName());
        assertEquals("12345678901", people.get(0).getCpf());
        assertEquals("11-10-2001", people.get(0).getBirthDate());
        assertEquals(phones, people.get(0).getPhones());

    }

    @Test
    public void shouldFindPersonById() throws PersonNotFoundException {

        List<PhoneDTO> phones = new ArrayList<>();

        PhoneDTO phoneDTO = PhoneDTO.builder()
                .id(1L)
                .number("11983375610")
                .build();
        phones.add(phoneDTO);

       PersonDTO person = PersonDTO.builder()
                .Id(1L)
                .firstName("Test")
                .lastName("created")
                .cpf("12345678901")
                .birthDate("11-10-2001")
                .phones(phones)
                .build();

        when(personService.findById(1L)).thenReturn(person);

        person = personController.findById(1L);

        assertEquals(1L, person.getId());
        assertEquals("Test", person.getFirstName());
        assertEquals("created", person.getLastName());
        assertEquals("12345678901", person.getCpf());
        assertEquals("11-10-2001", person.getBirthDate());
        assertEquals(phones, person.getPhones());
    }

    @Test
    public void sholdThrowExceptionWhenPersonNotFound() throws PersonNotFoundException {

        when(personService.findById(1L)).thenThrow(PersonNotFoundException.class);

        assertThrows(PersonNotFoundException.class, () -> personController.findById(1L));

    }

    @Test
    public void shouldUpdatePersonById() throws PersonNotFoundException {
        List<PhoneDTO> phones = new ArrayList<>();

        PhoneDTO phoneDTO = PhoneDTO.builder()
                .id(1L)
                .number("11983375610")
                .build();
        phones.add(phoneDTO);

        PersonDTO person = PersonDTO.builder()
                .Id(1L)
                .firstName("Test")
                .lastName("created")
                .cpf("12345678901")
                .birthDate("11-10-2001")
                .phones(phones)
                .build();

        when(personService.updateById(1L, person)).thenReturn(MessageResponseDTO.builder()
                        .message("Person updated with success")
                .build()
        );
        MessageResponseDTO messageResponse = personController.updateById(1L, person);

        assertEquals("Person updated with success", messageResponse.getMessage());
    }

    @Test
    public void shouldDeletepersonById() throws PersonNotFoundException {
        doNothing().when(personService).delete(1L);

        personController.deleteById(1l);
    }

}