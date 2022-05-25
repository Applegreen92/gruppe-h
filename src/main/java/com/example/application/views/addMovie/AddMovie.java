package com.example.application.views.addMovie;
import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieService;
import com.example.application.views.MainLayout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.atmosphere.config.service.Post;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@PageTitle("AddMovie")
@Route(value = "addmovie", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class AddMovie extends Div {

    public AddMovie(){
        add(saveMovieLayout());
    }


    protected Component saveMovieLayout(){

        // Textfields wich defines a Movie Object
        TextField textFieldMovieId = new TextField("MovieID");
        TextField textFieldTitle = new TextField("Movie Title");
        TextField textFieldPosterSrc = new TextField("Postersrc");
        TextField textFieldreleaseDate = new TextField("Release date");
        TextField textFieldLength = new TextField("Movie length");

        VerticalLayout layout = new VerticalLayout();

        //Add Textfields to the layout
        layout.add(
                textFieldMovieId,
                textFieldTitle,
                textFieldPosterSrc,
                textFieldreleaseDate,
                textFieldLength);
        // WIth this Button you put the Movie Object into the DB

        layout.add(new Button("Save Movie",
                buttonClickEvent -> {
                    try {
                        saveMovieInDatabase(

                                Integer.valueOf(textFieldMovieId.getValue()),
                                textFieldTitle.getValue(),
                                textFieldPosterSrc.getValue(),
                                Integer.valueOf(textFieldreleaseDate.getValue()),
                                Integer.valueOf(textFieldLength.getValue()));
                    } catch (IOException e) {
                        System.out.println("Could not put Movie into Database");
                        throw new RuntimeException(e);
                    }
                }));


        return layout;
    }




    public void saveMovieInDatabase(int movieId, String title, String posterSrc, int releaseDate, int length) throws IOException {
        String movieString = new ObjectMapper().writeValueAsString(new Movie(movieId,title,posterSrc,releaseDate,length));

        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost post = new HttpPost("http://localhost:8080/movie/postmovie");
        post.setEntity(new StringEntity(movieString));
        post.setHeader("Accept","application/json");
        post.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(post);
        System.out.println(response.getStatusLine().getStatusCode());

    }

}
