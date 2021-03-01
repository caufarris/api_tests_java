package TestsAPI;

public class MassaDados {
    String vote_id;

    String url_cadastro = "user/passwordlesssignup";
    String corpo_cadastro = "{\"email\": \"claudiafarias73@gmail.com\", \"appDescription\": \"Testes API JAVA MAVEN\"}";

    String url_voto = "votes/";
    String corpo_voto = "{\"image_id\": \"4co\", \"value\": true, \"sub_id\": \"demo-f07961\"}";
    String corpo_favorita = "{\"image_id\": \"d4v\"}";
    String corpo_desfavorita = "favourites/{favourite_id}";

}
