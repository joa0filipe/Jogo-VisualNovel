package Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

import Model.EstadoDeJogo;
public class JogoRepository {

    private Gson gson;

    public JogoRepository(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvarEstado(EstadoDeJogo state){
        try (FileWriter writer = new FileWriter("EstadoDeJogo.json")) {
            gson.toJson(state, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
