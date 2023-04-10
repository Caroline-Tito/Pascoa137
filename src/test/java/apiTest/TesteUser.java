// nome do pacote
package apiTest;

// Blibliotecas


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.*;

// Classe
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Test @Order(1)
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

    @Test @Order(2)
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
    @Test @Order(3)
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
    @Test @Order(4)
    public void testarExcluirUser(){ // inicio do delete user
        String username = "carol";
        given()
                .contentType(ct)
                .log().all()
        .when()
                .delete(uriUser + username)
        .then()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(username))
        ;


    } // fim do delete user

    @Test @Order(5)
    public void testarLogin(){ // inicio do login
        String username = "carol";
        String password = "123456";

        Response response = (Response) given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + "login?username=" + username +"$password=" + password)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", containsString("logged in user session:"))
                .body("message", hasLength(36))
        .extract()
        ;
        // extração do token da resposta
        String token = response.jsonPath().getString("message").substring(23);
        System.out.println("O conteudo do Token: " + token);

    } // fim do login

    @ParameterizedTest @Order(6)
    @CsvFileSource(resources = "csv/massaUser.csv", numLinesToSkip = 1, delimiter = ',')
    public void TestarIncluirUserCSV(
            String id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            String userStatus)
    { //inicio Incluir CSV
            //carregar os dados do nosso json
            /*

            StringBuilder jsonBody = new StringBuilder("{");
            jsonBody.append("'id': " + id + ",");
            jsonBody.append("'username': " + username + ",");
            jsonBody.append("'firstName': " + firstName + ",");
            jsonBody.append("'lastName': " + lastName + ",");
            jsonBody.append("'email': " + email + ",");
            jsonBody.append("'password': " + password+ ",");
            jsonBody.append("'phone': " + phone + ",");
            jsonBody.append("'userStatus': " + userStatus);
            jsonBody.append("}");
            */
            User user = new User(); //instancia a classe User

            user.id = id;
            user.username = username;
            user.firstName = firstName;
            user.lastName = lastName;
            user.email = email;
            user.password = password;
            user.phone = phone;
            user.userStatus = userStatus;

            Gson gson = new Gson(); // instancia a classe Gson
            String jsonBody = gson.toJson(user);



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
                    .body("message", is(id))                      // message é o userid
            ;

    } // fim do incluir csv




} // fim da classe

