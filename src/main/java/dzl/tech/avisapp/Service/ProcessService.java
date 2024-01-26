package dzl.tech.avisapp.Service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProcessService {


    public int timeExecutionProcessFunction(){
        return 0;
    }
    private static void calculateAverageAndVariation(int n) {
        // Ici, vous pouvez ajouter le code pour calculer la moyenne et la variation
        double total = 0.0;
        for (int i = 1; i <= n; i++) {
            total += i;
        }
        double average = total / n;

        double sumOfSquares = 0.0;
        for (int i = 1; i <= n; i++) {
            sumOfSquares += Math.pow(i - average, 2);
        }
        double variance = sumOfSquares / n;
        double standardDeviation = Math.sqrt(variance);

        System.out.println("Moyenne : " + average);
        System.out.println("Ecart-type : " + (double)Math.round(standardDeviation));
    }

    private static void coupleWordStartAndEnd(String filepath) throws IOException {
        Path path = Paths.get(filepath+".txt"); // Remplacez par le chemin vers votre fichier
        String content = new String(Files.readAllBytes(path));

        Set<String> wordPairs = new HashSet<>();
        String[] words = content.split("\\s+"); // Sépare le texte en mots en utilisant l'espace comme séparateur

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            if (word1.endsWith(word2.substring(0, word2.length() - 1))) {
                wordPairs.add(word1 + " " + word2);
            }
        }

        System.out.println("Nombre de couples de mots identiques : " + wordPairs.size());
        System.out.println("Couples de mots identiques : " + wordPairs);

    }


}
