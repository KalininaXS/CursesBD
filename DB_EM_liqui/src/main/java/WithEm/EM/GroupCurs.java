package WithEm.EM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "GroupCurs")
@NoArgsConstructor
@AllArgsConstructor
public class GroupCurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long ID;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ID_GROUP")
    private List<Stud> GROUP_CURSES;


    //@Column(name = "NUMBER_OF_GROUP")
    //private int NUMBER_OF_GROUP;

    @Column (name = "YEAR_OF_ENTER")
    private int YEAR_OF_ENTER;
}
