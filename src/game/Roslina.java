package game;
public class Roslina extends Organizm {
    private final int szasna_na_rozmnozenie=2;    
    protected int get_szansa_na_rozmnozenie()
    {
        return szasna_na_rozmnozenie;
    }

    public void akcja()
    {
        int rnd= (int)(Math.random()%100);
        if (rnd < szasna_na_rozmnozenie)
        {
            Pair pt = losowe_puste_pole();
            if (pt.first == this.get_x() && pt.second == this.get_y())
                        return;
            rozmnoz(pt.first, pt.second, swiat);
        }
    }

    public void kolizja(Organizm kol)
    {
        swiat.add_log(this.get_nazwa() + "Zostaje rozdeptany przez" + kol.get_nazwa());
        umrzyj();
        kol.przesun(this.get_x(), this.get_y());
    }
}
