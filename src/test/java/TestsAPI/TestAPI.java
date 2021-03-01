package TestsAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class TestAPI extends MassaDados {

    @BeforeClass
    public static void url_base() {
        RestAssured.baseURI = "https://api.thecatapi.com/v1/";
    }

    @Test
    public void cadastrar_email() {

        Response response = given()
                .contentType("application/json").body(corpo_cadastro)
                .when().post(url_cadastro);
        validacao(response);

    }

    @Test
    public void votar() {

        Response response = given()
                .contentType("application/json").body(corpo_voto)
                .when().post(url_voto);
        validacao(response);

        String id = response.jsonPath().getString("id");
        vote_id = id;


    }

    @Test
    public void apagarVotacao() {
        votar();
        apagarVoto();

    }

    private void apagarVoto() {

        String url = "votes/{vote_id}";

        Response response = given()
                .contentType("application/json")
                .header("x-api-key", "dbeab9620-8152-41de-9db8-0c21bccb0793")
                .pathParam("vote_id", vote_id)
                .when().delete(url);

        validacao(response);
    }

    @Test
    public void favoritaDesfavorita() {
        favorita();
        desfavorita();

    }

    private void favorita() {

        Response response =
                given()
                        .contentType("application/json")
                        .header("x-api-key", "beab9620-8152-41de-9db8-0c21bccb0793")
                        .body(corpo_favorita)
                        .when().post("favourites");
        String id = response.jsonPath().getString("id");
        vote_id = id;

        validacao(response);
    }

    private void desfavorita() {

        Response response =
                given()
                        .contentType("application/json")
                        .header("x-api-key", "beab9620-8152-41de-9db8-0c21bccb0793")
                        .pathParam("favourite_id", vote_id)
                        .when().delete(corpo_desfavorita);
        validacao(response);

    }

    public void validacao(Response response) {
        response.then().statusCode(200).body("message", containsString("SUCCESS"));
        System.out.println("RETORNO API =>" + response.body().asString());
        System.out.println("___________________________________________");
    }

}
