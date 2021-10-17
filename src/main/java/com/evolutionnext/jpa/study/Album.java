package com.evolutionnext.jpa.study;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author Daniel Hinojosa
 * @since 6/14/14 2:15 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
@Entity
@Table(name = "xyz_album")
public class Album {
    private Long id;
    private String name;
    private List<Performer> performers;
    private Date releaseDate;
    private List<Review> reviews;
    private List<String> genres;
    private List<Track> tracks;

    public Album() {
    }

    public Album(String s) {
        this.name = s;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "xyz_album_performers",
            joinColumns = @JoinColumn(name = "albumID"),
            inverseJoinColumns = @JoinColumn(name = "performerID"))
    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    @Temporal(value = TemporalType.DATE)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @ElementCollection
    @CollectionTable(
            name = "xyz_album_genres",
            joinColumns = @JoinColumn(name = "albumID")
    )
    @Column(name = "genre")
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @OneToMany(mappedBy = "album")
    public List<Track> getTracks() {
        return tracks;
    }

    protected void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return Objects.toString(name);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Album)) return false;
        Album other = (Album) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }


    //Internal Listeners
    @PrePersist
    void onPrePersist() {
        System.out.println("pre-persist called");
    }

    @PostPersist
    void onPostPersist() {
        System.out.println("post-persist called");
    }

    @PostLoad
    void onPostLoad() {
        System.out.println("post-load called");
    }

    @PreUpdate
    void onPreUpdate() {
        System.out.println("pre-update called");
    }

    @PostUpdate
    void onPostUpdate() {
        System.out.println("post-update called");
    }

    @PreRemove
    void onPreRemove() {
        System.out.println("pre-remove called");
    }

    @PostRemove
    void onPostRemove() {
        System.out.println("post-remove called");
    }
}
