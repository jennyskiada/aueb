package gr.aueb.moviesite.model;

import java.util.List;

/**
 * GetBookmarksResponse Model Object
 */
public class GetBookmarksResponse {

    private String email;
    private List<String> bookmarks;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<String> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
