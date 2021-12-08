# Hvordan kjøre prototypen

Programvare som trengs for å kjøre prototypen er: 
- IntelliJ med Java 15
- Nettleser (Google Chrome er anbefalt)
- Avhengigheter (blir automatisk satt opp av byggesystemet Gradle)
- Javalin
- JUnit
- Vue
- Hamcrest 
- jackson
- sqlite-jdbc


Kjøre prototypen: 
1. Unzippe prosjektmappen som leveres i Inspera
2. Fra File i IntelliJ, trykk på Open og hent prosjektet 'Software-Eng-8'
3. Gå til File igjen, og velg Project Structure (eller Ctrl+Alt+Shift+S) 
4. Fra Project Settings menyen velg Modules, og trykk på '+' tegnet over 'Software-Eng-8'
5. Velg ‘Import Module og hent 'Software Engineering'-mappen som ligger inne i 'Software-Eng-8'-mappa.
6. Herfra importer modulen som 'import module from external model' som et Gradle prosjekt
7. Åpne Project Structure igjen (fra File eller Ctrl+Alt+Shift+S)
8. Fra Project Settings menyen, velg ‘Project’, og under ‘Project SDK’ velg Java 15
9. Bygg prosjektet ved å trykke på ‘hammer’ symbolet øverst til høyre eller bruk snarveien Ctrl+F9.
10. Inne i prosjektstrukturen, naviger gjennom ‘Software-Eng-8’ til ‘Software Engineering’ til ‘src’ til ‘main’ til ‘java’ til ‘Software.Engineering.Gruppe’ til Application-filen
11. Høyreklikk og trykk på "Run ‘Application.main()’". Dette vil starte opp Webserveren og du kan nå gå til http://localhost:7777/ i nettleseren.

























