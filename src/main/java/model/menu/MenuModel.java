package model.menu;

import model.Model;

public class MenuModel implements Model {

    public enum Option {NEWGAME, EXIT;
        private static final Option[] values = values();
        public Option next() {

            return null;
        }

        public Option previous() {

            return null;
        }

        public static int max() {

            return 0;
        }

        public int diffToOption(Option option) { // return the difference in index

            return 0;
        }

    } // Same question of Color enum. TODO: add more options
    private Option current;

    public MenuModel() {
        current = Option.NEWGAME;
    }

    public Option getCurrentOption() {
        return current;
    }

}
