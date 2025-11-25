package eus.arreseainhize.recyclerview;

public class Element {
    String name;
    String description;
    float rating;
    int image;

    public Element(int image, String name, String description) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.rating = 0; // ¡IMPORTANTE! Inicializar el rating
    }

    // Constructor sobrecargado que incluye rating
    public Element(int image, String name, String description, float rating) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }

    // Métodos getter y setter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    // Añade equals y hashCode para identificar elementos correctamente
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return image == element.image &&
                Float.compare(element.rating, rating) == 0 &&
                name.equals(element.name) &&
                description.equals(element.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + image;
        result = 31 * result + Float.floatToIntBits(rating);
        return result;
    }
}