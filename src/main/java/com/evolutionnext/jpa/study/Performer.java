package com.evolutionnext.jpa.study;

import javax.persistence.*;
import java.util.List;

/**
 * @author Daniel Hinojosa
 * @since 6/17/14 6:14 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
@Entity
@Table(name = "xyz_performer")
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Performer {
    private Long id;
    private List<Album> albums;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany
    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
