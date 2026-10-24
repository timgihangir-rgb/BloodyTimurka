import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Проверка событийных файлов сценария: читает <сценарий>_E (сериализованный
 * Events_GameData) тем же кодом, что и игра, и печатает список событий.
 *
 * Использование: java -cp BE2.jar:tools CheckEvents <файл> [<файл> ...]
 */
public class CheckEvents {
    public static void main(String[] args) throws Exception {
        int totalEvents = 0;
        int badFiles = 0;
        for (String path : args) {
            try {
                Object o;
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
                    o = in.readObject();
                }
                Class<?> c = o.getClass();
                Field fSize = c.getDeclaredField("iEventsSize");
                fSize.setAccessible(true);
                int size = fSize.getInt(o);
                Field fList = c.getDeclaredField("lEvents");
                fList.setAccessible(true);
                List<?> list = (List<?>) fList.get(o);
                List<String> names = new ArrayList<>();
                for (Object ev : list) {
                    Field fName = ev.getClass().getDeclaredField("sEventName");
                    fName.setAccessible(true);
                    names.add((String) fName.get(ev));
                }
                totalEvents += names.size();
                System.out.println("OK  " + path + "  событий=" + size + "  прочитано=" + names.size());
                for (String n : names) System.out.println("      " + n);
            } catch (Throwable e) {
                ++badFiles;
                System.out.println("ОШИБКА " + path + " -> " + e);
            }
        }
        System.out.println("---");
        System.out.println("файлов с ошибками: " + badFiles);
        System.out.println("всего событий: " + totalEvents);
    }
}
