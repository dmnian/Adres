import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class AdressModelList {
    private ArrayList<AdressModel> adressModelList;
    private Queue<Coordinates> aQueue = new LinkedList<Coordinates>();

    public AdressModelList() {
        this.adressModelList = new ArrayList<AdressModel>();
    }

    public void getImport(String name) {


        double lat, lon;

        try {
            Scanner sc = new Scanner(new File(name));
            while (sc.hasNext()) {
                StringTokenizer st = new StringTokenizer(sc.nextLine(), ";");

                lat = Double.parseDouble(st.nextElement().toString());
                lon = Double.parseDouble(st.nextElement().toString());

//                aQueue.add(new Coordinates(lat, lon));
                aQueue.add(new Coordinates(lat, lon));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void writeToDatabase(DatabaseConn db) {
        for (AdressModel adressModel : adressModelList) {
//            if (adressModel != null)
//            System.out.println(db.insertAdres(adressModel.lat, adressModel.lon, adressModel.address.country, adressModel.address.city, adressModel.address.road, adressModel.address.house_number));
            db.insertAdres(adressModel.lat, adressModel.lon, adressModel.address.country, adressModel.address.city, adressModel.address.road, adressModel.address.house_number);
        }
    }

    public Queue<Coordinates> getaQueue() {
        return aQueue;
    }

    public ArrayList<AdressModel> getAdressModelList() {
        return adressModelList;
    }

}
