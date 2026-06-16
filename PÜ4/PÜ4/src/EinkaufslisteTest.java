import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class EinkaufslisteTest {

    // Test_A: addProduct()
    @Test
    public void testAddProduct() {
    	Einkaufsliste liste = new Einkaufsliste();
        Produkt p1 = new Produkt("Milch", 1.20);
        
        try {
            liste.addProduct(null);
        } catch (IllegalArgumentException e) {
        }
        assertNull(liste.gibtProduktAnStelle(0), "Test_A01");

        // Hinzufügen von exakt 10 Produkten (Mindestanzahl)
        assertDoesNotThrow(() -> {
            for(int i = 0; i < 10; i++) {
                liste.addProduct(p1);
            }
        }, "Test_A02");

        // Prüfen, ob das 10. Produkt (Index 9) korrekt gespeichert wurde
        assertEquals(p1, liste.gibtProduktAnStelle(9), "Test_A03");
    }

    // Test_B: gibtProduktAnStelle()
    @Test
    public void testGibtProduktAnStelle() {
        Einkaufsliste liste = new Einkaufsliste();
        Produkt p1 = new Produkt("Milch", 1.20);
        Produkt p2 = new Produkt("Brot", 2.00);
        liste.addProduct(p1);
        liste.addProduct(p2);

        // Gültige Indizes
        
        // Index = 0 -> Produkt p1
        assertEquals(p1, liste.gibtProduktAnStelle(0), "Test_B01");
        
        // Index = 1 -> Produkt p2
        assertEquals(p2, liste.gibtProduktAnStelle(1), "Test_B02");

        // Ungültige Indizes
        
        // Index = -1 -> null
        assertNull(liste.gibtProduktAnStelle(-1), "Test_B03");
        
        // Index = 2 -> null
        assertNull(liste.gibtProduktAnStelle(2), "Test_B04");
    }

    // Test_C: entferneProdukt()
    @Test
    public void testEntferneProdukt() {
        Einkaufsliste liste = new Einkaufsliste();
        Produkt p1 = new Produkt("Apfel", 1.0);
        Produkt p2 = new Produkt("Banane", 2.0);
        Produkt p3 = new Produkt("Birne", 3.0);
        Produkt p4 = new Produkt("Banane", 4.0); 

        liste.addProduct(p1);
        liste.addProduct(p2);
        liste.addProduct(p3);
        liste.addProduct(p4);

        // Banane entfernen -> erstes Vorkommen wird entfernt
        liste.entferneProdukt(new String("Banane"));

        // Index 0 -> Apfel
        assertEquals(p1, liste.gibtProduktAnStelle(0), "Test_C01");
        
        // Index 1 -> Birne
        assertEquals(p3, liste.gibtProduktAnStelle(1), "Test_C02");
        
        // Index 2 -> Banane (zweites Vorkommen)
        assertEquals(p4, liste.gibtProduktAnStelle(2), "Test_C03");
        
        // Index 3 -> null
        assertNull(liste.gibtProduktAnStelle(3), "Test_C04");
        
        // Null übergeben 
        try {
            liste.entferneProdukt(null);
        } catch (IllegalArgumentException e) {
        }
        assertEquals(p1, liste.gibtProduktAnStelle(0), "Test_C05");
        assertEquals(p3, liste.gibtProduktAnStelle(1), "Test_C06");
        assertEquals(p4, liste.gibtProduktAnStelle(2), "Test_C07");
        
        // Produkt übergeben, das nicht in der Liste ist
        try {
            liste.entferneProdukt("Kiwi");
        } catch (IllegalArgumentException e) {
        }
        assertEquals(p1, liste.gibtProduktAnStelle(0), "Test_C08");
        assertEquals(p3, liste.gibtProduktAnStelle(1), "Test_C09");
        assertEquals(p4, liste.gibtProduktAnStelle(2), "Test_C10");
        
        // Groß- und Kleinschreibung testen
        liste.entferneProdukt("banane"); 
        assertEquals(p4, liste.gibtProduktAnStelle(2), "Test_C11");
        
        // Letztes Element entfernen
        liste.entferneProdukt(new String("Banane"));
        assertNull(liste.gibtProduktAnStelle(2), "Test_C12");
        
        // Erstes Element entfernen
        liste.entferneProdukt(new String("Apfel"));
        assertEquals(p3, liste.gibtProduktAnStelle(0), "Test_C13");
        
        // Bei leerer Liste testen
        Einkaufsliste leereListe = new Einkaufsliste();
        try {
            leereListe.entferneProdukt("Apfel");
        } catch (IllegalArgumentException e) {
        }
        assertNull(leereListe.gibtProduktAnStelle(0), "Test_C14");
    }

    // Test_D: findeProduktKombination()
    @Test
    public void testFindeProduktKombination() {
        Einkaufsliste liste = new Einkaufsliste();
        Produkt p1 = new Produkt("Apfel", 1.50);
        Produkt p2 = new Produkt("Banane", 2.50);
        Produkt p3 = new Produkt("Kirsche", 3.00);

        liste.addProduct(p1);
        liste.addProduct(p2);
        liste.addProduct(p3);

        // Passende Kombination
        
        // Budget = 4.00 -> Apfel (1.50) + Banane (2.50) -> Array mit 2 Elementen
        Produkt[] kombination = liste.findeProduktKombination(4.00);
        assertNotNull(kombination, "Test_D01");
        assertEquals(2, kombination.length, "Test_D02");
        
        boolean istKorrektesPaar = (kombination[0] == p1 && kombination[1] == p2) || 
                                   (kombination[0] == p2 && kombination[1] == p1);
        assertTrue(istKorrektesPaar, "Test_D03");

        // Keine passende Kombination
        
        // Budget = 10.00 -> keine Kombination -> null
        Produkt[] keineKombination = liste.findeProduktKombination(10.00);
        assertNull(keineKombination, "Test_D04");
        
        // Leere Liste -> keine Kombination -> null
        Einkaufsliste leereListe = new Einkaufsliste();
        assertNull(leereListe.findeProduktKombination(10.0), "Test_D05");
        
        // Nur ein Element -> keine Kombination -> null
        Einkaufsliste einElementListe = new Einkaufsliste();
        einElementListe.addProduct(new Produkt("Mango", 5.0));
        assertNull(einElementListe.findeProduktKombination(10.0), "Test_D06");
        
        // Budget = 10.00 -> Keine Kombination -> null
        Einkaufsliste selbstKombListe = new Einkaufsliste();
        Produkt p4 = new Produkt("Apfel", 5.0);
        Produkt p5 = new Produkt("Birne", 10.0);
        selbstKombListe.addProduct(p4);
        selbstKombListe.addProduct(p5);
        assertNull(selbstKombListe.findeProduktKombination(10.0), "Test_D07");
        
        // Passende Kombination
        
        // Budget = 10.00 -> Apfel (5.0) + Birne (5.0) -> Array mit 2 Elementen
        Produkt p6 = new Produkt("Apfel", 5.0);
        Produkt p7 = new Produkt("Birne", 5.0);
        selbstKombListe.addProduct(p6);
        selbstKombListe.addProduct(p7);
        Produkt[] gleichePreise = selbstKombListe.findeProduktKombination(10.0);
        assertNotNull(gleichePreise, "Test_D08");
        assertEquals(2, gleichePreise.length, "Test_D09");
    }
    
    // Test_E: zeigeProdukte()
    @Test
    public void testZeigeProdukte() {
    	Einkaufsliste liste = new Einkaufsliste();

        // Konstruktor-Tests
    	Produkt p1 = new Produkt(null, -5.0); 
        Produkt p2 = new Produkt("", 1.00); 
        Produkt p3 = new Produkt("Apfel", 2.0);       

        // Rabatt-Tests
        Produkt p4 = new Produkt("Brot Voll", 4.00);
        p4.gebeRabatt(100.0);
        
        Produkt p5 = new Produkt("Brot 50%", 2.00);
        p5.gebeRabatt(50.0);
        
        Produkt p6 = new Produkt("Brot Null", 2.00);
        p6.gebeRabatt(0.0);

        Produkt p7 = new Produkt("Brot Negativ", 3.00);
        p7.gebeRabatt(-10.0);

        Produkt p8 = new Produkt("Brot ZuViel", 4.00);
        p8.gebeRabatt(150.0);

        liste.addProduct(p1);
        liste.addProduct(p2);
        liste.addProduct(p3);
        liste.addProduct(p4);
        liste.addProduct(p5);
        liste.addProduct(p6);
        liste.addProduct(p7);
        liste.addProduct(p8);

        // Hilfestellung für die schnelle Korrektur in der Konsole
        System.out.println("\n=== ERWARTETE WERTE ===");
        System.out.println("1. Unknown      | 0.00");
        System.out.println("2.              | 1.00");
        System.out.println("3. Apfel        | 2.00");
        System.out.println("4. Brot Voll    | 0.00");
        System.out.println("5. Brot 50%     | 1.00");
        System.out.println("6. Brot Null    | 2.00");
        System.out.println("7. Brot Negativ | 3.00");
        System.out.println("8. Brot ZuViel  | 4.00");
        System.out.println("=======================");
        System.out.println("AUSGABE DER STUDIS:");
        
        liste.zeigeProdukte();
    }
    
}