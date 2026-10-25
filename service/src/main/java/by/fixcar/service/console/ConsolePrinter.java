package by.fixcar.service.console;

import by.fixcar.model.Displayable;

import java.util.List;

public class ConsolePrinter {

    public static <T extends Displayable> void printAll(List<T> lst){
        if (lst.isEmpty()) System.out.println("Данных для отображения нет!");
        for(T obj : lst) obj.showInfo();
    }

}
