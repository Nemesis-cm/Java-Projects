
package dvdlibrary.dto;

public class Library {
    private String name;
    private Dvd dvd;
    public Library(String name) {
        this.name = name;
    }

    public Dvd getDvd() {
        return dvd;
    }

    public String getName() {
        return name;
    }

    
}
