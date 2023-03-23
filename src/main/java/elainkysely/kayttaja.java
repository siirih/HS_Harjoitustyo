package elainkysely;
/** Luokka asettaa kayttajan nimen, ian ja nimiehdotuksen lemmikille
 * @author Siiri Harinen
 * @author siirihar@student.uef.fi
 * @version 1.0 2023/03/22
 */
public class kayttaja {
    /**Kayttajan nimi merkkijonona */
    private String nimi;
    /**Kayttajan ika kokonaislukuna*/
    private int ika;
    /**Nimiehdotus lemmikille merkkijonona*/
    private String petname;
    /** Kayttajan perustiedot
     * @param n String nimi
     * @param i int ika
     * @param pn String nimiehdotus lemmikille*/
    public kayttaja(String n, int i, String pn){
        this.setNimi(n);
        this.setIka(i);
        this.setPetname(pn);
    }
    /** Palauttaa nimen
     * @return String nimi*/
    public String getNimi() {
        return nimi;
    }
    /** Asettaa nimen
     * @param nimi String*/
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    /** Palauttaa ian
     * @return int ika*/
    public int getIka() {
        return ika;
    }
    /** Asetaa ian
     * @param ika int*/
    public void setIka(int ika) {
        this.ika = ika;
    }
    /** Palauttaa nimiehdotuksen lemmikille
     * @return String petname*/
    public String getPetname() {
        return petname;
    }
    /** Asettaa nimiehdotuksen lemmikille
     * @param petname String*/
    public void setPetname(String petname) {
        this.petname = petname;
    }
}

