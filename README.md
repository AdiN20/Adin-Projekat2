# Personal Management System (JavaFX + MongoDB)

Ovaj projekat predstavlja desktop aplikaciju za upravljanje ličnim finansijama i zadacima (To-Do lista), razvijenu u JavaFX-u sa MongoDB bazom podataka.

## Funkcionalnosti
- **Finansijski modul:** Dodavanje prihoda i troškova, automatsko računanje salda i praćenje broja transakcija.
- **To-Do lista:** Upravljanje svakodnevnim zadacima uz praćenje statistike ukupnih zadataka.
- **Export Report:** Generisanje profesionalnih PDF izvještaja za finansije i zadatke pomoću **iText** biblioteke.
- **Postavke:** Promjena lozinke direktno u bazi i mogućnost promjene vizuelne teme aplikacije.
- **Baza podataka:** Potpuna integracija sa MongoDB (NoSQL) bazom.

## Korištene Tehnologije
- **Java 17**
- **JavaFX** (Korisnički interfejs)
- **MongoDB** (Skladištenje podataka)
- **Maven** (Upravljanje zavisnostima)
- **iText** (PDF generisanje)

## Kako pokrenuti projekat
1. Osigurajte da je **MongoDB** servis pokrenut lokalno.
2. Klonirajte repozitorij.
3. Pokrenite `mvn clean install` da učitate sve zavisnosti iz `pom.xml`.
4. Pokrenite aplikaciju preko `Launcher.java` klase.

## Autori
- [Adin Piric]
