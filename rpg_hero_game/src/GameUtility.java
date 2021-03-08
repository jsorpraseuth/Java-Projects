import Difficulty.*;

public class GameUtility {
    public static DifficultyFactoryIF createDifficultyFactory(DifficultyType difficulty) {
        switch (difficulty) {
            case BEGINNER:
                return new BeginnerFactory();
            case INTERMEDIATE:
                return new IntermediateFactory();
            case ADVANCED:
                return new AdvancedFactory();
            default:
                return null;
        }
    }
}

