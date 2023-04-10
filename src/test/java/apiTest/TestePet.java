// Nome do pacote
package apiTest;

// Bibliotecas

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


// classe
    public class TestePet { // inicio da classe
    // atributos

    String ct2 = "application/json"; // content type
    String uriPet = "https://petstore.swagger.io/v2/pet/";


    // funções e métodos
    // funções de apoio

    public static String lerArquivoJsonPet(String arquivoJasonPet) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJasonPet)));
    }


    // funções de teste
    @Test

    public void testarIncluirPet() throws IOException {
        // carregar os dados do json
        String jsonBody = lerArquivoJsonPet("src/test/resources/json/pet1.json");


        // realizar o teste
        given()
                .contentType(ct2)
                .log().all()
                .body(jsonBody)
        .when()
                .post("https://petstore.swagger.io/v2/pet/")
        .then()
                .log().all()
                .statusCode(200)
                .body("category.name", is("cat"))
                .body("tags[0].name", is("vacinado"))
                .body("name", is("leo"))
                .body("status", is("available"))
                ;

    } // fim do post

    @Test
    public void testarConsultarPet() {
        Integer petId = 1372533;

        // resultado esperado
        int petId2 = 2;  // codigo da categoria
        String tipo = "cat";
        String tags = "vacinado";
        String name = "leo";
        String status = "available";


        given()
                .contentType(ct2)
                .log().all()
                .when()
                .get(uriPet + petId)
                .then()
                .log().all()
                .statusCode(200)
                .body("category.name", is(tipo))
                .body("tags[0].name", is(tags))
                .body("name", is(name))
                .body("status", is(status))

        ;
    }
    @Test
    public void testarAlterarPet() throws IOException {
        String jsonBody = lerArquivoJsonPet("src/test/resources/json/pet2.json");
        given()
                .contentType(ct2)
                .log().all()
                .body(jsonBody)
                .when()
                .put(uriPet)
                .then()
                .log().all()
                .statusCode(200)
                .body("category.name", is("cat"))
                .body("tags[0].name", is("tosado"))
                .body("name", is("leo"))
                .body("status", is("available"))
        ;
    }
    @Test
    public void testarExcluirPet(){
        Integer petId = 1372533;
            given()
                    .contentType(ct2)
                    .log().all()
            .when()
                    .delete(uriPet + petId)
            .then()
                    .statusCode(200)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", is("1372533"))

            ;


    }




}