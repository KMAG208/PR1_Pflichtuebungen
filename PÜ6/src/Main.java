public class Main {
    public static void main(String[] args) throws Exception {
    	Uhrzeit zeit1 = new Uhrzeit(11, 12,12);
    	Uhrzeit zeit2 = new Uhrzeit(11,13,50);
    	int sekunden = 0;
    	
    	System.out.println(zeit1.addiereSekunden(sekunden));
    }
}
