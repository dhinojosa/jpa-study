package com.evolutionnext.jpa.study;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Hinojosa
 * @since 6/17/14 6:16 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
@Entity
@Table(name = "xyz_band")
public class Band extends Performer {
    private String name;
    private List<Performer> performers;

    public Band() {
        this.performers = new ArrayList<Performer>();
    }

    public Band(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public List<Performer> getPerformers() {
        return performers;
    }


    protected void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public void addPerformer(Performer performer) {
        this.performers.add(performer);
    }
}
