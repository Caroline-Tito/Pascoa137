// nome do pacote
package apiTest;

// Blibliotecas


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// Classe
public class TesteUser { // inicio da classe

    // Atributos

    static String ct = "application/json"; // content type
    static String uriUser = "https://petstore.swagger.io/v2/user/";



    // Funções e métodos
    //Funções de apoio

    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }


    //Funções de Teste
    @Test
    public void testarIncluirUser() throws IOException {
        //carregar os dados do nosso json
        String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");
        String userId = "1371102533";

        // realizar o teste
        given()                                                 // dado que
                .contentType(ct)                               // o tipo de conteúdo
                .log().all()                                    // mostre tudo
                .body(jsonBody)                              // corpo da requisição
        .when()                                               // Quando
                .post(uriUser)                               //Endpoint/ onde
        .then()                                                 // então
                .log().all()                                    // mostre tudo na volta
                .statusCode(200)                               //comunicação ida e volta ok
                .body("code", is(200))                  // tag code é 200
                .body("type", is("unknown"))           // tag type é "unknown
                .body("message", is(userId))                      // message é o userid
        ;
    } // fim do Post

    @Test
    public void testarConsultarUser(){
        String username = "carol";

        // Resultado Esperado
        int userId = 1371102533;                  // Código do usuário
        String email = "charlie@gmail.com";
        String senha = "123456";
        String telefone = "45454545";

        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(userId))
                .body("email", is(email))
                .body("password", is(senha))
                .body("phone", is(telefone))
        ;

    } // fim do get User
    @Test
    public void testarAlterarUser() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");

        String userId = "1371102533";
        String username = "carol";

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .put(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))
        ;

    }

    




} // fim da classe

