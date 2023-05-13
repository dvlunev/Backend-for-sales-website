package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.controller.AuthController;
import ru.skypro.homework.dto.LoginReqDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.AuthService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;
    @Mock
    private AuthService authService;
    private final Role userRole = Role.USER;
    private final LoginReqDto req = new LoginReqDto();
    private final RegisterReqDto reqReg = new RegisterReqDto();


    @BeforeEach
    public void setUp() {
        req.setUsername("testuser");
        req.setPassword("testpassword");

        reqReg.setUsername("testuser");
        reqReg.setPassword("testpassword");
    }

    @Test
    public void loginSuccessTest() {
        when(authService.login(req.getUsername(), req.getPassword())).thenReturn(true);

        ResponseEntity<?> responseEntity = authController.login(req);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(authService).login(req.getUsername(), req.getPassword());
    }

    @Test
    void loginFailureTest() {
        when(authService.login(req.getUsername(), req.getPassword())).thenReturn(false);

        ResponseEntity<?> responseEntity = authController.login(req);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        verify(authService).login(req.getUsername(), req.getPassword());
    }

    @Test
    public void registerSuccessTest() {
        when(authService.register(reqReg, userRole)).thenReturn(true);

        ResponseEntity<?> response = authController.register(reqReg);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(authService).register(reqReg, userRole);
    }

    @Test
    void registerFailureTest() {
        when(authService.register(reqReg, userRole)).thenReturn(false);

        ResponseEntity<?> response = authController.register(reqReg);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(authService).register(reqReg, userRole);
    }
}
