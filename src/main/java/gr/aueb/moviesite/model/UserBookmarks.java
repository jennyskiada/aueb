package gr.aueb.moviesite.model;

import java.util.List;

/**
 * UserBookmarks Object
 */
public class UserBookmarks {

    private String email;
    private String name;
    private List<String> bookmarks;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<String> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
