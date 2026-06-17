public class Zeitpunkt {
    private int tag;
    private int monat;
    private int jahr;
    private Uhrzeit uhrzeit;

    /**
    * Konstruktor der Klasse Zeitpunkt.
    *
    * Prüft die übergebenen Werte auf Gültigkeit.
    * Bei einem ungültigen Datum oder bei einer Uhrzeit mit dem Wert null wird das Objekt
    * auf den Standardwert 01.01.1900 00:00:00 gesetzt.
    *
    * @param tag Der Tag des Zeitpunkts.
    * @param monat Der Monat des Zeitpunkts.
    * @param jahr Das Jahr des Zeitpunkts.
    * @param uhrzeit Die Uhrzeit des Zeitpunkts.
    */
    public Zeitpunkt(int tag, int monat, int jahr, Uhrzeit uhrzeit){
        if(uhrzeit == null || !istGueltigesDatum(tag, monat, jahr)) {
            this.tag = 1;
            this.monat = 1;
            this.jahr = 1900;
            this.uhrzeit = new Uhrzeit(0, 0, 0);
        } else {
            this.tag = tag;
            this.monat = monat;
            this.jahr = jahr;
            this.uhrzeit = uhrzeit;
        }
    }

    /**
    * Liefert den Tag des aktuellen Zeitpunkts.
    *
    * @return Der Tag des Zeitpunkts.
    */
    public int getTag(){
        return this.tag;
    }

    /**
    * Liefert den Monat des aktuellen Zeitpunkts.
    *
    * @return Der Monat des Zeitpunkts.
    */
    public int getMonat(){
        return this.monat;
    }

    /**
    * Liefert das Jahr des aktuellen Zeitpunkts.
    *
    * @return Das Jahr des Zeitpunkts.
    */
    public int getJahr(){
        return this.jahr;
    }

    /**
    * Liefert die Uhrzeit des aktuellen Zeitpunkts.
    *
    * @return Die gespeicherte Uhrzeit.
    */
    public Uhrzeit getUhrzeit(){
        return this.uhrzeit;
    }

    /**
    * Prüft, ob die übergebenen Werte ein gültiges Datum im gregorianischen Kalender bilden.
    *
    * Ein Datum ist genau dann gültig, wenn das Jahr größer oder gleich 1900 ist,
    * der Monat im Bereich 1 bis 12 liegt und der Tag im gültigen Bereich des jeweiligen
    * Monats liegt. Dabei werden Monatslängen und Schaltjahre berücksichtigt.
    *
    * @param tag Der zu prüfende Tag.
    * @param monat Der zu prüfende Monat.
    * @param jahr Das zu prüfende Jahr.
    * @return true, wenn das Datum gültig ist, sonst false.
    */
    public boolean istGueltigesDatum(int tag, int monat, int jahr){
        if (!(tag <= tageImMonat(monat, jahr) && tag > 0)) {
            return false;            
        } 

        if(!(monat <= 12 && monat > 0)){
            return false;
        }

        if (!(jahr >= 1900)) {
            return false;
        }

        return true;
    }

    /**
    * Prüft, ob das Jahr des aktuellen Zeitpunkts ein Schaltjahr ist.
    *
    * Ein Jahr ist ein Schaltjahr, wenn es durch 4 teilbar ist.
    * Ist es jedoch durch 100 teilbar, ist es kein Schaltjahr.
    * Ist es zusätzlich durch 400 teilbar, ist es doch ein Schaltjahr.
    *
    * @return true, wenn das Jahr ein Schaltjahr ist, sonst false.
    */
    public boolean istSchaltjahr(){
        if(this.jahr %100 != 0){
            return this.jahr %4 == 0;
        }

        if (this.jahr %400 != 0) {
            return !(this.jahr %100 == 0);            
        }

        return this.jahr %400 == 0;
    }

    /**
    * Prüft, ob das übergebene Jahr ein Schaltjahr ist.
    *
    * Ein Jahr ist ein Schaltjahr, wenn es durch 4 teilbar ist.
    * Ist es jedoch durch 100 teilbar, ist es kein Schaltjahr.
    * Ist es zusätzlich durch 400 teilbar, ist es doch ein Schaltjahr.
    *
    * @param jahr Das Jahr welches überprüft werden soll.
    * @return true, wenn das Jahr ein Schaltjahr ist, sonst false.
    */
    public static boolean istSchaltjahr(int jahr){
        if(jahr %100 != 0){
            return jahr %4 == 0;
        }

        if (jahr %400 != 0) {
            return !(jahr %100 == 0);            
        }

        return jahr %400 == 0;
    }

    /**
    * Liefert die Anzahl der Tage des Monats des aktuellen Zeitpunkts.
    *
    * Dabei werden die unterschiedlichen Monatslängen sowie Schaltjahre
    * berücksichtigt.
    *
    * @return Die Anzahl der Tage im aktuellen Monat.
    */
    public int tageImMonat(){
        int[] monatLaenge = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

        if(!(istSchaltjahr() && this.monat == 2)){
            return monatLaenge[this.monat-1];
        }

        return monatLaenge[2-1]+1;
    }

    /**
    * Liefert die Anzahl der Tage des übergebenen Monats.
    *
    * Dabei werden die unterschiedlichen Monatslängen sowie Schaltjahre
    * berücksichtigt.
    *
    * @param monat Der Monat welcher überprüft werden soll.
    * @param jahr Das Jahr in welchem dieser Monat liegt.
    * @return Die Anzahl der Tage im aktuellen Monat.
    */
    public static int tageImMonat(int monat, int jahr){
        int[] monatLaenge = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

        if(!(istSchaltjahr(jahr) && monat == 2)){
            return monatLaenge[monat-1];
        }

        return monatLaenge[2-1]+1;
    }

    /**
    * Liefert den Wochentag des aktuellen Zeitpunkts.
    *
    * Als Rückgabewert wird einer der folgenden Strings geliefert:
    * Montag, Dienstag, Mittwoch, Donnerstag, Freitag, Samstag oder Sonntag.
    *
    * Als Orientierung gilt: Der 01.01.1900 war ein Montag.
    *
    * @return Der Wochentag des aktuellen Zeitpunkts als String.
    */
    public String wochenTag(){
        String[] rueckgabeWerte = new String[]{"Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag","Sonntag"};
        int tageCount = 0;

        for(int i = 1900; i < this.jahr; i++){
            if(istSchaltjahr(i)){
                tageCount += 366;
            } else {
                tageCount += 365;
            }
        }

        for(int i = 1; i < this.monat; i++){
            tageCount += tageImMonat(i, this.jahr);
        }

        for(int i = 1; i < this.tag ; i++){
            tageCount ++;
        }

        return rueckgabeWerte[(tageCount % 7)];
    }
}
