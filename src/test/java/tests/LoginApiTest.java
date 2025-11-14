package tests;

import config.TestConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Disabled("Executar apenas com backend ativo")
public class LoginApiTest {

    @BeforeAll
    static void setup() {
        baseURI = TestConfig.getBaseUrl();
        basePath = "/api";
    }

    private static io.restassured.response.Response login(String email, String password) {
        return given()
                .contentType(ContentType.JSON)
                .body(String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password))
                .when()
                .post("/login");
    }

    @Test
    void TC1_200_loginValidoRetornaTokenEPerfil() {
        login(TestConfig.getUserEmail(), TestConfig.getUserPassword())
                .then()
                .statusCode(200)
                .body("token", not(emptyOrNullString()))
                .body("user.profile", equalTo("USER"));
    }

    @Test
    void TC2_401_credenciaisInvalidas() {
        login(TestConfig.getUserEmail(), "senha-errada-123")
                .then()
                .statusCode(401)
                .body("error", containsString("inválida"));
    }

    @Test
    void TC3_403_acessoNegado() {
        login(TestConfig.getVisitorEmail(), TestConfig.getUserPassword())
                .then()
                .statusCode(403)
                .body("error", containsString("acesso"))
                .body("error", containsString("negado"));
    }

    @Test
    void TC4_423_usuarioBloqueado() {
        login(TestConfig.getLockedUserEmail(), TestConfig.getUserPassword())
                .then()
                .statusCode(423)
                .body("error", containsString("bloqueado"));
    }
}