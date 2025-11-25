package eus.arreseainhize.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class ElementsRepository {

    List<Element> elements = new ArrayList<>();

    public interface Callback {
        void whenFinish(List<Element> elements);
    }

    ElementsRepository(){
        // Añadir elementos con ratings iniciales
        elements.add(new Element(R.drawable.android_original, "Android","Android es un sistema operativo móvil basado en el núcleo Linux y otros software de código abierto.", 4.5f));
        elements.add(new Element(R.drawable.angularjs_original, "AngularJS","AngularJS, es un framework de JavaScript de código abierto, mantenido por Google, que se utiliza para crear y mantener aplicaciones web de una sola página.", 4.0f));
        elements.add(new Element(R.drawable.bash_original, "Bash","GNU Bash o simplemente Bash es una interfaz de usuario de línea de comandos popular, específicamente un shell de Unix; así como un lenguaje de scripting. Bash fue originalmente escrito por Brian Fox para el sistema operativo GNU, y pretendía ser el reemplazo de software libre del shell Bourne.\u200B \u200B ", 3.5f));
        elements.add(new Element(R.drawable.c_original, "C","C es un lenguaje de programación de propósito general\u200B originalmente desarrollado por Dennis Ritchie entre 1969 y 1972 en los Laboratorios Bell, \u200B como evolución del anterior lenguaje B, a su vez basado en BCPL.\u200B\u200B\u200BAl igual que B, es un lenguaje orientado a la implementación de sistemas operativos, concretamente Unix", 4.0f));
        elements.add(new Element(R.drawable.bootstrap_original, "Bootstrap","Bootstrap es una biblioteca multiplataforma o conjunto de herramientas de código abierto para diseño de sitios y aplicaciones web", 4.5f));
        elements.add(new Element(R.drawable.cplusplus_original, "C++","C++ es un lenguaje de programación diseñado en 1979 por Bjarne Stroustrup. La intención de su creación fue extender al lenguaje de programación C y añadir mecanismos que permiten la manipulación de objetos. En ese sentido, desde el punto de vista de los lenguajes orientados a objetos, C++ es un lenguaje híbrido.", 4.0f));
        elements.add(new Element(R.drawable.csharp_original, "C#","\"C#\" es un lenguaje de programación multiparadigma desarrollado y estandarizado por la empresa Microsoft como parte de su plataforma .NET, que después fue aprobado como un estándar por la ECMA e ISO. C# es uno de los lenguajes de programación diseñados para la infraestructura de lenguaje común. ", 4.0f));
        elements.add(new Element(R.drawable.css3_original, "CSS3","CSS es un lenguaje de diseño gráfico que permite definir y crear la presentación de un documento estructurado escrito en un lenguaje de marcado. Es muy usado para establecer el diseño visual de los documentos web e interfaces de usuario escritas en HTML", 4.5f));
        elements.add(new Element(R.drawable.java_original, "Java","Java es un lenguaje de programación y una plataforma informática que fue comercializada por primera vez en 1995 por Sun Microsystems.\u200B\u200B", 4.0f));
        elements.add(new Element(R.drawable.unity_original, "Unity","Unity es un motor de videojuego multiplataforma creado por Unity Technologies. Unity está disponible como plataforma de desarrollo para Microsoft Windows, Mac OS, Linux. La plataforma de desarrollo tiene soporte de compilación con diferentes tipos de plataformas.", 4.0f));
        elements.add(new Element(R.drawable.unrealengine_original, "Unreal Engine","Unreal Engine es un motor de juegos creado por la compañía Epic Games, dado a conocer inicialmente en el shooter en primera persona Unreal en 1998.", 4.5f));
    }

    public List<Element> get() {
        return elements;
    }

    public void insert(Element element, Callback callback) {
        elements.add(element);
        callback.whenFinish(elements);
    }

    public void delete(Element element, Callback callback) {
        elements.remove(element);
        callback.whenFinish(elements);
    }

    public void update(Element elementToUpdate, float newRating, Callback callback) {
        for (int i = 0; i < elements.size(); i++) {
            Element current = elements.get(i);
            if (current.equals(elementToUpdate)) {
                elements.get(i).setRating(newRating);
                break;
            }
        }
        callback.whenFinish(new ArrayList<>(elements));
    }
}