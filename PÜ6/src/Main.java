public class Main {
    public static void main(String[] args) throws Exception {
  	 	
    	Uhrzeit zeit1 = new Uhrzeit(11, 12,12);
    	Uhrzeit zeit2 = new Uhrzeit(11,13,50);
    	int sekunden = 120;

		Zeitpunkt punkt = new Zeitpunkt(14, 1, 4001, zeit2);
    	System.out.println(punkt.wochenTag());
    }
}
