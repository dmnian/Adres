import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdressModel {
    public double lat;
    public double lon;

    public Address address;

    class Address {

        public String house_number;
        public String city;
        public String country;
        public String road;

    }

    public static AdressModel getAdressModel(String input) {
        Gson gson = new GsonBuilder().create();
        AdressModel adressModel = gson.fromJson(input, AdressModel.class);
        return adressModel;
    }
}



