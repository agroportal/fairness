package fr.lirmm.fairness.assessment.principles.criterion.question.tests;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MetaDataExistTest implements Test<String> {


    public static final String NOT_EXIST_EXPLANATION = " is not present" ;
    private final static Set<String> INVALID_METADATA_VALUES = new HashSet<String>(
            Arrays.asList(new String[] { null, "null", "", "[]" }));
    private static MetaDataExistTest instance;

    @Override
    public boolean test(String... element) {
        return element[0] != null && ! element[0].trim().isEmpty() && !INVALID_METADATA_VALUES.contains(element[0]);
    }

    public static MetaDataExistTest getInstance() {
        if(instance == null){
            instance = new MetaDataExistTest();
        }
        return instance;
    }


    public static boolean isValid(String... element){
        return getInstance().test(element);
    }
}
