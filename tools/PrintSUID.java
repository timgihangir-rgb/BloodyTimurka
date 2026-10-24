import java.io.ObjectStreamClass;

/**
 * Печатает вычисляемые JVM serialVersionUID для указанных классов.
 * Запускается дважды - с ZetvlX.jar (значения донора) и с BE2.jar (значения игры),
 * чтобы побайтово починить расхождение в сериализованных событийных файлах.
 *
 * Использование: java -cp <game.jar>:tools PrintSUID <класс> [<класс> ...]
 */
public class PrintSUID {
    public static void main(String[] args) {
        for (String name : args) {
            try {
                Class<?> c = Class.forName(name);
                ObjectStreamClass osc = ObjectStreamClass.lookup(c);
                System.out.println(name + "\t" + osc.getSerialVersionUID() + "\t" + c.isInterface() + "\t" + java.lang.reflect.Modifier.isAbstract(c.getModifiers()));
            } catch (Throwable e) {
                System.out.println(name + "\tERROR\t" + e);
            }
        }
    }
}
