package com.optogo.service;

import com.optogo.utils.parse.DiseaseSymptomParser;
import unbbayes.prs.exception.InvalidParentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author avujasinovic
 * Class that is used to recommend other symptoms for certain disease.
 */
public class SymptomRecommender {

    public static final String DISEASE_SYMPTOM_FILE = "resources/disease_symptom.txt";

    /**
     * Method that recommends additional symptoms for certain disease which are not provided by patient.
     * This helps increasing precision. Diseases with probability less then 50% are not considered.
     * @param providedSymptoms
     * @return
     * @throws FileNotFoundException
     * @throws InvalidParentException
     */
    public static Set<String> recommend(List<String> providedSymptoms, Map<String, Float> predictions) throws IOException, InvalidParentException {
        Set<String> allSymptoms = new HashSet<>();
        Set<String> diseaseInPrediction = predictions.keySet();

        for(String d : diseaseInPrediction)
            if(predictions.get(d) > 0.3f)
                allSymptoms.addAll(DiseaseSymptomParser.getSymptoms(DISEASE_SYMPTOM_FILE, d));

        allSymptoms.removeAll(providedSymptoms);

        return allSymptoms;
    }

}
