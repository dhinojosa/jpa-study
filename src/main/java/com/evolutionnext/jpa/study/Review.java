package com.evolutionnext.jpa.study;

import javax.persistence.*;

/**
 * @author Daniel Hinojosa
 * @since 6/17/14 9:13 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

@Entity
@Table(name = "xyz_review")
public class Review {
    private Long id;
    private String review;
    private Integer rating;
    private Album album;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    public String getReview() {
        return review;
    }


    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @ManyToOne
    @JoinColumn(name="albumID")
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
