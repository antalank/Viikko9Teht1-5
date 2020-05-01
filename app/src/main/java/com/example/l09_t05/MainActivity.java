package com.example.l09_t05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView sijainti;
    TextView aukioloaika;
    TextView paivat;
    TextView otsikkoSmartPost;
    TextView otsikkoMaa;
    TextView otsikkoAika;
    TextView otsikkoPaiva;
    TextView otsikko;
    TextView nouto;
    EditText aikaAlku;
    EditText aikaLoppu;
    EditText paivaAlku;
    EditText paivaLoppu;

    int valinta;
    int kierros = 0;

    ArrayList<Post> postiLista = new ArrayList();
    ArrayList<Smart> smartList = new ArrayList();
    ArrayList<Nouto> viroNouto = new ArrayList();

    List<String> ma_list = Arrays.asList("ma", "ma-ti", "ma-ke", "ma-to", "ma-pe", "ma-la", "ma-su");
    List<String> ti_list = Arrays.asList("ti", "ma-ti", "ma-ke", "ma-to", "ma-pe", "ma-la", "ma-su", "ti-ke", "ti-to", "ti-pe", "ti-la", "ti-su");
    List<String> ke_list = Arrays.asList("ke", "ma-ke", "ma-to", "ma-pe", "ma-la", "ma-su", "ti-ke", "ti-to", "ti-pe", "ti-la", "ti-su", "ke-to", "ke-pe", "ke-la", "ke-su");
    List<String> to_list = Arrays.asList("to", "ma-to", "ma-pe", "ma-la", "ma-su", "to-pe", "to-la", "to-su", "ti-to", "ti-pe", "ti-la", "ti-su", "ke-to", "ke-pe", "ke-la", "ke-su");
    List<String> pe_list = Arrays.asList("pe", "ma-pe", "ma-la", "ma-su", "pe-la", "pe-su", "ti-pe", "ti-la", "ti-su", "ke-pe", "ke-la", "ke-su", "to-pe", "to-la", "to-su");
    List<String> la_list = Arrays.asList("la", "ma-la", "ma-su", "ti-la", "ti-su", "ke-la", "ke-su", "to-la", "to-su", "pe-la", "pe-su", "la-su");
    List<String> su_list = Arrays.asList("su", "ma-su", "ti-su", "ke-su", "to-su", "pe-su", "la-su");
    List<String> mati_list = Arrays.asList("ma-ti", "ma-ke", "ma-to", "ma-pe", "ma-la", "ma-su");
    List<String> make_list = Arrays.asList("ma-ke", "ma-to", "ma-pe", "ma-la", "ma-su");
    List<String> mato_list = Arrays.asList("ma-to", "ma-pe", "ma-la", "ma-su");
    List<String> mape_list = Arrays.asList("ma-pe", "ma-la", "ma-su");
    List<String> mala_list = Arrays.asList("ma-la", "ma-su");
    List<String> masu_list = Arrays.asList("ma-su");
    List<String> tike_list = Arrays.asList("ti-ke", "ti-to", "ti-pe", "ti-la", "ti-su", "ma-ke", "ma-to", "ma-pe", "ma-la", "ma-su");
    List<String> tito_list = Arrays.asList("ti-to", "ti-pe", "ti-la", "ti-su", "ma-to", "ma-pe", "ma-la", "ma-su");
    List<String> tipe_list = Arrays.asList("ti-pe", "ti-la", "ti-su", "ma-pe", "ma-la", "ma-su");
    List<String> tila_list = Arrays.asList("ti-la", "ti-su", "ma-la", "ma-su");
    List<String> tisu_list = Arrays.asList("ti-su", "ma-su");
    List<String> keto_list = Arrays.asList("ke-to", "ke-pe", "ke-la", "ke-su", "ma-to", "ma-pe", "ma-la", "ma-su");
    List<String> kepe_list = Arrays.asList("ke-pe", "ke-la", "ke-su", "ma-pe", "ma-la", "ma-su", "ti-pe", "ti-la", "ti-su");
    List<String> kela_list = Arrays.asList("ke-la", "ke-su", "ma-la", "ti-la", "ma-su", "ti-su");
    List<String> kesu_list = Arrays.asList("ke-su", "ti-su", "ma-su");
    List<String> tope_list = Arrays.asList("to-pe", "to-la", "to-su", "ma-pe", "ma-la", "ma-su", "ti-pe", "ti-la", "ti-su", "ke-pe", "ke-la", "ke-su");
    List<String> tola_list = Arrays.asList("to-la", "to-su", "ma-la", "ma-su", "ti-la", "ke-la", "to-la", "pe-la");
    List<String> tosu_list = Arrays.asList("to-su", "ma-su", "ti-su", "ke-su");
    List<String> pela_list = Arrays.asList("pe-la", "pe-su", "ma-la", "ma-su", "ti-la", "ke-la", "to-la");
    List<String> pesu_list= Arrays.asList("pe-su", "ma-su", "ti-su", "ke-su", "to-su");
    List<String> lasu_list = Arrays.asList("la-su", "ma-su", "ti-su", "ke-su", "to-su", "pe-su");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sijainti = (TextView) findViewById(R.id.sijainti);
        sijainti.setText("Sijainti näkyy tässä");
        aukioloaika = (TextView) findViewById(R.id.aukioloaika);
        aukioloaika.setText("Aukioloajat näkyvät tässä");
        paivat = (TextView) findViewById(R.id.paivat);
        paivat.setText("Viron noutoajan alku näkyv tässä");
        otsikkoSmartPost = (TextView) findViewById(R.id.otsikkoSmartPost);
        otsikkoSmartPost.setText("Valitse SmartPost");
        otsikkoMaa = (TextView) findViewById(R.id.otsikkoMaa);
        otsikkoMaa.setText("Valitse maa");
        otsikkoAika = (TextView) findViewById(R.id.otsikkoAika);
        otsikkoAika.setText("Syötä kellonajat");
        otsikkoPaiva = (TextView) findViewById(R.id.otsikkoPaiva);
        otsikkoPaiva.setText("Syötä viikonpäivät");
        otsikko = (TextView) findViewById(R.id.otsikko);
        otsikko.setText("Hae SmartPost tietoja tällä sovelluksella");
        nouto = (TextView) findViewById(R.id.nouto);
        nouto.setText("Noutopäivä (Viro)?");

        paivaAlku = (EditText) findViewById(R.id.paivaAlku);
        paivaAlku.setText("");
        paivaLoppu = (EditText) findViewById(R.id.paivaLoppu);
        paivaLoppu.setText("");
        aikaAlku = (EditText) findViewById(R.id.aikaAlku);
        aikaAlku.setText("");
        aikaLoppu = (EditText) findViewById(R.id.aikaLoppu);
        aikaLoppu.setText("");

        readXML();
        //Spinner 1: maat
        Spinner spinner = findViewById(R.id.valitseMaa);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.maat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new LongRunningTask().execute();
    }
    private class LongRunningTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            //Viron noutoaikatiedot
            try {
                DocumentBuilder builder1 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                String urlString1 = "http://iseteenindus.smartpost.ee/api/?request=courier&type=express";
                Document doc1 = builder1.parse(urlString1);
                doc1.getDocumentElement().normalize();
                System.out.println("Root element: " + doc1.getDocumentElement().getNodeName());
                NodeList nList1 = doc1.getDocumentElement().getElementsByTagName("item");

                for (int i = 0; i < nList1.getLength(); i++) {
                    Node node1 = nList1.item(i);
                    System.out.println("Element is this: " + node1.getNodeName());
                    if (node1.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node1;

                        int a = Integer.parseInt(element.getElementsByTagName("place_id").item(0).getTextContent());
                        int b = Integer.parseInt(element.getElementsByTagName("day").item(0).getTextContent());
                        String c = element.getElementsByTagName("express_out").item(0).getTextContent();

                        //Lisätään listaan
                        viroNouto.add(new Nouto(a, b, c));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } finally {
                System.out.println("#################LOPPU#####################");
            }
            //Suomen tiedot
            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                String urlString = "http://iseteenindus.smartpost.ee/api/?request=destinations&country=FI&type=APT";
                Document doc = builder.parse(urlString);
                doc.getDocumentElement().normalize();
                System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

                NodeList nList = doc.getDocumentElement().getElementsByTagName("item");

                for (int i = 0; i < nList.getLength(); i++) {
                    Node node = nList.item(i);
                    System.out.println("Element is this: " + node.getNodeName());

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        int a = Integer.parseInt(element.getElementsByTagName("place_id").item(0).getTextContent());
                        String b = element.getElementsByTagName("name").item(0).getTextContent();
                        String hajotus = "[,]";
                        String[] bb = b.split(hajotus);
                        String bbb = bb[1];
                        String c = element.getElementsByTagName("city").item(0).getTextContent();
                        String d = element.getElementsByTagName("address").item(0).getTextContent();
                        String e = element.getElementsByTagName("country").item(0).getTextContent();
                        int f = Integer.parseInt(element.getElementsByTagName("postalcode").item(0).getTextContent());
                        int g = Integer.parseInt(element.getElementsByTagName("routingcode").item(0).getTextContent());
                        String h = element.getElementsByTagName("availability").item(0).getTextContent();
                        String k = element.getElementsByTagName("description").item(0).getTextContent();
                        //Lisätään listaan
                        if (h.equals("24h")) {
                            h = "ma-su 0.00 - 24.00";
                            postiLista.add(new Post(a, bbb, c, d, e, f, g, h, k));
                        }
                        else if (h.equals("ma-to 7.00 - 21.00, pe-su 24h")) {
                            h = "ma-to 7.00 - 21.00, pe-su 0.00 - 24.00";
                            postiLista.add(new Post(a, bbb, c, d, e, f, g, h, k));
                        }
                        else if (h.equals("ma-pe 7.30 - 21.00, la 7.30 - 21.00 ja 8.00 - 22.00, su 9.00 - 21.00")) {
                            h = "ma-pe 7.30 - 21.00, la 7.30 - 22.00, su 9.00 - 21.00";
                            postiLista.add(new Post(a, bbb, c, d, e, f, g, h, k));
                        }
                        else {
                            postiLista.add(new Post(a, bbb, c, d, e, f, g, h, k));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } finally {
                System.out.println("#################LOPPU#####################");
            }
            //Viron tiedot
            try {
                DocumentBuilder builder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                String urlString2 = "http://iseteenindus.smartpost.ee/api/?request=destinations&country=EE&type=APT";
                Document doc2 = builder2.parse(urlString2);
                doc2.getDocumentElement().normalize();
                System.out.println("Root element: " + doc2.getDocumentElement().getNodeName());

                NodeList nList2 = doc2.getDocumentElement().getElementsByTagName("item");

                for (int i = 0; i < nList2.getLength(); i++) {
                    Node node2 = nList2.item(i);
                    System.out.println("Element is this: " + node2.getNodeName());

                    if (node2.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node2;

                        int a = Integer.parseInt(element2.getElementsByTagName("place_id").item(0).getTextContent());
                        String b = element2.getElementsByTagName("name").item(0).getTextContent();
                        String c = element2.getElementsByTagName("city").item(0).getTextContent();
                        String d = element2.getElementsByTagName("address").item(0).getTextContent();
                        String e = element2.getElementsByTagName("country").item(0).getTextContent();
                        int f = Integer.parseInt(element2.getElementsByTagName("postalcode").item(0).getTextContent());
                        int g = 0;
                        if (element2.getElementsByTagName("routingcode").item(0).getTextContent().length() > 0) {
                            g = Integer.parseInt(element2.getElementsByTagName("routingcode").item(0).getTextContent());
                        }

                        //Parsinta aukioloajoille
                        String h = element2.getElementsByTagName("availability").item(0).getTextContent();
                        String tulos = null;
                        String tulos2 = null;
                        String lopullinen = null;
                        switch (h) {
                            case "E-R 8:00-17:00 L - P -":
                                lopullinen = "ma-pe 8.00 - 17.00";
                                System.out.println(lopullinen);
                                break;
                            case "E-R 8:00-22:00 L-P 8:00-22:00":
                                lopullinen = "ma-pe 8.00 - 22.00, la-su 8.00 - 22.00";
                                break;
                            case "E-P kell 9:00-23:00":
                                lopullinen = "ma-su 9.00 - 23.00";
                                break;
                            case "E-P kell 9:00-20:00":
                                lopullinen = "ma-su 9.00 - 20.00";
                                break;
                            case "E-P kell 09:00-21:00":
                                lopullinen = "ma-su 9.00 - 21.00";
                                break;
                            case "E-P kell 08:00-22:00":
                                lopullinen = "ma-su 8.00 - 22.00";
                                break;
                            case "E-L 10:00-20:00 P 11:00-18:00":
                                lopullinen = "ma-la 10.00 - 20.00, su 11.00 - 18.00";
                                break;
                            case "E-N, P 8.00-22.00, R-L 8.00-22.00":
                                lopullinen = "ma-su 8.00 - 22.00";
                                break;
                            case "E-P 10:00 21:00":
                                lopullinen = "ma-su 10.00 - 21.00";
                                break;
                            case "E-P 9.00–20:00":
                                lopullinen = "ma-su 9.00 - 20.00";
                                break;
                            case "E-P 08:00–22:00":
                                lopullinen = "ma-su 08.00 – 22.00";
                                break;
                            case "E-P 24h":
                                lopullinen = "ma-su 0.00 - 24.00";
                                break;

                            default:
                                String hajotus = "[,]";
                                String hajotus2 = "[;]";
                                String[] separated1 = h.split(hajotus);
                                System.out.println(h);
                                String[] separated2 = null;
                                if (separated1.length < 2) {
                                    String alku = separated1[0];
                                    separated2 = alku.split(hajotus2);
                                } else {
                                    separated2 = separated1;
                                }
                                int koko = separated2.length;
                                String separated3 = separated2[0];
                                String[] aikataulu = separated3.split("\\s+");
                                String paivat = aikataulu[0];
                                String l_paiva = null,
                                        l_aika = null, a_aika, alku_aika, p_aika, paatto_aika;
                                switch (koko) {
                                    case 1:
                                        if (aikataulu.length == 4) {
                                            if (paivat.equals("E-R")) {
                                                l_paiva = "ma-pe";
                                            } else if (paivat.equals("E-L")) {
                                                l_paiva = "ma-la";
                                            } else if (paivat.equals("E-P")) {
                                                l_paiva = "ma-su";
                                            } a_aika = aikataulu[1];
                                            alku_aika = a_aika.replace(":", ".");
                                            p_aika = aikataulu[3];
                                            paatto_aika = p_aika.replace(":", ".");
                                            lopullinen = (l_paiva + " " + alku_aika + " - " + paatto_aika);

                                        } else if (aikataulu.length == 3) {

                                            if (paivat.equals("E-R")) {
                                                l_paiva = "ma-pe";
                                            } else if (paivat.equals("E-L")) {
                                                l_paiva = "ma-la";
                                            } else if (paivat.equals("E-P")) {
                                                l_paiva = "ma-su";
                                            }
                                            l_aika = aikataulu[2];
                                            String [] ai_aika = l_aika.split("-");
                                            a_aika = aikataulu[1];
                                            alku_aika = a_aika.replace(":", ".");
                                            p_aika = ai_aika[1];
                                            paatto_aika = p_aika.replace(":", ".");
                                            lopullinen = (l_paiva + " " + alku_aika + " - " + paatto_aika);
                                        } else {
                                            if (paivat.equals("E-R")) {
                                                l_paiva = "ma-pe";
                                            } else if (paivat.equals("E-L")) {
                                                l_paiva = "ma-la";
                                            } else if (paivat.equals("E-P")) {
                                                l_paiva = "ma-su";
                                            } if (aikataulu[1].equals("24h")) {
                                                tulos = (l_paiva + " 0.00 - 24.00");
                                            } else {
                                                l_aika = aikataulu[1];
                                                String [] al_aika = l_aika.split("-");
                                                alku_aika = al_aika[0];
                                                alku_aika = alku_aika.replace(":", ".");
                                                p_aika = al_aika[1];
                                                paatto_aika = p_aika.replace(":", ".");
                                                lopullinen = (l_paiva + " " + alku_aika + " - " + paatto_aika); }
                                        }
                                        break;
                                    case 2:
                                        if (aikataulu.length == 4) {
                                            if (paivat.equals("E-R")) {
                                                l_paiva = "ma-pe";
                                            } else if (paivat.equals("E-L")) {
                                                l_paiva = "ma-la";
                                            } else if (paivat.equals("E-P")) {
                                                l_paiva = "ma-su";
                                            } a_aika = aikataulu[1];
                                            alku_aika = a_aika.replace(":", ".");
                                            p_aika = aikataulu[3];
                                            paatto_aika = p_aika.replace(":", ".");
                                            tulos = (l_paiva + " " + alku_aika + " - " + paatto_aika);

                                        } else if (aikataulu.length == 3) {
                                            if (paivat.equals("E-R")) {
                                                l_paiva = "ma-pe";
                                            } else if (paivat.equals("E-L")) {
                                                l_paiva = "ma-la";
                                            } else if (paivat.equals("E-P")) {
                                                l_paiva = "ma-su";
                                            }
                                            l_aika = aikataulu[2];
                                            String [] ai_aika = l_aika.split("-");
                                            a_aika = aikataulu[1];
                                            alku_aika = a_aika.replace(":", ".");
                                            p_aika = ai_aika[1];
                                            paatto_aika = p_aika.replace(":", ".");
                                            tulos = (l_paiva + " " + alku_aika + " - " + paatto_aika);
                                        } else {
                                            if (paivat.equals("E-R")) {
                                                l_paiva = "ma-pe";
                                            } else if (paivat.equals("E-L")) {
                                                l_paiva = "ma-la";
                                            } else if (paivat.equals("E-P")) {
                                                l_paiva = "ma-su";
                                            } if (aikataulu[1].equals("24h")) {
                                                tulos = (l_paiva + " 0.00 - 24.00");
                                            } else {
                                                l_aika = aikataulu[1];
                                                String [] al_aika = l_aika.split("-");
                                                alku_aika = al_aika[0];
                                                alku_aika = alku_aika.replace(":", ".");
                                                p_aika = al_aika[1];
                                                paatto_aika = p_aika.replace(":", ".");
                                                tulos = (l_paiva + " " + alku_aika + " - " + paatto_aika); }
                                        }
                                        ///////////////////////////////////////////////////////////////
                                        String toinen = separated2[1];
                                        String[] aikataulutoinen = toinen.split("\\s+");
                                        String p = aikataulutoinen[1];
                                        if (p.equals("P")) {
                                            l_paiva = "su";
                                        }
                                        String aika = aikataulutoinen[2];
                                        String [] al_aika = aika.split("-");
                                        a_aika = al_aika[0];
                                        alku_aika = a_aika.replace(":", ".");
                                        p_aika = al_aika[1];
                                        paatto_aika = p_aika.replace(":", ".");
                                        tulos2 = (l_paiva + " " + alku_aika + " - " + paatto_aika);
                                        lopullinen = (tulos + ", " + tulos2);
                                        break;
                                    default:
                                        System.out.println("Ei tietoja");
                                        break;
                                } break;
                        }
                        System.out.println(lopullinen);
                        //Parsinta aukioloajoille
                        String k = element2.getElementsByTagName("description").item(0).getTextContent();

                        //Lisätään listaan
                        postiLista.add(new Post(a, b, c, d, e, f, g, lopullinen, k));
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } finally {
                System.out.println("#################LOPPU#####################");
            }
            return null;
        }
    }
    public void readXML() {

    }
    public void lisaaListaan(View v) {
        List<String> lista = new ArrayList<String>();
        for (String q : lasu_list) {
            System.out.println(lasu_list.get(2));
        }
        lista.clear();
        smartList.clear();
        int i = 0;
        String alku;
        for (Post p : postiLista) {
            //Parsinta alkaa
            alku = p.getAvailability();
            System.out.println(alku);
            String hajotus = "[,]";
            String[] separated1 = alku.split(hajotus);
            int koko = separated1.length;
            String paivaSyote = paivaAlku.getText().toString();
            //Parsitaan käyttäjän syöttämä kellonaika
            String a = aikaAlku.getText().toString();
            String b = aikaLoppu.getText().toString();
            String maat = p.getCountry();
            if (paivaSyote.equals("") && a.equals("") && b.equals("")) {
                switch (kierros) {
                    case 1:
                        if (maat.equals("EE")) {
                            lista.add(p.getName());
                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                            i = i + 1;
                        } break;
                    case 2:
                        if (maat.equals("FI")) {
                            lista.add(p.getName());
                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                            i = i + 1; }
                        break;
                    case 0:
                        lista.add(p.getName());
                        smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                        i = i + 1;
                        break;
                    default:
                        break;
                }
            } else {
            double aikaA = Double.parseDouble(a);
            double aikaL = Double.parseDouble(b);
            int laskuri = 0;
            int vas = 0;
            int k = 0;
            List<String> listaPaiville = new ArrayList<String>();
            List<Double> listaAAjoille = new ArrayList<Double>();
            List<Double> listaLAjoille = new ArrayList<Double>();
            String[] separated21 = separated1[0].split("\\s+");
            String[] separated22;
            String[] separated33;
            String[] separated44;
            String[] separated55;
            String paiva1, paiva2, paiva3, paiva4, paiva5, aaKello1, aaKello2, aaKello3, aaKello4, aaKello5, llKello1, llKello2, llKello3, llKello4, llKello5;
            double aKlo1, aKlo2, aKlo3, aKlo4, aKlo5, lKlo1, lKlo2, lKlo3, lKlo4, lKlo5;

            //Hae maa
            switch (koko) {
                case 1:
                    paiva1 = separated21[0];
                    aaKello1 = separated21[1];
                    llKello1 = separated21[3];
                    aKlo1 = Double.valueOf(aaKello1);
                    lKlo1 = Double.valueOf(llKello1);

                    listaPaiville.clear();
                    listaLAjoille.clear();
                    listaAAjoille.clear();
                    listaPaiville.add(paiva1);
                    listaAAjoille.add(aKlo1);
                    listaLAjoille.add(lKlo1);

                    if (paivaSyote.equals("ma")) {
                        for (String aa : ma_list) {
                            String tulos = ma_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti")) {
                        for (String aa : ti_list) {
                            String tulos = ti_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke")) {
                        for (String aa : ke_list) {
                            String tulos = ke_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to")) {
                        for (String aa : to_list) {
                            String tulos = to_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe")) {
                        for (String aa : pe_list) {
                            String tulos = pe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la")) {
                        for (String aa : la_list) {
                            String tulos = la_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("su")) {
                        for (String aa : su_list) {
                            String tulos = su_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ti")) {
                        for (String aa : mati_list) {
                            String tulos = mati_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ke")) {
                        for (String aa : make_list) {
                            String tulos = make_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-to")) {
                        for (String aa : mato_list) {
                            String tulos = mato_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-pe")) {
                        for (String aa : mape_list) {
                            String tulos = mape_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-la")) {
                        for (String aa : mala_list) {
                            String tulos = mala_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-su")) {
                        for (String aa : masu_list) {
                            String tulos = masu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-ke")) {
                        for (String aa : tike_list) {
                            String tulos = tike_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-to")) {
                        for (String aa : tito_list) {
                            String tulos = tito_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-pe")) {
                        for (String aa : tipe_list) {
                            String tulos = tipe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-la")) {
                        for (String aa : tila_list) {
                            String tulos = tila_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-su")) {
                        for (String aa : tisu_list) {
                            String tulos = tisu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-to")) {
                        for (String aa : keto_list) {
                            String tulos = keto_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-pe")) {
                        for (String aa : kepe_list) {
                            String tulos = kepe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-la")) {
                        for (String aa : kela_list) {
                            String tulos = kela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-su")) {
                        for (String aa : kesu_list) {
                            String tulos = kesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-pe")) {
                        for (String aa : tope_list) {
                            String tulos = tope_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-la")) {
                        for (String aa : tola_list) {
                            String tulos = tola_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-su")) {
                        for (String aa : tosu_list) {
                            String tulos = tosu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-la")) {
                        for (String aa : pela_list) {
                            String tulos = pela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-su")) {
                        for (String aa : pesu_list) {
                            String tulos = pesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la-su")) {
                        for (String aa : lasu_list) {
                            String tulos = lasu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1)) {
                                vas = 1;
                            }
                        }
                    }
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (vas > 0) {

                        double aika1 = listaAAjoille.get(laskuri);
                        if (aikaA >= aika1) {
                            double aika2 = listaLAjoille.get(laskuri);
                            if (aikaL <= aika2) {
                                switch (kierros) {
                                    case 1:
                                        if (maat.equals("EE")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1;
                                        } break;
                                    case 2:
                                        if (maat.equals("FI")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1; }
                                        break;
                                    case 0:
                                        lista.add(p.getName());
                                        smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                        i = i + 1;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }

                    break;
                case 2:
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    separated22 = separated1[1].split("\\s+");
                    paiva1 = separated21[0];
                    paiva2 = separated22[1];
                    aaKello1 = separated21[1];
                    if (separated22.length < 4) {
                        aaKello2 = "0.00";
                    } else { aaKello2 = separated22[2];  }
                    llKello1 = separated21[3];
                    if (separated22.length < 4) {
                        llKello2 = "24";
                    } else { llKello2 = separated22[4]; }

                    aKlo1 = Double.valueOf(aaKello1);
                    aKlo2 = Double.valueOf(aaKello2);
                    lKlo1 = Double.valueOf(llKello1);
                    lKlo2 = Double.valueOf(llKello2);


                    listaPaiville.clear();
                    listaLAjoille.clear();
                    listaAAjoille.clear();
                    listaPaiville.add(paiva1);
                    listaPaiville.add(paiva2);
                    listaAAjoille.add(aKlo1);
                    listaAAjoille.add(aKlo2);
                    listaLAjoille.add(lKlo1);
                    listaLAjoille.add(lKlo2);

                    if (paivaSyote.equals("ma")) {
                    for (String aa : ma_list) {
                        String tulos = ma_list.get(k);
                        k = k + 1;
                        if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                            vas = 1;
                        }
                    }
                }
                    if (paivaSyote.equals("ti")) {
                        for (String aa : ti_list) {
                            String tulos = ti_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke")) {
                        for (String aa : ke_list) {
                            String tulos = ke_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to")) {
                        for (String aa : to_list) {
                            String tulos = to_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe")) {
                        for (String aa : pe_list) {
                            String tulos = pe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la")) {
                        for (String aa : la_list) {
                            String tulos = la_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("su")) {
                        for (String aa : su_list) {
                            String tulos = su_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ti")) {
                        for (String aa : mati_list) {
                            String tulos = mati_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ke")) {
                        for (String aa : make_list) {
                            String tulos = make_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-to")) {
                        for (String aa : mato_list) {
                            String tulos = mato_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-pe")) {
                        for (String aa : mape_list) {
                            String tulos = mape_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-la")) {
                        for (String aa : mala_list) {
                            String tulos = mala_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-su")) {
                        for (String aa : masu_list) {
                            String tulos = masu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-ke")) {
                        for (String aa : tike_list) {
                            String tulos = tike_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-to")) {
                        for (String aa : tito_list) {
                            String tulos = tito_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-pe")) {
                        for (String aa : tipe_list) {
                            String tulos = tipe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-la")) {
                        for (String aa : tila_list) {
                            String tulos = tila_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-su")) {
                        for (String aa : tisu_list) {
                            String tulos = tisu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-to")) {
                        for (String aa : keto_list) {
                            String tulos = keto_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-pe")) {
                        for (String aa : kepe_list) {
                            String tulos = kepe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-la")) {
                        for (String aa : kela_list) {
                            String tulos = kela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-su")) {
                        for (String aa : kesu_list) {
                            String tulos = kesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-pe")) {
                        for (String aa : tope_list) {
                            String tulos = tope_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-la")) {
                        for (String aa : tola_list) {
                            String tulos = tola_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-su")) {
                        for (String aa : tosu_list) {
                            String tulos = tosu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-la")) {
                        for (String aa : pela_list) {
                            String tulos = pela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-su")) {
                        for (String aa : pesu_list) {
                            String tulos = pesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la-su")) {
                        for (String aa : lasu_list) {
                            String tulos = lasu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2)) {
                                vas = 1;
                            }
                        }
                    }
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (vas > 0) {
                        if (paivaSyote.equals(paiva2)) {
                            laskuri = laskuri + 1;
                        }
                        double aika1 = listaAAjoille.get(laskuri);
                        if (aikaA >= aika1) {
                            double aika2 = listaLAjoille.get(laskuri);
                            if (aikaL <= aika2) {
                                switch (kierros) {
                                    case 1:
                                        if (maat.equals("EE")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1;
                                        } break;
                                    case 2:
                                        if (maat.equals("FI")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1; }
                                        break;
                                    case 0:
                                        lista.add(p.getName());
                                        smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                        i = i + 1;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    } break;
                case 3:
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    separated22 = separated1[1].split("\\s+");
                    separated33 = separated1[2].split("\\s+");
                    paiva1 = separated21[0];
                    paiva2 = separated22[1];
                    paiva3 = separated33[1];
                    aaKello1 = separated21[1];
                    if (separated22.length < 4) {
                        aaKello2 = "0.00";
                    } else { aaKello2 = separated22[2];  }
                    llKello1 = separated21[3];
                    if (separated22.length < 4) {
                        llKello2 = "24";
                    } else { llKello2 = separated22[4]; }

                    if (separated33.length < 4) {
                        aaKello3 = "0.00";
                    } else { aaKello3 = separated33[2];  }

                    if (separated33.length < 4) {
                        llKello3 = "24";
                    } else { llKello3 = separated33[4]; }


                    aKlo1 = Double.valueOf(aaKello1);
                    aKlo2 = Double.valueOf(aaKello2);
                    aKlo3 = Double.valueOf(aaKello3);
                    lKlo1 = Double.valueOf(llKello1);
                    lKlo2 = Double.valueOf(llKello2);
                    lKlo3 = Double.valueOf(llKello3);

                    listaPaiville.clear();
                    listaLAjoille.clear();
                    listaAAjoille.clear();
                    listaPaiville.add(paiva1);
                    listaPaiville.add(paiva2);
                    listaPaiville.add(paiva3);
                    listaAAjoille.add(aKlo1);
                    listaAAjoille.add(aKlo2);
                    listaAAjoille.add(aKlo3);
                    listaLAjoille.add(lKlo1);
                    listaLAjoille.add(lKlo2);
                    listaLAjoille.add(lKlo3);

                    if (paivaSyote.equals("ma")) {
                        for (String aa : ma_list) {
                            String tulos = ma_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti")) {
                        for (String aa : ti_list) {
                            String tulos = ti_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke")) {
                        for (String aa : ke_list) {
                            String tulos = ke_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to")) {
                        for (String aa : to_list) {
                            String tulos = to_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe")) {
                        for (String aa : pe_list) {
                            String tulos = pe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la")) {
                        for (String aa : la_list) {
                            String tulos = la_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("su")) {
                        for (String aa : su_list) {
                            String tulos = su_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ti")) {
                        for (String aa : mati_list) {
                            String tulos = mati_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ke")) {
                        for (String aa : make_list) {
                            String tulos = make_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-to")) {
                        for (String aa : mato_list) {
                            String tulos = mato_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-pe")) {
                        for (String aa : mape_list) {
                            String tulos = mape_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-la")) {
                        for (String aa : mala_list) {
                            String tulos = mala_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-su")) {
                        for (String aa : masu_list) {
                            String tulos = masu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-ke")) {
                        for (String aa : tike_list) {
                            String tulos = tike_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-to")) {
                        for (String aa : tito_list) {
                            String tulos = tito_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-pe")) {
                        for (String aa : tipe_list) {
                            String tulos = tipe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-la")) {
                        for (String aa : tila_list) {
                            String tulos = tila_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-su")) {
                        for (String aa : tisu_list) {
                            String tulos = tisu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-to")) {
                        for (String aa : keto_list) {
                            String tulos = keto_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-pe")) {
                        for (String aa : kepe_list) {
                            String tulos = kepe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-la")) {
                        for (String aa : kela_list) {
                            String tulos = kela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-su")) {
                        for (String aa : kesu_list) {
                            String tulos = kesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-pe")) {
                        for (String aa : tope_list) {
                            String tulos = tope_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-la")) {
                        for (String aa : tola_list) {
                            String tulos = tola_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-su")) {
                        for (String aa : tosu_list) {
                            String tulos = tosu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-la")) {
                        for (String aa : pela_list) {
                            String tulos = pela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-su")) {
                        for (String aa : pesu_list) {
                            String tulos = pesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la-su")) {
                        for (String aa : lasu_list) {
                            String tulos = lasu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3)) {
                                vas = 1;
                            }
                        }
                    }
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (vas > 0) {
                        if (paivaSyote.equals(paiva2)) {
                            laskuri = laskuri + 1;
                        }
                        if (paivaSyote.equals(paiva3)) {
                            laskuri = laskuri + 2;
                        }
                        double aika1 = listaAAjoille.get(laskuri);
                        if (aikaA >= aika1) {
                            double aika2 = listaLAjoille.get(laskuri);
                            if (aikaL <= aika2) {
                                switch (kierros) {
                                    case 1:
                                        if (maat.equals("EE")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1;
                                        } break;
                                    case 2:
                                        if (maat.equals("FI")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1; }
                                        break;
                                    case 0:
                                        lista.add(p.getName());
                                        smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                        i = i + 1;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    separated22 = separated1[1].split("\\s+");
                    separated33 = separated1[2].split("\\s+");
                    separated44 = separated1[3].split("\\s+");
                    paiva1 = separated21[0];
                    paiva2 = separated22[1];
                    paiva3 = separated33[1];
                    paiva4 = separated44[1];
                    aaKello1 = separated21[1];
                    if (separated22.length < 4) {
                        aaKello2 = "0.00";
                    } else { aaKello2 = separated22[2];  }
                    llKello1 = separated21[3];
                    if (separated22.length < 4) {
                        llKello2 = "24";
                    } else { llKello2 = separated22[4]; }

                    if (separated33.length < 4) {
                        aaKello3 = "0.00";
                    } else { aaKello3 = separated33[2];  }

                    if (separated33.length < 4) {
                        llKello3 = "24";
                    } else { llKello3 = separated33[4]; }

                    if (separated44.length < 4) {
                        aaKello4 = "0.00";
                    } else { aaKello4 = separated44[2];  }

                    if (separated44.length < 4) {
                        llKello4 = "24";
                    } else { llKello4 = separated44[4]; }


                    aKlo1 = Double.valueOf(aaKello1);
                    aKlo2 = Double.valueOf(aaKello2);
                    aKlo3 = Double.valueOf(aaKello3);
                    aKlo4 = Double.valueOf(aaKello4);
                    lKlo1 = Double.valueOf(llKello1);
                    lKlo2 = Double.valueOf(llKello2);
                    lKlo3 = Double.valueOf(llKello3);
                    lKlo4 = Double.valueOf(llKello4);

                    listaPaiville.clear();
                    listaLAjoille.clear();
                    listaAAjoille.clear();
                    listaPaiville.add(paiva1);
                    listaPaiville.add(paiva2);
                    listaPaiville.add(paiva3);
                    listaPaiville.add(paiva4);
                    listaAAjoille.add(aKlo1);
                    listaAAjoille.add(aKlo2);
                    listaAAjoille.add(aKlo3);
                    listaAAjoille.add(aKlo4);
                    listaLAjoille.add(lKlo1);
                    listaLAjoille.add(lKlo2);
                    listaLAjoille.add(lKlo3);
                    listaLAjoille.add(lKlo4);

                    if (paivaSyote.equals("ma")) {
                        for (String aa : ma_list) {
                            String tulos = ma_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti")) {
                        for (String aa : ti_list) {
                            String tulos = ti_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke")) {
                        for (String aa : ke_list) {
                            String tulos = ke_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to")) {
                        for (String aa : to_list) {
                            String tulos = to_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe")) {
                        for (String aa : pe_list) {
                            String tulos = pe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la")) {
                        for (String aa : la_list) {
                            String tulos = la_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("su")) {
                        for (String aa : su_list) {
                            String tulos = su_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ti")) {
                        for (String aa : mati_list) {
                            String tulos = mati_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ke")) {
                        for (String aa : make_list) {
                            String tulos = make_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-to")) {
                        for (String aa : mato_list) {
                            String tulos = mato_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-pe")) {
                        for (String aa : mape_list) {
                            String tulos = mape_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-la")) {
                        for (String aa : mala_list) {
                            String tulos = mala_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-su")) {
                        for (String aa : masu_list) {
                            String tulos = masu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-ke")) {
                        for (String aa : tike_list) {
                            String tulos = tike_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-to")) {
                        for (String aa : tito_list) {
                            String tulos = tito_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-pe")) {
                        for (String aa : tipe_list) {
                            String tulos = tipe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-la")) {
                        for (String aa : tila_list) {
                            String tulos = tila_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-su")) {
                        for (String aa : tisu_list) {
                            String tulos = tisu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-to")) {
                        for (String aa : keto_list) {
                            String tulos = keto_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-pe")) {
                        for (String aa : kepe_list) {
                            String tulos = kepe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-la")) {
                        for (String aa : kela_list) {
                            String tulos = kela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-su")) {
                        for (String aa : kesu_list) {
                            String tulos = kesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-pe")) {
                        for (String aa : tope_list) {
                            String tulos = tope_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-la")) {
                        for (String aa : tola_list) {
                            String tulos = tola_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-su")) {
                        for (String aa : tosu_list) {
                            String tulos = tosu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-la")) {
                        for (String aa : pela_list) {
                            String tulos = pela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-su")) {
                        for (String aa : pesu_list) {
                            String tulos = pesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la-su")) {
                        for (String aa : lasu_list) {
                            String tulos = lasu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4)) {
                                vas = 1;
                            }
                        }
                    }
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (vas > 0) {
                        if (paivaSyote.equals(paiva2)) {
                            laskuri = laskuri + 1;
                        }
                        if (paivaSyote.equals(paiva3)) {
                            laskuri = laskuri + 2;
                        }
                        if (paivaSyote.equals(paiva4)) {
                            laskuri = laskuri + 3;
                        }
                        double aika1 = listaAAjoille.get(laskuri);
                        if (aikaA >= aika1) {
                            double aika2 = listaLAjoille.get(laskuri);
                            if (aikaL <= aika2) {
                                switch (kierros) {
                                    case 1:
                                        if (maat.equals("EE")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1;
                                        } break;
                                    case 2:
                                        if (maat.equals("FI")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1; }
                                        break;
                                    case 0:
                                        lista.add(p.getName());
                                        smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                        i = i + 1;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                    break;

                case 5:
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    separated22 = separated1[1].split("\\s+");
                    separated33 = separated1[2].split("\\s+");
                    separated44 = separated1[3].split("\\s+");
                    separated55 = separated1[4].split("\\s+");
                    paiva1 = separated21[0];
                    paiva2 = separated22[1];
                    paiva3 = separated33[1];
                    paiva4 = separated44[1];
                    paiva5 = separated55[1];
                    aaKello1 = separated21[1];
                    if (separated22.length < 4) {
                        aaKello2 = "0.00";
                    } else { aaKello2 = separated22[2];  }
                    llKello1 = separated21[3];
                    if (separated22.length < 4) {
                        llKello2 = "24";
                    } else { llKello2 = separated22[4]; }

                    if (separated33.length < 4) {
                        aaKello3 = "0.00";
                    } else { aaKello3 = separated33[2];  }

                    if (separated33.length < 4) {
                        llKello3 = "24";
                    } else { llKello3 = separated33[4]; }

                    if (separated44.length < 4) {
                        aaKello4 = "0.00";
                    } else { aaKello4 = separated44[2];  }

                    if (separated44.length < 4) {
                        llKello4 = "24";
                    } else { llKello4 = separated44[4]; }

                    if (separated55.length < 4) {
                        aaKello5 = "0.00";
                    } else { aaKello5 = separated55[2];  }

                    if (separated55.length < 4) {
                        llKello5 = "24";
                    } else { llKello5 = separated55[4]; }


                    aKlo1 = Double.valueOf(aaKello1);
                    aKlo2 = Double.valueOf(aaKello2);
                    aKlo3 = Double.valueOf(aaKello3);
                    aKlo4 = Double.valueOf(aaKello4);
                    aKlo5 = Double.valueOf(aaKello5);
                    lKlo1 = Double.valueOf(llKello1);
                    lKlo2 = Double.valueOf(llKello2);
                    lKlo3 = Double.valueOf(llKello3);
                    lKlo4 = Double.valueOf(llKello4);
                    lKlo5 = Double.valueOf(llKello5);

                    listaPaiville.clear();
                    listaLAjoille.clear();
                    listaAAjoille.clear();
                    listaPaiville.add(paiva1);
                    listaPaiville.add(paiva2);
                    listaPaiville.add(paiva3);
                    listaPaiville.add(paiva4);
                    listaPaiville.add(paiva5);
                    listaAAjoille.add(aKlo1);
                    listaAAjoille.add(aKlo2);
                    listaAAjoille.add(aKlo3);
                    listaAAjoille.add(aKlo4);
                    listaAAjoille.add(aKlo5);
                    listaLAjoille.add(lKlo1);
                    listaLAjoille.add(lKlo2);
                    listaLAjoille.add(lKlo3);
                    listaLAjoille.add(lKlo4);
                    listaLAjoille.add(lKlo5);

                    if (paivaSyote.equals("ma")) {
                        for (String aa : ma_list) {
                            String tulos = ma_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti")) {
                        for (String aa : ti_list) {
                            String tulos = ti_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke")) {
                        for (String aa : ke_list) {
                            String tulos = ke_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to")) {
                        for (String aa : to_list) {
                            String tulos = to_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe")) {
                        for (String aa : pe_list) {
                            String tulos = pe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la")) {
                        for (String aa : la_list) {
                            String tulos = la_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("su")) {
                        for (String aa : su_list) {
                            String tulos = su_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ti")) {
                        for (String aa : mati_list) {
                            String tulos = mati_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-ke")) {
                        for (String aa : make_list) {
                            String tulos = make_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-to")) {
                        for (String aa : mato_list) {
                            String tulos = mato_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-pe")) {
                        for (String aa : mape_list) {
                            String tulos = mape_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-la")) {
                        for (String aa : mala_list) {
                            String tulos = mala_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ma-su")) {
                        for (String aa : masu_list) {
                            String tulos = masu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-ke")) {
                        for (String aa : tike_list) {
                            String tulos = tike_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-to")) {
                        for (String aa : tito_list) {
                            String tulos = tito_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-pe")) {
                        for (String aa : tipe_list) {
                            String tulos = tipe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-la")) {
                        for (String aa : tila_list) {
                            String tulos = tila_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ti-su")) {
                        for (String aa : tisu_list) {
                            String tulos = tisu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-to")) {
                        for (String aa : keto_list) {
                            String tulos = keto_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-pe")) {
                        for (String aa : kepe_list) {
                            String tulos = kepe_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-la")) {
                        for (String aa : kela_list) {
                            String tulos = kela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("ke-su")) {
                        for (String aa : kesu_list) {
                            String tulos = kesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-pe")) {
                        for (String aa : tope_list) {
                            String tulos = tope_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-la")) {
                        for (String aa : tola_list) {
                            String tulos = tola_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("to-su")) {
                        for (String aa : tosu_list) {
                            String tulos = tosu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-la")) {
                        for (String aa : pela_list) {
                            String tulos = pela_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("pe-su")) {
                        for (String aa : pesu_list) {
                            String tulos = pesu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    if (paivaSyote.equals("la-su")) {
                        for (String aa : lasu_list) {
                            String tulos = lasu_list.get(k);
                            k = k + 1;
                            if (tulos.equals(paiva1) || tulos.equals(paiva2) || tulos.equals(paiva3) || tulos.equals(paiva4) || tulos.equals(paiva5)) {
                                vas = 1;
                            }
                        }
                    }
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (vas > 0) {
                        if (paivaSyote.equals(paiva2)) {
                            laskuri = laskuri + 1;
                        }
                        if (paivaSyote.equals(paiva3)) {
                            laskuri = laskuri + 2;
                        }
                        if (paivaSyote.equals(paiva4)) {
                            laskuri = laskuri + 3;
                        }
                        if (paivaSyote.equals(paiva5)) {
                            laskuri = laskuri + 4;
                        }
                        double aika1 = listaAAjoille.get(laskuri);
                        if (aikaA >= aika1) {
                            double aika2 = listaLAjoille.get(laskuri);
                            if (aikaL <= aika2) {
                                switch (kierros) {
                                    case 1:
                                        if (maat.equals("EE")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1;
                                        } break;
                                    case 2:
                                        if (maat.equals("FI")) {
                                            lista.add(p.getName());
                                            smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                            i = i + 1; }
                                        break;
                                    case 0:
                                        lista.add(p.getName());
                                        smartList.add(new Smart(i, p.getPlace_id(), p.getName(), p.getCity(), p.getAddress(), p.getCountry(), p.getPostalcode(), p.getAvailability()));
                                        i = i + 1;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        } }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.valitseSmartPost);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);
    }
    public void lueListasta(View v) {
        String noutoAika = "";
        for (Smart s : smartList) {
            if (s.getCountry().equals("FI")) {
                if (valinta == s.getId()) {
                    aukioloaika.setText(s.getAvailability());
                    sijainti.setText(s.getCity() + ": " + s.getPostalcode() + ", " + s.getAddress() + ".");
                    paivat.setText("Noutoajat samat kuin aukioloajat");
                }
            } else if (s.getCountry().equals("EE")) {
                String a = paivaLoppu.getText().toString();
                if (valinta == s.getId()) {
                    aukioloaika.setText(s.getAvailability());
                    sijainti.setText(s.getCity() + ": " + s.getPostalcode() + ", " + s.getAddress() + ".");

                    for (Nouto n : viroNouto) {
                        if (a.equals("ma")) {
                            if (s.getPlace_id() == n.getPlace_id() && n.getDay() == 1) {
                                noutoAika = n.getExpress_out();
                                paivat.setText("Noutoaika: " + noutoAika);
                            }
                        } else if (a.equals("ti")) {
                            if (s.getPlace_id() == n.getPlace_id() && n.getDay() == 2) {
                                noutoAika = n.getExpress_out();
                                paivat.setText("Noutoaika: " + noutoAika);
                            }
                        } else if (a.equals("ke")) {
                            if (s.getPlace_id() == n.getPlace_id() && n.getDay() == 3) {
                                noutoAika = n.getExpress_out();
                                paivat.setText("Noutoaika: " + noutoAika);
                            }
                        } else if (a.equals("to")) {
                            if (s.getPlace_id() == n.getPlace_id() && n.getDay() == 4) {
                                noutoAika = n.getExpress_out();
                                paivat.setText("Noutoaika: " + noutoAika);
                            }
                        } else if (a.equals("pe")) {
                            if (s.getPlace_id() == n.getPlace_id() && n.getDay() == 5) {
                                noutoAika = n.getExpress_out();
                                paivat.setText("Noutoaika: " + noutoAika);
                            }
                        } else if (a.equals("la")) {
                            if (s.getPlace_id() == n.getPlace_id() && n.getDay() == 6) {
                                noutoAika = n.getExpress_out();
                                paivat.setText("Noutoaika: " + noutoAika);
                            }
                        } else if (a.equals("su")) {
                            if (s.getPlace_id() == n.getPlace_id() && n.getDay() == 7) {
                                noutoAika = n.getExpress_out();
                                paivat.setText("Noutoaika: " + noutoAika);
                            }
                        }
                    }
                }
            }
        }
    }
    //spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.valitseSmartPost){
            valinta = parent.getSelectedItemPosition();
        }
        else if (parent.getId() == R.id.valitseMaa) {
            kierros = parent.getSelectedItemPosition();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}