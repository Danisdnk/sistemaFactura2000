package dal;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Repository<T extends Identificable> {
    private final String DATA_DIRECTORY = "data/";
    private final String FILE_EXTENSION = ".json";
    private final String INDICE_FILE = "indice";

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
        if ( this.updateCache | this.cache == null ) {
            var indice = getIndice();

            var list = new ArrayList<T>();

            for (int i = 1; i < indice; i++) {

                var obj = this.leerArchivo(this.tipo, getPath(String.valueOf(i)));
                if ( obj != null ) {
                    list.add(obj);
                }
            }

            this.cache = list;
            this.updateCache = false;
        }

        return this.cache;
    }

    public T getByID(int id) {
        return this.leerArchivo(this.tipo, getPath(String.valueOf(id)));
    }

    public void crearTodos(List<T> list) {
        for (T obj:list) {
            insertar(obj);
        }
    }

    public int insertar(T obj) {
        var indice = getIndice();
        var path = getPath(String.valueOf(indice));

        obj.setID(indice);
        var next = indice + 1;
        escribirArchivo(getPath(INDICE_FILE), new Indice(next));
        escribirArchivo(path, obj);

        return indice;
    }

    public boolean updatear(T obj) {
        var indice = obj.getID();
        var path = getPath(String.valueOf(indice));
        return this.escribirArchivo(path, obj);
    }

    public boolean borrar(T obj) {
        return borrarConFileIo(obj);
    }

    public int getIndice() {
        crearIndice();
        var ind = this.leerArchivo(Indice.class, getPath(INDICE_FILE));
        assert ind != null;
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
        var Existe = Files.exists(( Path.of(path) )); //verificamos que existe el path

        if ( Existe ) {
            try {
                var fr = new FileReader(path);
                var reader = new JsonReader(new FileReader(path));

                R result = this.serializer.fromJson(reader, type);
                fr.close();
                reader.close();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
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

            fileWriter.close();
            setUpdateCache();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    private boolean borrarConFileIo(T obj) {
        var path = getPath(String.valueOf(obj.getID()));

        try {
            Files.delete(Path.of(path));
            updateCache= true;
            return true;
        }
        catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            System.err.println(x);
        }
        return false;

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
