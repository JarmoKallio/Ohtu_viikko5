package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
        OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenMaara =0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        luvut = new int[KAPASITEETTI];
        alustaTaulukko();
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti >= 0) {
            luvut = new int[kapasiteetti];
            alustaTaulukko();
            this.kasvatuskoko = OLETUSKASVATUS;
        }
    }

    private void alustaTaulukko() {
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (!(kapasiteetti < 0 && kasvatuskoko < 0)) {
        luvut = new int[kapasiteetti];
        alustaTaulukko();
        this.kasvatuskoko = kasvatuskoko;
        }
    }

    public boolean lisaaLuku(int luku) {
        if (!sisaltaaLuvun(luku)) {
            if (alkioidenMaara == luvut.length) {
                kasvataTaulukonKokoa();
            }
            luvut[alkioidenMaara] = luku;
            alkioidenMaara++;
            return true;
        }
        return false;
    }

    private void kasvataTaulukonKokoa() {
        int[] taulukkoOld = new int[luvut.length];
        taulukkoOld = luvut;
        kopioiTaulukko(luvut, taulukkoOld);
        luvut = new int[alkioidenMaara + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, luvut);
    }

    public boolean sisaltaaLuvun(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int indeksi = haeLuvunSijainti(luku);
        return poistaLuku(indeksi);
    }

    private boolean poistaLuku(int indeksi) {
        if (indeksi != -1) {
            for (int j = indeksi; j < alkioidenMaara - 1; j++) {
                luvut[j] = luvut[j + 1];
            }
            luvut[alkioidenMaara - 1] = 0;
            alkioidenMaara--;
            return true;
        }
        return false;
    }

    private int haeLuvunSijainti(int luku) {
        int indeksi = -1;
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == luvut[i]) {
                indeksi = i;
                break;
            }
        }
        return indeksi;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int getJoukonKoko() {
        return alkioidenMaara;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        if (alkioidenMaara > 0) {
            for (int i = 0; i < alkioidenMaara - 1; i++) {
                tuotos += luvut[i];
                tuotos += ", ";
            }
            tuotos += luvut[alkioidenMaara - 1];
        }
        return tuotos + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenMaara];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaaLuku(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaaLuku(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaaLuku(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaaLuku(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }

}
