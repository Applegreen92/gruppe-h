package com.model;



import com.model.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawler {

    public static void getMovies(String genre) throws IOException{

        try {
            //Navigates to the IMDB website based on the passed genre and copy's html-code into the document 'movies'
            Document movies = Jsoup.connect("https://www.imdb.com/search/title/?genres="+genre+"&title_type=feature&explore=genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=facfbd0c-6f3d-4c05-9348-22eebd58852e&pf_rd_r=EJ5YBM5AY176J6Y11SY7&pf_rd_s=center-6&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_mvpop_1").get();
            Elements body = movies.select("div.lister-list");
            //System.out.println(body);

            if(!body.isEmpty()) {

                //lister.item is the list with the movie entries.
                for (Element movie : body.select("div, .lister.item")) {

                    //scans for the movie poster and saves them as a link. They are found under lister-item-image and
                    // marked with a 'src' tag ,which specifies the location of an external resource.


                    String img = movie.select("img, .loadlate").attr("src");
                    //System.out.println(img);

                    String title = movie.select("a, lister-item-header").text().strip();
                    System.out.println(title);



                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }




    }




}
