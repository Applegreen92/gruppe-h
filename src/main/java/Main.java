
import com.model.*;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
       Crawler insertMovies = new Crawler();
       insertMovies.getMovies("action");
    }
}
