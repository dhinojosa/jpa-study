package com.evolutionnext.jpa.study;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Daniel Hinojosa
 * @since 6/18/14 6:15 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
@Entity
@Table(name = "xyz_countdown")
public class Countdown {
    private Long id;
    private Date week;
    private List<Track> tracks;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getWeek() {
        return week;
    }

    public void setWeek(Date week) {
        this.week = week;
    }

    @OneToMany
    @OrderColumn(name="ranking")
    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
