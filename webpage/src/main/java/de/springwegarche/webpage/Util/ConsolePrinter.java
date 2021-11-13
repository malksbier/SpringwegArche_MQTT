package de.springwegarche.webpage.Util;

import java.util.Objects;

public class ConsolePrinter {
    private boolean print = true;

    public boolean println (String text) {
        if(print) {
            System.out.println(text);
            return true;
        } else {
            return false;
        }
    }
    public boolean print (String text) {
        if(print) {
            System.out.print(text);
            return true;
        } else {
            return false;
        }
    }

    public ConsolePrinter(boolean print) {
        this.print = print;
    }


    public boolean getPrint() {
        return this.print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(print);
    }

    @Override
    public String toString() {
        return "{" +
            " print='" + getPrint() + "'" +
            "}";
    }

}
