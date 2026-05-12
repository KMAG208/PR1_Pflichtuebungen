public class Produkt {
	String name = "Unknown";
	double preis = 0.0;
	
	Produkt(String eName, double ePreis){
		if(eName != null) {
			name = eName;
		}
		if(ePreis >= 0) {
			preis = ePreis;
		}
	}
}
