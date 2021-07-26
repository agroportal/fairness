package fr.lirmm.fairness.assessment.principles.criterion.question.tests;



public class MetaDataExistTest implements Test<String> {


    private static MetaDataExistTest instance;

    @Override
    public boolean test(String... element) {
        return element[0] != null && ! element[0].trim().isEmpty();
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
