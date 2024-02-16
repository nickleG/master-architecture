package de.ng.master.architecture.publisher.publish;

import java.util.List;

public class TestData {

  private static final List<String> NOUNS = List.of("Haus", "Auto", "Baum", "Hund", "Katze", "Tisch", "Stuhl", "Buch", "Apfel", "Sonne",
      "Mond", "Fenster", "Tür", "Kaffee", "Tee", "Blume", "Garten", "Telefon", "Computer", "Schlüssel", "Uhr", "Brille",
      "Straße", "Fluss", "Berg", "Wolke", "Vogel", "Fisch", "Boot", "Schuh", "Hemd", "Hose", "Jacke", "Kuchen", "Keks",
      "Käse", "Milch", "Ei", "Salat", "Pizza", "Fahrrad", "Zug", "Flugzeug", "Schiff", "Ball", "Spielzeug", "Musik",
      "Film", "Buchstabe", "Zahl");

  private static final List<String> VERBS = List.of("gehen", "laufen", "springen", "tanzen", "schwimmen", "klettern", "spielen", "lesen");

  private static final List<String> ADJECTIVES = List.of("groß", "klein", "schön", "hässlich", "alt ", "jung");


  public static String generateMessage() {
    return String.format("%s %s %s", getRandomElement(ADJECTIVES), getRandomElement(NOUNS), getRandomElement(VERBS));
  }

  private static String getRandomElement(List<String> list) {
    return list.get((int) (Math.random() * list.size()));
  }
}
