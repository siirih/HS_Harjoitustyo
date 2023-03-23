package elainkysely;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.util.Arrays;
/** Kysely, joka antaa kayttajan vastausten perusteella kayttajalle lemmikki ehdotuksen.
 * @author Siiri Harinen
 * @author siirihar@student.uef.fi
 * @version 1.0 2023/03/22
 */
public class kysely extends Application{
    /**VBox, johon tehdaan muutoksia koko ohjelman ajan*/
    private VBox vboksi = new VBox(10);
    /** Teksteissa kaytettava fontti*/
    private Font fontti = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
    /** Taulukko, johon kayttajan valinnat sijoitetaan.*/
    private String[] valinnat = new String[5];
    /** Taulukko, johon luetaan tiedostosta saatu taulukko.*/
    private String[] finalvalinnat = new String[5];
    /** Laskee kysymysten maaran*/
    private int kohta=0;
    /** Sivunumero*/
    private int sivu=1;
    /** Olioluokka asettaa kayttajan nimen, ian ja nimiehdotuksen lemmikille*/
    kayttaja k1 = new kayttaja("User",0,"Fenn");

    /** Tekee ikkunan ja asettaa sen elementit, muokkaa vboksia aloituskohtauksen mukaiseksi.
     * @param stage Stage */
    @Override
    public void start(Stage stage) {
        //Starting scene
        vboksi.setAlignment(Pos.CENTER);
        Text startTitle = new Text("What pet suits you according to this questionnaire");
        startTitle.setFont(fontti);
        HBox hbnimi = new HBox(1);
        Label lnimi = new Label("Name: ");
        TextField tfnimi = new TextField(k1.getNimi());
        Label lika = new Label("Age: ");
        TextField tfika = new TextField(Integer.toString(k1.getIka()));
        hbnimi.getChildren().addAll(lnimi,tfnimi, lika, tfika);
        hbnimi.setAlignment(Pos.CENTER);
        HBox hbpet = new HBox(1);
        Label lpetname = new Label("Suggest a name for the pet: ");
        TextField tfpet = new TextField(k1.getPetname());
        hbpet.getChildren().addAll(lpetname,tfpet);
        hbpet.setAlignment(Pos.CENTER);
        Image startImage = new Image("pexels-calin-razvan-12658225.jpg");
        ImageView startImageView = new ImageView(startImage);
        startImageView.setFitWidth(340);
        startImageView.setFitHeight(300);
        Button start = new Button("Start");
        Text sivu1 = new Text(sivu + " / 7");
        sivu++;
        vboksi.getChildren().add(startTitle);
        vboksi.getChildren().add(hbnimi);
        vboksi.getChildren().add(hbpet);
        vboksi.getChildren().add(startImageView);
        vboksi.getChildren().add(start);
        vboksi.getChildren().add(sivu1);
        //Starting scene start-button
        start.setOnAction(e -> {
            k1.setNimi(tfnimi.getText());
            k1.setIka(Integer.parseInt(tfika.getText()));
            k1.setPetname(tfpet.getText());
            vboksi.getChildren().remove(startTitle);
            vboksi.getChildren().remove(hbnimi);
            vboksi.getChildren().remove(hbpet);
            vboksi.getChildren().remove(startImageView);
            vboksi.getChildren().remove(start);
            vboksi.getChildren().remove(sivu1);
            getQ1();
        });
        //SCENE
        Scene scene = new Scene(vboksi, 450, 480);
        stage.setTitle("Questionnaire!");
        stage.setScene(scene);
        stage.show();
    }
    /** Tekee ensimmaisen kysymyksen, muokkaa vboksia.
     */
    public void getQ1() {
        //Question 1
        Text q1Title = new Text("Which environment would you like to live in?");
        q1Title.setFont(fontti);
        HBox q1imagehbox = new HBox(10);
        q1imagehbox.setAlignment(Pos.CENTER);
        Image q1p1 = new Image("pexels-bart-walus-10992816.jpg");
        Image q1p2 = new Image("pexels-janith-thenuwara-6658767.jpg");
        ImageView ivq1p1 = new ImageView(q1p1);
        ImageView ivq1p2 = new ImageView(q1p2);
        ivq1p1.setFitHeight(200);
        ivq1p1.setFitWidth(200);
        ivq1p2.setFitHeight(200);
        ivq1p2.setFitWidth(200);
        q1imagehbox.getChildren().addAll(ivq1p1, ivq1p2);
        HBox pick1 = new HBox(150);
        pick1.setAlignment(Pos.CENTER);
        Button Bcity = new Button("City");
        Button Bcountry = new Button("Country");
        Bcity.setPrefWidth(70);
        Bcountry.setPrefWidth(70);
        pick1.getChildren().addAll(Bcity, Bcountry);
        Text sivu2 = new Text(sivu + " / 7");
        sivu++;
        vboksi.getChildren().add(q1Title);
        vboksi.getChildren().add(q1imagehbox);
        vboksi.getChildren().add(pick1);
        vboksi.getChildren().add(sivu2);

        //Question 1 City (A)
        Bcity.setOnAction(e -> {
            vboksi.getChildren().remove(q1Title);
            vboksi.getChildren().remove(q1imagehbox);
            vboksi.getChildren().remove(pick1);
            vboksi.getChildren().remove(sivu2);
            valinnat[0] = "A";
            kohta++;
            getQ2();
        });
        //Question 1 Country (B)
        Bcountry.setOnAction(e -> {
            vboksi.getChildren().remove(q1Title);
            vboksi.getChildren().remove(q1imagehbox);
            vboksi.getChildren().remove(pick1);
            vboksi.getChildren().remove(sivu2);
            valinnat[0] = "B";
            kohta++;
            getQ2();
        });

    }
    /** Tekee toisen kysymyksen, muokkaa vboksia.
     */
    public void getQ2() {
        //Question 2
        Text q2Title = new Text("How much attention are you ready to give your pet?");
        q2Title.setFont(fontti);
        HBox q2imagehbox = new HBox(10);
        q2imagehbox.setAlignment(Pos.CENTER);
        Image q2p1 = new Image("chewy-4cHELXbJhvU-unsplash.jpg");
        Image q2p2 = new Image("erik-mclean-U-Vu_r6qyyU-unsplash.jpg");
        ImageView ivq2p1 = new ImageView(q2p1);
        ImageView ivq2p2 = new ImageView(q2p2);
        ivq2p1.setFitHeight(240);
        ivq2p1.setFitWidth(200);
        ivq2p2.setFitHeight(240);
        ivq2p2.setFitWidth(200);
        q2imagehbox.getChildren().addAll(ivq2p1, ivq2p2);
        HBox pick2 = new HBox(150);
        pick2.setAlignment(Pos.CENTER);
        Button blots = new Button("A lot");
        Button bnotmuch = new Button("Not much");
        blots.setPrefWidth(70);
        bnotmuch.setPrefWidth(70);
        pick2.getChildren().addAll(blots, bnotmuch);
        Text sivu3 = new Text(sivu + " / 7");
        sivu++;
        vboksi.getChildren().add(q2Title);
        vboksi.getChildren().add(q2imagehbox);
        vboksi.getChildren().add(pick2);
        vboksi.getChildren().add(sivu3);

        //Question 2 A lot (A)
        blots.setOnAction(e -> {
            vboksi.getChildren().remove(q2Title);
            vboksi.getChildren().remove(q2imagehbox);
            vboksi.getChildren().remove(pick2);
            vboksi.getChildren().remove(sivu3);
            valinnat[1] = "A";
            kohta++;
            getQ3();
        });
        //Question 2 Not much (B)
        bnotmuch.setOnAction(e -> {
            vboksi.getChildren().remove(q2Title);
            vboksi.getChildren().remove(q2imagehbox);
            vboksi.getChildren().remove(pick2);
            vboksi.getChildren().remove(sivu3);
            valinnat[1] = "B";
            kohta++;
            getQ3();
        });
    }
    /** Tekee kolmannen kysymyksen, muokkaa vboksia.
     */
    public void getQ3() {
        //Question 3
        Text q3Title = new Text("Do you like to go outside?");
        q3Title.setFont(fontti);
        HBox q3imagehbox = new HBox(10);
        q3imagehbox.setAlignment(Pos.CENTER);
        Image q3p1 = new Image("devon-hawkins-BWFjuR6cQCg-unsplash.jpg");
        Image q3p2 = new Image("fausto-sandoval-u2qP5qCLgQ4-unsplash.jpg");
        ImageView ivq3p1 = new ImageView(q3p1);
        ImageView ivq3p2 = new ImageView(q3p2);
        ivq3p1.setFitHeight(240);
        ivq3p1.setFitWidth(200);
        ivq3p2.setFitHeight(240);
        ivq3p2.setFitWidth(200);
        q3imagehbox.getChildren().addAll(ivq3p1, ivq3p2);
        HBox pick3 = new HBox(150);
        pick3.setAlignment(Pos.CENTER);
        Button byes = new Button("Yes");
        Button bno = new Button("No");
        byes.setPrefWidth(70);
        bno.setPrefWidth(70);
        pick3.getChildren().addAll(byes, bno);
        Text sivu4 = new Text(sivu + " / 7");
        sivu++;
        vboksi.getChildren().add(q3Title);
        vboksi.getChildren().add(q3imagehbox);
        vboksi.getChildren().add(pick3);
        vboksi.getChildren().add(sivu4);

        //Question 3 yes (A)
        byes.setOnAction(e -> {
            vboksi.getChildren().remove(q3Title);
            vboksi.getChildren().remove(q3imagehbox);
            vboksi.getChildren().remove(pick3);
            vboksi.getChildren().remove(sivu4);
            valinnat[2] = "A";
            kohta++;
            getQ4();
        });
        //Question 3 no (B)
        bno.setOnAction(e -> {
            vboksi.getChildren().remove(q3Title);
            vboksi.getChildren().remove(q3imagehbox);
            vboksi.getChildren().remove(pick3);
            vboksi.getChildren().remove(sivu4);
            valinnat[2] = "B";
            kohta++;
            getQ4();
        });
    }
    /** Tekee neljannen kysymyksen, muokkaa vboksia.
     */
    public void getQ4() {
        //Question 4
        Text q4Title = new Text("How much money are you willing to spend?");
        q4Title.setFont(fontti);
        HBox q4imagehbox = new HBox(10);
        q4imagehbox.setAlignment(Pos.CENTER);
        Image q4p1 = new Image("omid-armin-8Nppe0yLmn8-unsplash.jpg");
        Image q4p2 = new Image("marcel-strauss-trmjRyr3Itk-unsplash.jpg");
        ImageView ivq4p1 = new ImageView(q4p1);
        ImageView ivq4p2 = new ImageView(q4p2);
        ivq4p1.setFitHeight(240);
        ivq4p1.setFitWidth(200);
        ivq4p2.setFitHeight(240);
        ivq4p2.setFitWidth(200);
        q4imagehbox.getChildren().addAll(ivq4p1, ivq4p2);
        HBox pick4 = new HBox(150);
        pick4.setAlignment(Pos.CENTER);
        Button btons = new Button("A lot");
        Button bntm = new Button("Not much");
        btons.setPrefWidth(70);
        bntm.setPrefWidth(70);
        pick4.getChildren().addAll(btons, bntm);
        Text sivu5 = new Text(sivu + " / 7");
        sivu++;
        vboksi.getChildren().add(q4Title);
        vboksi.getChildren().add(q4imagehbox);
        vboksi.getChildren().add(pick4);
        vboksi.getChildren().add(sivu5);

        //Question 4 A lot (A)
        btons.setOnAction(e -> {
            vboksi.getChildren().remove(q4Title);
            vboksi.getChildren().remove(q4imagehbox);
            vboksi.getChildren().remove(pick4);
            vboksi.getChildren().remove(sivu5);
            valinnat[3] = "A";
            kohta++;
            getQ5();
        });
        //Question 4 Not that much (B)
        bntm.setOnAction(e -> {
            vboksi.getChildren().remove(q4Title);
            vboksi.getChildren().remove(q4imagehbox);
            vboksi.getChildren().remove(pick4);
            vboksi.getChildren().remove(sivu5);
            valinnat[3] = "B";
            kohta++;
            getQ5();
        });
    }
    /** Tekee viidennen kysymyksen, muokkaa vboksia.
     */
    public void getQ5() {
        //Question 5
        Text q5Title = new Text("How easily are you grossed out?");
        q5Title.setFont(fontti);
        HBox q5imagehbox = new HBox(10);
        q5imagehbox.setAlignment(Pos.CENTER);
        Image q5p2 = new Image("fernando-jorge-cropped.jpg");
        Image q5p1 = new Image("17177619998_12a9bce3be_q.jpg");
        ImageView ivq5p1 = new ImageView(q5p1);
        ImageView ivq5p2 = new ImageView(q5p2);
        ivq5p1.setFitHeight(200);
        ivq5p1.setFitWidth(200);
        ivq5p2.setFitHeight(200);
        ivq5p2.setFitWidth(200);
        q5imagehbox.getChildren().addAll(ivq5p1, ivq5p2);
        HBox pick5 = new HBox(120);
        pick5.setAlignment(Pos.CENTER);
        Button btons = new Button("Not that easily");
        Button bntm = new Button("Super easily");
        btons.setPrefWidth(100);
        bntm.setPrefWidth(100);
        pick5.getChildren().addAll(btons, bntm);
        Text sivu6 = new Text(sivu + " / 7");
        sivu++;
        vboksi.getChildren().add(q5Title);
        vboksi.getChildren().add(q5imagehbox);
        vboksi.getChildren().add(pick5);
        vboksi.getChildren().add(sivu6);

        //Question 5 A lot (A)
        btons.setOnAction(e -> {
            vboksi.getChildren().remove(q5Title);
            vboksi.getChildren().remove(q5imagehbox);
            vboksi.getChildren().remove(pick5);
            vboksi.getChildren().remove(sivu6);
            valinnat[4] = "A";
            kohta++;
            try {
                readToFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                getEnd();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        //Question 5 Not that much (B)
        bntm.setOnAction(e -> {
            vboksi.getChildren().remove(q5Title);
            vboksi.getChildren().remove(q5imagehbox);
            vboksi.getChildren().remove(pick5);
            vboksi.getChildren().remove(sivu6);
            valinnat[4] = "B";
            kohta++;
            try {
                readToFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                getEnd();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    /** Lukee kayttajan antamat vastaukset tiedostoon.
     * @throws IOException IOException
     */
    public void readToFile() throws IOException {
        String tiedostonnimi = "valinnat.txt";
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(tiedostonnimi));
        outputStream.writeObject(valinnat);
    }
    /** Lukee tiedostossa olevan taulukon uuteen taulukkoon.
     * @throws IOException IOException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public void readFromFile() throws IOException, ClassNotFoundException {
        String tiedostonnimi = "valinnat.txt";
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(tiedostonnimi));
        finalvalinnat = (String[])inputStream.readObject();
        System.out.println("Valinnat: " + Arrays.toString(finalvalinnat));
    }
    /** Tarkistaa, etta kaikki kysymykset on kayty lapi.
     * Muokkaa vboksia vastaamaan kayttajan antamia vastauksia ja palauttaa sen.
     * @throws IOException IOException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public void getEnd() throws IOException, ClassNotFoundException {
        Text sivu7 = new Text(sivu++ + " / 7");
        Text bye = new Text("Have a good day " + k1.getNimi() + "!");
        Text goodidea = new Text("Are you sure " + k1.getPetname() + " is a good name option?");
        bye.setFont(fontti);
        goodidea.setFont(fontti);
        if(kohta ==5) {
            readFromFile();
            if (k1.getIka() >= 18) {
                //Ending 1 big dog
                if (Arrays.toString(finalvalinnat).equals("[B, A, A, A, A]") ||
                        Arrays.toString(finalvalinnat).equals("[B, A, A, A, B]")) {
                    Text e1Title = new Text("You could get a big dog breed");
                    e1Title.setFont(fontti);
                    HBox e1imagehbox = new HBox();
                    e1imagehbox.setAlignment(Pos.CENTER);
                    Image e1p1 = new Image("noemi-macavei-katocz-c7bUIRBqapA-unsplash.jpg");
                    Image e1p2 = new Image("colin-davis-ZTD_WOL4cXA-unsplash.jpg");
                    ImageView ive1p1 = new ImageView(e1p1);
                    ImageView ive1p2 = new ImageView(e1p2);
                    ive1p1.setFitHeight(265);
                    ive1p1.setFitWidth(220);
                    ive1p2.setFitHeight(265);
                    ive1p2.setFitWidth(220);
                    e1imagehbox.getChildren().addAll(ive1p1, ive1p2);
                    vboksi.getChildren().add(e1Title);
                    vboksi.getChildren().add(goodidea);
                    vboksi.getChildren().add(e1imagehbox);
                    vboksi.getChildren().add(bye);
                    vboksi.getChildren().add(sivu7);
                }
                //Ending 2 small dog
                else if (Arrays.toString(finalvalinnat).equals("[A, A, A, A, A]") ||
                        Arrays.toString(finalvalinnat).equals("[A, A, A, A, B]") ||
                        Arrays.toString(finalvalinnat).equals("[A, A, A, B, A]") ||
                        Arrays.toString(finalvalinnat).equals("[A, A, A, B, B]")) {
                    Text e2Title = new Text("You could get a small dog breed");
                    e2Title.setFont(fontti);
                    HBox e2imagehbox = new HBox();
                    e2imagehbox.setAlignment(Pos.CENTER);
                    Image e2p1 = new Image("m-poiss-i8O3Qw5hWj0-unsplash.jpg");
                    Image e2p2 = new Image("pearl-lynn-6qzDI1LYhGc-unsplash.jpg");
                    ImageView ive2p1 = new ImageView(e2p1);
                    ImageView ive2p2 = new ImageView(e2p2);
                    ive2p1.setFitHeight(265);
                    ive2p1.setFitWidth(220);
                    ive2p2.setFitHeight(265);
                    ive2p2.setFitWidth(220);
                    e2imagehbox.getChildren().addAll(ive2p1, ive2p2);
                    vboksi.getChildren().add(e2Title);
                    vboksi.getChildren().add(goodidea);
                    vboksi.getChildren().add(e2imagehbox);
                    vboksi.getChildren().add(bye);
                    vboksi.getChildren().add(sivu7);
                }
                //Ending 3 cat
                else if (Arrays.toString(finalvalinnat).equals("[A, A, B, A, A]") ||
                        Arrays.toString(finalvalinnat).equals("[A, A, B, A, B]") ||
                        Arrays.toString(finalvalinnat).equals("[A, A, B, B, A]") ||
                        Arrays.toString(finalvalinnat).equals("[A, A, B, B, B]")) {
                    Text e3Title = new Text("You could get a cat");
                    e3Title.setFont(fontti);
                    HBox e3imagehbox = new HBox();
                    e3imagehbox.setAlignment(Pos.CENTER);
                    Image e3p1 = new Image("alex-meier-KGiQFgF7dkc-unsplash.jpg");
                    Image e3p2 = new Image("milada-vigerova-7E9qvMOsZEM-unsplash.jpg");
                    ImageView ive3p1 = new ImageView(e3p1);
                    ImageView ive3p2 = new ImageView(e3p2);
                    ive3p1.setFitHeight(265);
                    ive3p1.setFitWidth(220);
                    ive3p2.setFitHeight(265);
                    ive3p2.setFitWidth(220);
                    e3imagehbox.getChildren().addAll(ive3p1, ive3p2);
                    vboksi.getChildren().add(e3Title);
                    vboksi.getChildren().add(goodidea);
                    vboksi.getChildren().add(e3imagehbox);
                    vboksi.getChildren().add(bye);
                    vboksi.getChildren().add(sivu7);
                }
                //Ending 4 fish/hamster
                else if (Arrays.toString(finalvalinnat).equals("[A, B, A, A, A]") ||
                        Arrays.toString(finalvalinnat).equals("[A, B, A, B, A]") ||
                        Arrays.toString(finalvalinnat).equals("[A, B, B, A, A]") ||
                        Arrays.toString(finalvalinnat).equals("[A, B, B, B, A]") ||
                        Arrays.toString(finalvalinnat).equals("[B, B, B, A, A]") ||
                        Arrays.toString(finalvalinnat).equals("[B, B, B, B, A]")) {
                    Text e4Title = new Text("You could get fish or a hamster \n depending on your preference");
                    e4Title.setFont(fontti);
                    HBox e4imagehbox = new HBox();
                    e4imagehbox.setAlignment(Pos.CENTER);
                    Image e4p1 = new Image("maksym-sirman-MRpMpHFz6eQ-unsplash.jpg");
                    Image e4p2 = new Image("denis-bayer-4KC4hUWCtJI-unsplash.jpg");
                    ImageView ive4p1 = new ImageView(e4p1);
                    ImageView ive4p2 = new ImageView(e4p2);
                    ive4p1.setFitHeight(265);
                    ive4p1.setFitWidth(220);
                    ive4p2.setFitHeight(265);
                    ive4p2.setFitWidth(220);
                    e4imagehbox.getChildren().addAll(ive4p1, ive4p2);
                    vboksi.getChildren().add(e4Title);
                    vboksi.getChildren().add(goodidea);
                    vboksi.getChildren().add(e4imagehbox);
                    vboksi.getChildren().add(bye);
                    vboksi.getChildren().add(sivu7);
                }
                //Ending 5 Maine Coon / Savannah
                else if (Arrays.toString(finalvalinnat).equals("[B, A, A, B, A]") ||
                        Arrays.toString(finalvalinnat).equals("[B, A, A, B, B]") ||
                        Arrays.toString(finalvalinnat).equals("[B, A, B, A, A]") ||
                        Arrays.toString(finalvalinnat).equals("[B, A, B, A, B]") ||
                        Arrays.toString(finalvalinnat).equals("[B, A, B, B, A]") ||
                        Arrays.toString(finalvalinnat).equals("[B, A, B, B, B]")) {
                    Text e5Title = new Text("You could get a Maine Coon or a Savannah");
                    e5Title.setFont(fontti);
                    HBox e5imagehbox = new HBox();
                    e5imagehbox.setAlignment(Pos.CENTER);
                    Image e5p1 = new Image("kanashi-zCFkafZ5tCQ-unsplash.jpg");
                    Image e5p2 = new Image("petrebels-xbk720P0NyE-unsplash.jpg");
                    ImageView ive5p1 = new ImageView(e5p1);
                    ImageView ive5p2 = new ImageView(e5p2);
                    ive5p1.setFitHeight(265);
                    ive5p1.setFitWidth(220);
                    ive5p2.setFitHeight(265);
                    ive5p2.setFitWidth(220);
                    e5imagehbox.getChildren().addAll(ive5p1, ive5p2);
                    vboksi.getChildren().add(e5Title);
                    vboksi.getChildren().add(goodidea);
                    vboksi.getChildren().add(e5imagehbox);
                    vboksi.getChildren().add(bye);
                    vboksi.getChildren().add(sivu7);
                }
                //Ending 6 Snake
                else if (Arrays.toString(finalvalinnat).equals("[A, B, A, A, B]") ||
                        Arrays.toString(finalvalinnat).equals("[A, B, B, A, B]") ||
                        Arrays.toString(finalvalinnat).equals("[B, B, B, A, B]")) {
                    Text e6Title = new Text("You could get a snake");
                    e6Title.setFont(fontti);
                    HBox e6imagehbox = new HBox();
                    e6imagehbox.setAlignment(Pos.CENTER);
                    Image e6p1 = new Image("elang-wardhana-gDEkCVwMgAE-unsplash.jpg");
                    Image e6p2 = new Image("natasha-polyakova-Fms4iqYzEV4-unsplash.jpg");
                    ImageView ive6p1 = new ImageView(e6p1);
                    ImageView ive6p2 = new ImageView(e6p2);
                    ive6p1.setFitHeight(265);
                    ive6p1.setFitWidth(220);
                    ive6p2.setFitHeight(265);
                    ive6p2.setFitWidth(220);
                    e6imagehbox.getChildren().addAll(ive6p1, ive6p2);
                    vboksi.getChildren().add(e6Title);
                    vboksi.getChildren().add(goodidea);
                    vboksi.getChildren().add(e6imagehbox);
                    vboksi.getChildren().add(bye);
                    vboksi.getChildren().add(sivu7);
                }
                //Ending 7 Leopard Gecko
                else if (Arrays.toString(finalvalinnat).equals("[A, B, A, B, B]") ||
                        Arrays.toString(finalvalinnat).equals("[A, B, B, B, B]") ||
                        Arrays.toString(finalvalinnat).equals("[B, B, B, B, B]")) {
                    Text e7Title = new Text("You could get a Leopard Gecko");
                    e7Title.setFont(fontti);
                    HBox e7imagehbox = new HBox();
                    e7imagehbox.setAlignment(Pos.CENTER);
                    Image e7p1 = new Image("Afghan_Leopard_Gecko.jpg");
                    Image e7p2 = new Image("tree-water-nature-snow-animal-cute-1194535-pxhere.com.jpg");
                    ImageView ive7p1 = new ImageView(e7p1);
                    ImageView ive7p2 = new ImageView(e7p2);
                    ive7p1.setFitHeight(265);
                    ive7p1.setFitWidth(220);
                    ive7p2.setFitHeight(265);
                    ive7p2.setFitWidth(220);
                    e7imagehbox.getChildren().addAll(ive7p1, ive7p2);
                    vboksi.getChildren().add(e7Title);
                    vboksi.getChildren().add(goodidea);
                    vboksi.getChildren().add(e7imagehbox);
                    vboksi.getChildren().add(bye);
                    vboksi.getChildren().add(sivu7);
                }
                //Ending 8 Chickens
                else if (Arrays.toString(finalvalinnat).equals("[B, B, A, A, A]") ||
                        Arrays.toString(finalvalinnat).equals("[B, B, A, A, B]") ||
                        Arrays.toString(finalvalinnat).equals("[B, B, A, B, A]") ||
                        Arrays.toString(finalvalinnat).equals("[B, B, A, B, B]")) {
                    Text e8Title = new Text("You could get chickens");
                    e8Title.setFont(fontti);
                    HBox e8imagehbox = new HBox();
                    e8imagehbox.setAlignment(Pos.CENTER);
                    Image e8p1 = new Image("ben-moreland-auijD19Byq8-unsplash.jpg");
                    Image e8p2 = new Image("alex-ambedo-Vzr0kxzwVPQ-unsplash.jpg");
                    ImageView ive8p1 = new ImageView(e8p1);
                    ImageView ive8p2 = new ImageView(e8p2);
                    ive8p1.setFitHeight(265);
                    ive8p1.setFitWidth(220);
                    ive8p2.setFitHeight(265);
                    ive8p2.setFitWidth(220);
                    e8imagehbox.getChildren().addAll(ive8p1, ive8p2);
                    vboksi.getChildren().add(e8Title);
                    vboksi.getChildren().add(goodidea);
                    vboksi.getChildren().add(e8imagehbox);
                    vboksi.getChildren().add(bye);
                    vboksi.getChildren().add(sivu7);
                }
            }
            else {
                Text goodtry = new Text("Sorry " + k1.getNimi() + " but you're not old enough >:)");
                goodtry.setFont(fontti);
                Text namesuggestion = new Text("Maybe think about " + k1.getPetname() + " while you're at it");
                namesuggestion.setFont(fontti);
                Text tsure = new Text("What I mean is that it might not be the best name\n\n\n");
                tsure.setFont(fontti);
                vboksi.getChildren().add(goodtry);
                vboksi.getChildren().add(namesuggestion);
                vboksi.getChildren().add(tsure);
                vboksi.getChildren().add(sivu7);

            }
        }
        else{
            System.out.println("Error with question amount");
        }
    }
    /** Ajaa ohjelman
     * @param args String[]
     * */
    public static void main(String[] args) {
        launch();
    }
}
