package dal;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Repository<T> {
    private final String DATA_DIRECTORY = "data/";
    private final String FILE_EXTENSION = ".json";
    private final String INDICE_FILE = "indice";
    private final Type INDICE_TYPE = new TypeToken<Indice>() {}.getType();

    private Class<T> tipo;
    private String directorio;
    private Gson serializer;
    private List<T> cache;
    private boolean updateCache;

    public Repository(Class<T> type) {
        this.tipo = type;
        this.directorio = type.getSimpleName().toLowerCase(Locale.ROOT);

        this.serializer = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        crearDirectorio();
        crearIndice();
    }

    public List<T> getTodos() {
        if (this.updateCache | this.cache == null) {
            var indice = getIndice();

            var list = new ArrayList<T>();

            for (int i = 1; i < indice; i++) {
                var obj = this.leerArchivo(this.tipo, getPath(String.valueOf(i)));

                list.add(obj);
            }

            this.cache = list;
            this.updateCache = false;
        }

        return this.cache;
    }

    public void crearTodos(List<T> list) {
        for (T obj:list) {
            crear(obj);
        }
    }

    public boolean crear(T obj) {
        var indice = getIndice();
        var path = getPath(String.valueOf(indice));

        ((Identificable)obj).setID(indice);
        var next = indice + 1;
        escribirArchivo(getPath(INDICE_FILE), new Indice(next));
        return escribirArchivo(path, obj);
    }

    public boolean updatear(T obj) {
        var indice = ((Identificable)obj).getID();
        var path = getPath(String.valueOf(indice));
        return this.escribirArchivo(path, obj);
    }

    public boolean borrar(int id) {
        return borrarArchivo(id);
    }

    public int getIndice() {
        crearIndice();
        var ind = this.leerArchivo(Indice.class, getPath(INDICE_FILE));
        return ind.getIndice();
    }

    private void crearDirectorio(){
        File directory = new File(getDirectorioPath());
        if (!directory.exists()){
            directory.mkdirs();
        }
    }

    private void crearIndice(){
        File indice = new File(getPath(INDICE_FILE));
        if (!indice.exists()){
            escribirArchivo(getPath(INDICE_FILE), new Indice());
        }
    }

    private <R> R leerArchivo(Class<R> type, String path) {
        try {
            var fr = new FileReader(path);
            var reader = new JsonReader(new FileReader(path));
            R result = this.serializer.fromJson(reader, type);

            fr.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private <R> boolean escribirArchivo(String path, R obj) {
        try {
            var fileWriter = new FileWriter(path, false);
            var json = toJSON(obj);
            var bw = new BufferedWriter(fileWriter);
            bw.write(json);
            bw.close();
            setUpdateCache();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean borrarArchivo(int indice) {
        var path = getPath(String.valueOf(indice));
        var file = new File(path);

        var resultado = file.delete();

        if(resultado) {
            setUpdateCache();
        }

        return resultado;
    }

    private <R> String toJSON(R obj) {
        return this.serializer.toJson(obj);
    }

    private String getDirectorioPath() {
        return DATA_DIRECTORY.concat(this.directorio);
    }

    private String getPath(String file) {
        return getDirectorioPath().concat("/").concat(file).concat(FILE_EXTENSION);
    }

    private void setUpdateCache() {
        this.updateCache = true;
    }

    private class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        @Override
        public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
        }
    }
}
