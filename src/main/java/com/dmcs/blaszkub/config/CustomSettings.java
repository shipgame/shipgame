package com.dmcs.blaszkub.config;

import com.dmcs.blaszkub.enums.ModeType;
import com.dmcs.blaszkub.enums.ShipType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class CustomSettings {
    public static final String CHOOSE_SHOOT_COORDINATES = "Choose shoot coordinates:";
    public static final String YOU_WON = "YOU WON!";

    private static final String CHOOSE_SHIP_TYPE_1_2_3_4 = "Choose ship type: 1, 2, 3, 4";
    private static final String DO_YOU_WANT_TO_SET_SETTINGS_BY_YOURSELF_Y_N = "Do you want to set settings by yourself? y/n";
    private static final String DO_YOU_WANT_TO_PLACE_SHIPS_BY_YOURSELF_Y_N = "Do you want to place ships by yourself? y/n";
    private static final String ENTER_LENGTH_OF_BOARD = "Enter length of board:";
    private static final String ENTER_WIDTH_OF_BOARD = "Enter width of board:";
    private static final char N = 'n';
    private static final String SIZE_HAS_TO_BE_BIGGER_THAN_OR_EQUAL_TO_20_AND_SMALLER_THAN_1000_ENTER_NEW_VALUE = "Size has to be bigger than or equal to 20 and smaller than 1000\nEnter new value:";
    private static final char Y = 'y';
    private static final String YOU_HAVE_TO_CHOOSE_BETWEEN_Y_YES_AND_N_NO = "You have to choose between 'y' - yes and 'n' - no";
    private static final String DO_YOU_WANT_TO_CHOOSE_ANOTHER_SHIP = "Do you want to choose another ship?";
    private static final int MIN_SIZE = 10;
    private static final int MAX_SIZE = 1000;

    private boolean setByPlayer;
    @Setter
    private GameConfig gameConfig;
    private Scanner scanner = new Scanner(System.in);

    public void setCustomGameConfig() {
        System.out.println(DO_YOU_WANT_TO_SET_SETTINGS_BY_YOURSELF_Y_N);
        char choice = scanner.next().charAt(0);
        setByPlayer = choice == Y;

        if (setByPlayer) {
            getSettingsFromPlayer();
        }
    }

    public List<ShipType> getShipTypes() {
        List<ShipType> shipTypes = new ArrayList<>();
        char choice;

        do {
            System.out.println(CHOOSE_SHIP_TYPE_1_2_3_4);
            char shipChoice = scanner.next().charAt(0);
            shipTypes.add(ShipType.fromString(Character.toString(shipChoice)));

            System.out.println(DO_YOU_WANT_TO_CHOOSE_ANOTHER_SHIP);
            choice = getChoiceLetter();
        } while (choice == Y);

        return shipTypes;
    }

    private void getSettingsFromPlayer() {
        gameConfig = new GameConfig();

        System.out.println(ENTER_LENGTH_OF_BOARD);
        gameConfig.setYAxisLength(getSize());

        System.out.println(ENTER_WIDTH_OF_BOARD);
        gameConfig.setXAxisLength(getSize());

        System.out.println(DO_YOU_WANT_TO_PLACE_SHIPS_BY_YOURSELF_Y_N);
        gameConfig.setModeType(chooseModeType(getChoiceLetter()));
    }

    private ModeType chooseModeType(char value) {
        return value == Y ? ModeType.MANUAL : ModeType.AUTO;
    }

    private int getSize() {
        int size = scanner.nextInt();

        if (size < MIN_SIZE || size > MAX_SIZE) {
            System.out.println(SIZE_HAS_TO_BE_BIGGER_THAN_OR_EQUAL_TO_20_AND_SMALLER_THAN_1000_ENTER_NEW_VALUE);
            return getSize();
        }

        return size;
    }

    private char getChoiceLetter() {
        char letter = scanner.next().charAt(0);

        if (letter != Y && letter != N) {
            System.out.println(YOU_HAVE_TO_CHOOSE_BETWEEN_Y_YES_AND_N_NO);
            return getChoiceLetter();
        }

        return letter;
    }

    @Override
    protected void finalize() throws Throwable {
        scanner.close();
        super.finalize();
    }
}
