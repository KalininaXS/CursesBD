package WithEm.EM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Stud")
@NoArgsConstructor
@AllArgsConstructor

public class Stud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long ID;

    @Column(name = "FIO")
    private String FIO;


    @Column(name = "ID_GROUP")
    private long ID_GROUP;


    @Column(name = "CURSES_NAME")
    private String CURSES_NAME;

    @Column(name = "MARK")
    private int MARK;

}